
package com.example.designpatterns.creational;

public class SingletonDemo {

    // lazily loaded
    private static SingletonDemo instance = null;

    private SingletonDemo(){
        
    }

    public static SingletonDemo getInstance() {
        if (instance == null)
            synchronized (SingletonDemo.class) { //thread-safety
                if (instance == null)
                    instance = new SingletonDemo();
            }

        return instance;
    }
}
