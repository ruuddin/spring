package com.spring.webservices;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                "com.spring.webservices");

        try {
            WebServiceClient wsClient = ac.getBean(WebServiceClient.class);
            wsClient.customSendAndReceive();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            
        }

    }
}
