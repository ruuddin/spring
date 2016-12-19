package com.training.spring;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface ContactRepository extends CrudRepository<Contact, Integer>{
    
    @Query("delete from Contact c where c.id IN :keys")
    void deleteAll(@Param("keys") Collection<Integer> keys);
    
    Contact findByName(String name);
}
