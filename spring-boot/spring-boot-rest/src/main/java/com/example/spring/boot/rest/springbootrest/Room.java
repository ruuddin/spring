package com.example.spring.boot.rest.springbootrest;

public class Room {

    private long id;
    private String type;
    private String number;
    private String name;

    public Room(long id, String type, String number, String name) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.name = name;
    }

    public Room() { super(); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
