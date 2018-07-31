package com.example.spring.boot.rest.springbootdata;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServices {

    private static List<Room> rooms = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(i, "Rooom"+i, "R"+i, "Q"));
        }
    }


    public List<Room> getAllRooms() {
        return rooms;
    }
}
