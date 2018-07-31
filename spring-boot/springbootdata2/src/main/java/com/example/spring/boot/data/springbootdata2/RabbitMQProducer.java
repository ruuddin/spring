package com.example.spring.boot.data.springbootdata2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
/*
    Start RabbitMQ server on the system
    Run maven module spring-boot-rabbitmq: it has the consumer
    Then run this module: this class is the producer, sends message to RabbitMQ
    Go to http://localhost:9999/api/rooms and also monitor the log on both
        consumer and producer applications
 */
@Component
public class RabbitMQProducer {

    @Value("${amqp.queue.name}")
    private String queueName;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private final ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context, ObjectMapper objectMapper) {
        super();
        this.restTemplate = new RestTemplate();
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
        this.objectMapper = objectMapper;
    }

    public void sendToRabbit(List<Room> list) {

        list.forEach(r -> {
            logger.info("Sending message");
            try {
                String json = objectMapper.writeValueAsString(r);
                rabbitTemplate.convertAndSend(queueName, json);
            } catch (JsonProcessingException e) {
                logger.error("Parsing exception", e);
            }
        });

//        context.close();
    }
}
