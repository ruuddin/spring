
package com.example.cassandra.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.example.cassandra.CassandraService;
import com.example.cassandra.Employee;
import com.example.cassandra.Person;
import com.example.cassandra.accessors.UserAccessor;
import com.example.cassandra.model.Address;
import com.example.cassandra.model.User;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class CassandraDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraDAO.class);

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    @Autowired
    private ListenableFuture<Session> asyncSession;

    public void getCassandraReleaseVersion() {
        try {
            ResultSet rs = session.execute("select release_version from system.local");

            Row row = rs.one();
            LOG.info(row.getString("release_version"));
        }
        catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    public void savePerson() {
        Person p = new Person("Riaz", LocalDate.now());
        Employee e = new Employee(10, "Home");

        /*
         * Option 1
         * 
        // Using SimpleStatement
        Statement stmt = new SimpleStatement("INSERT INTO t (id, json) VALUES (?, ?)", 42, p);
        */
        // Option 2: Using the Query Builder. 10x slower than PreparedStatement and BoundStatement
        /*BuiltStatement insertStmt = QueryBuilder.insertInto("t").value("id", 42).value("json", p);
        session.execute(insertStmt);
        
        insertStmt = QueryBuilder.insertInto("t").value("id", 43).value("json", e);
        session.execute(insertStmt);
        */

        // Using BoundStatements
        PreparedStatement ps = session.prepare("INSERT INTO t (id, json) VALUES (?, ?)");
        BoundStatement bs1 = ps.bind(1, p); // or alternatively...
        BoundStatement bs2 = ps.bind().setInt(0, 2).set(1, e, Person.class);
        session.execute(bs2);
        session.execute(bs1);

        LOG.info("Save success");
    }
    
    public void asyncRequest(){
        ListenableFuture<ResultSet> resultSet = Futures.transform(asyncSession,
                new AsyncFunction<Session, ResultSet>() {
                    public ListenableFuture<ResultSet> apply(Session s) throws Exception {
                        return s.executeAsync("select release_version from system.local");
                    }
                });

        while (!asyncSession.isDone())
            LOG.info("Waiting");

        // Use transform with a simple Function to apply a synchronous computation on the result:
        ListenableFuture<String> version = Futures.transform(resultSet,
                new Function<ResultSet, String>() {
                    public String apply(ResultSet rs) {
                        return rs.one().getString("release_version");
                    }
                });

        // Use a callback to perform an action once the future is complete:
        Futures.addCallback(version, new FutureCallback<String>() {
            public void onSuccess(String version) {
                LOG.info("Cassandra version: " + version);
            }

            public void onFailure(Throwable t) {
                LOG.error("Failed to retrieve the version: " + t.getMessage());
            }
        });
    }

    public void mappingManagerExample() {
        User u = createUser();

        save(User.class, u);

        System.out.println(getUser(u.getUserid()));
    }

    public void getPerson() {
        ResultSet rs = session.execute("SELECT * from t");
        List<Row> rows = rs.all();
        System.out.println(rows);

        /*// Let the driver convert the string for you...
        Person p = rows.get(0).get(1, Person.class);
        System.out.println(p);
        
        Person e = rows.get(1).get(1, Person.class);
        Employee emp = (Employee)e;
        System.out.println(emp);*/

        // ... or retrieve the raw string if you need it
        for (Row r : rows)
            System.out.println(r.get(1, String.class));
    }

    public void getAllUsers_AccessorApproach() {
        LOG.info("accessor_Ex6()");
        UserAccessor ua = mappingManager.createAccessor(UserAccessor.class);
        Result<User> users = ua.getAll();

        for (User u : users)
            System.out.println(u);
    }

    private User createUser() {
        UUID userId = UUID.randomUUID();
        User u = new User();
        u.setUserid(userId);
        u.setName("Riaz" + LocalDate.now());
        u.setAddress(new Address("Lincoln", 95051));
        return u;
    }

    private User getUser(UUID userId) {
        return mappingManager.mapper(User.class).get(userId);
    }

    private <T> void save(Class<T> c, Object o) {
        mappingManager.mapper(c).save((T)o);
    }

    /**
     * A thread to understand the connectionpooling and current load on the system.
     */
    private void currentLoad() {
        final LoadBalancingPolicy loadBalancingPolicy = session.getCluster().getConfiguration()
                .getPolicies().getLoadBalancingPolicy();
        final PoolingOptions poolingOptions = session.getCluster().getConfiguration()
                .getPoolingOptions();

        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Session.State state = session.getState();
                for (Host host : state.getConnectedHosts()) {
                    HostDistance distance = loadBalancingPolicy.distance(host);
                    int connections = state.getOpenConnections(host);
                    int inFlightQueries = state.getInFlightQueries(host);
                    System.out.printf("%s connections=%d, current load=%d, max load=%d%n", host,
                            connections, inFlightQueries,
                            connections * poolingOptions.getMaxRequestsPerConnection(distance));
                }
            }
        }, 5, 5, TimeUnit.SECONDS);
    }
}
