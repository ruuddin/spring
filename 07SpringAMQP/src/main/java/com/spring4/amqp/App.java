
package com.spring4.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static final String QUEUE_NAME = "sample-queue";

    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext(
                "classpath:/rabbitmq-config.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend(QUEUE_NAME, "foo");
        System.out.println(template.receiveAndConvert(QUEUE_NAME));

        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(
                RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend(QUEUE_NAME, "foo");
        System.out.println(template.receiveAndConvert(QUEUE_NAME));
        */
    }
}
