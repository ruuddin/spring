package com.example.spring.boot.security.springbootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServices {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServices(RoomRepository roomRepository) {
        super();
        this.roomRepository = roomRepository;
    }
//
//    static {
//        for (int i = 0; i < 10; i++) {
//            rooms.add(new Room(i, "Rooom"+i, "R"+i, "Q"));
//        }
//    }
//

    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();
        this.roomRepository.findAll().forEach(rooms::add);

        System.out.println(rooms);
        return rooms;
    }
}
