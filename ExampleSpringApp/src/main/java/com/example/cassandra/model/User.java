
package com.example.cassandra.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Computed;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "example", name = "users", readConsistency = "QUORUM", writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class User {
    
    @PartitionKey
    @Column(name = "user_id")
    private UUID userid;
    
    @Column
    private String name;
    
    @Computed("ttl(name)")
    Integer ttl;
    
    Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userId) {
        this.userid = userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User [userId=" + userid + ", name=" + name + ", ttl=" + ttl + ", address=" + address
                + "]";
    }

    public void setName(String name) {
        this.name = name;
    }
}
