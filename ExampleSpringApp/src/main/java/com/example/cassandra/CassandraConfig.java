
package com.example.cassandra;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryLogger;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.example.cassandra.dao.CassandraDAO;
import com.google.common.util.concurrent.ListenableFuture;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraConfig {

    @Autowired
    Environment env;

    @Bean
    public Cluster cluster() throws Exception {
        // Optional: PoolingOptions allows to configure connection pooling
        PoolingOptions pooling = new PoolingOptions();
        QueryLogger queryLogger = QueryLogger.builder()
            .build();
        
        Cluster c = Cluster.builder()
                .addContactPoints(InetAddress.getByName(env.getProperty("cassandra.contactpoints")))
                .withPoolingOptions(pooling).build();
        c.register(queryLogger);
        c.getConfiguration().getQueryOptions().setFetchSize(2000);
        // c.getConfiguration().getPoolingOptions(); //Get pooling options at runtime.

        JsonCodec<Person> jsonCodec = new JsonCodec<Person>(Person.class);
        CodecRegistry codeRegistry = c.getConfiguration().getCodecRegistry();
        codeRegistry.register(jsonCodec);

        return c;
    }

    @Bean
    public Session session() throws Exception {
        Session s = cluster().connect(env.getProperty("cassandra.keyspace"));
        return s;
    }
    
    @Bean MappingManager mappingManager() throws Exception{
       return new MappingManager(session());
    }

    @Bean
    public ListenableFuture<Session> asyncSession() throws Exception {
        return cluster().connectAsync();
    }
    
    @Bean
    public CassandraDAO cassandraDAO(){
        return new CassandraDAO();
    }
}
