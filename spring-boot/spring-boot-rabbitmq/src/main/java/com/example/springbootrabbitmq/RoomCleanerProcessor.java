package com.example.springbootrabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomCleanerProcessor {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoomCleanerProcessor.class);

    @Autowired
    public RoomCleanerProcessor(ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    public void receiveMessage(String message) {
        logger.info("message recvd");
        try {
            Room room = this.objectMapper.readValue(message, Room.class);
            logger.info("Room ready for cleaning "+room.getNumber());
        } catch(Exception e) {
            logger.error("Error", e);
        }
    }
}
