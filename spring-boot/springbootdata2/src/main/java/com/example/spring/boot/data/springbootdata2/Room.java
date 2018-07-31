package com.example.spring.boot.data.springbootdata2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROOM")
public class Room {

    @Id
    @Column(name="ROOM_ID")
    @GeneratedValue
    private long id;
    @Column(name="BED_INFO")
    private String type;
    @Column(name="ROOM_NUMBER")
    private String number;
    @Column(name="NAME")
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
