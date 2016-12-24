
package com.example.cassandra;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author riazuddin
 */
@Component
public class CassandraService {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraMainApplication.class);

    @Autowired
    private Session session;

    @Autowired
    private ListenableFuture<Session> asyncSession;

    public void getReleaseVersion_Ex1() {
        try {
            ResultSet rs = session.execute("select release_version from system.local");

            Row row = rs.one();
            LOG.info(row.getString("release_version"));
        }
        catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    public void asyncRequest_Ex2() {

        ListenableFuture<ResultSet> resultSet = Futures.transform(asyncSession,
                new AsyncFunction<Session, ResultSet>() {
                    public ListenableFuture<ResultSet> apply(Session s) throws Exception {
                        return s.executeAsync("select release_version from system.local");
                    }
                });

        while (!asyncSession.isDone())
            System.out.println("Waiting");

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

    public void savePerson_Ex3() {
        Person p = new Person("Riaz", LocalDate.now());
        /*
         * Option 1
         * 
        // Using SimpleStatement
        Statement stmt = new SimpleStatement("INSERT INTO t (id, json) VALUES (?, ?)", 42, p);
        */
        // Option 2: Using the Query Builder
        BuiltStatement insertStmt = QueryBuilder.insertInto("t").value("id", 42).value("json", p);
        session.execute(insertStmt);
        System.out.println("Save success");
        /*
         * Option 3
         * 
        // Using BoundStatements
        PreparedStatement ps = session.prepare("INSERT INTO t (id, json) VALUES (?, ?)");
        BoundStatement bs1 = ps.bind(42, p); // or alternatively...
        BoundStatement bs2 = ps.bind().setInt(0, 42).set(1, p, Person.class);
        */
    }
    
    public void getPerson_Ex4(){
        ResultSet rs = session.execute("SELECT * from t");
        Row row = rs.one();
        
        // Let the driver convert the string for you...
        Person p = row.get(1, Person.class);
        System.out.println(p);
        
        // ... or retrieve the raw string if you need it
        String json = row.get(1, String.class); // row.getString(1) would have worked too
        System.out.println(json);
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
