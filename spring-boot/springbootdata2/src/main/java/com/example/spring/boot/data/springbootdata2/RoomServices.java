package com.example.spring.boot.data.springbootdata2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServices {

    private RoomRepository roomRepository;
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    public RoomServices(RoomRepository roomRepository, RabbitMQProducer rabbitMQProducer) {
        super();
        this.roomRepository = roomRepository;
        this.rabbitMQProducer = rabbitMQProducer;
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
        this.rabbitMQProducer.sendToRabbit(rooms);
//        System.out.println(rooms);

        return rooms;
    }
}
