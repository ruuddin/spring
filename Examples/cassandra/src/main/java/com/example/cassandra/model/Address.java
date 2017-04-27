
package com.example.cassandra.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(keyspace = "example", name = "address")
public class Address {
    private String street;
    @Field(name = "zip_code")
    private int zipCode;
    
    public Address(){}
    
    public Address(String street, int zipCode){
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", zipCode=" + zipCode + "]";
    }

}