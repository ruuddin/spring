
package com.example.cassandra;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.datastax.driver.core.Cluster;

public class CassandraMainApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                "com.example.cassandra");
        Cluster cluster = ac.getBean(Cluster.class);

        try {
            CassandraService cs = ac.getBean(CassandraService.class);
            cs.exGetReleaseVersion();
            cs.ex2AsyncRequest();
            cs.ex3SavePerson();
            cs.ex4GetPerson();
            
            cs.ex5MappingManagerSaveAndGet();
            cs.ex6Accessor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            cluster.close();
        }

    }
}
