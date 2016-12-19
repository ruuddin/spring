package com.training.spring;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address implements Serializable{
    
    @Id
    private Integer id;
    
    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", city=" + city + ", postcode="
                + postcode + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null)? 0 : city.hashCode());
        result = prime * result + ((id == null)? 0 : id.hashCode());
        result = prime * result + ((postcode == null)? 0 : postcode.hashCode());
        result = prime * result + ((street == null)? 0 : street.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address)obj;
        if (city == null) {
            if (other.city != null)
                return false;
        }
        else if (!city.equals(other.city))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (postcode == null) {
            if (other.postcode != null)
                return false;
        }
        else if (!postcode.equals(other.postcode))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        }
        else if (!street.equals(other.street))
            return false;
        return true;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    private String street;
    private String city;
    private String postcode;
    
}
