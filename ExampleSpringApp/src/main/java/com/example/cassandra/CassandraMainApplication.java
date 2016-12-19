
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
            cs.getReleaseVersion_Ex1();
            cs.asyncRequest_Ex2();
            cs.savePerson_Ex3();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            cluster.close();
        }

    }
}
