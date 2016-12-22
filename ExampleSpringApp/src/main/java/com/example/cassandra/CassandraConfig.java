
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
import com.datastax.driver.core.Session;
import com.google.common.util.concurrent.ListenableFuture;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraConfig {

    @Autowired
    Environment env;

    @Bean
    public Cluster cluster() throws Exception{
        //Optional: PoolingOptions allows to configure connection pooling
        PoolingOptions pooling = new PoolingOptions();
        Cluster c = Cluster.builder()
                .addContactPoints(InetAddress.getByName(env.getProperty("cassandra.contactpoints")))
                .withPoolingOptions(pooling)
                .build();
        
        //c.getConfiguration().getPoolingOptions(); //Get pooling options at runtime.
        
        JsonCodec<Person> myJsonCodec = new JsonCodec<Person>(Person.class);
        CodecRegistry myCodecRegistry = c.getConfiguration().getCodecRegistry();
        myCodecRegistry.register(myJsonCodec);
        
        return c;
    }

    @Bean
    public Session session() throws Exception {
        return cluster().connect(env.getProperty("cassandra.keyspace"));
    }

    @Bean
    public ListenableFuture<Session> asyncSession() throws Exception {
        return cluster().connectAsync();
    }
}
