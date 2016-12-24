
package com.example.cassandra;

import java.time.LocalDate;

public class Person {

    private String name;
    
    private LocalDate date;
    
    public Person(){
    }

    public Person(
            String name, LocalDate date){
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", date=" + date + "]";
    }
}
