
package com.example.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cassandra.dao.CassandraDAO;

/**
 * @author riazuddin
 */
@Component
public class CassandraService {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraService.class);

    @Autowired
    private CassandraDAO cassandraDAO;
    
    public void exGetReleaseVersion() {
        cassandraDAO.getCassandraReleaseVersion();
    }

    public void ex2AsyncRequest() {
        cassandraDAO.asyncRequest();
    }

    public void ex3SavePerson() {
        cassandraDAO.savePerson();
    }
    
    public void ex4GetPerson(){
        cassandraDAO.getPerson();
    }
    
    public void ex5MappingManagerSaveAndGet(){
        cassandraDAO.mappingManagerExample();
    }
    
    public void ex6Accessor(){
        cassandraDAO.getAllUsers_AccessorApproach();
    }
}
