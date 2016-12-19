package com.training.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextSingleton {

    private static ApplicationContext appContext;
    
    @Autowired
    public ApplicationContextSingleton(ApplicationContext appContext){
        ApplicationContextSingleton.appContext = appContext;
    }
    
    public static ApplicationContext getApplicationContext(){
        return appContext;
    }
}
