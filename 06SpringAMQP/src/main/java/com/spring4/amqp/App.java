
package com.spring4.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Hello world!
 */
public class App {
    private static final String QUEUE_NAME = "sample-queue";

    public static void main(String[] args) {

        CachingConnectionFactory factory = null;
        try {
            factory = new CachingConnectionFactory("localhost");

            // Creating a queue
            RabbitAdmin admin = new RabbitAdmin(factory);
            Queue queue = new Queue(QUEUE_NAME);
            admin.declareQueue(queue);

            RabbitTemplate template = new RabbitTemplate(factory);
            // send to a default exchange
            template.convertAndSend("", QUEUE_NAME, "sample-queue test message!");

            // Receive the message
            System.out.println(template.receiveAndConvert(QUEUE_NAME));
            /*
            TopicExchange exchange = new TopicExchange("sample-topic-exchange");
            admin.declareExchange(exchange);
            admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("sample-key"));
            
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
            Object listener = new Object() {
                public void handleMessage(String message) {
                    System.out.println("Message received: " + message);
                }
            };
            MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
            container.setMessageListener(adapter);
            container.setQueueNames(QUEUE_NAME);
            container.start();*/
        }
        finally {
            if (factory != null) {
                factory.destroy();
            }
        }
    }
}
