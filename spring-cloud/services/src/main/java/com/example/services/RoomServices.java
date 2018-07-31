package com.example.services;

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

    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();
        this.roomRepository.findAll().forEach(rooms::add);

        System.out.println(rooms);
        return rooms;
    }

    public Room findByRoomNumber(String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }
}
