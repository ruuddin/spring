package com.example.cassandra.accessors;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.example.cassandra.model.User;

@Accessor
public interface UserAccessor {

    @Query("select * from users")
    Result<User> getAll();
    
    @Query("select * from users where user_id = ?")
    User getOne(UUID uuid);
    
    @Query("insert into user (userId, name) values (:u, :n)")
    ResultSet insert(@Param("u") UUID userId, @Param("n") String name);
}
