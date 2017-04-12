package com.example.java.concurrency;

public class SynchronizeObject {
    private final Object key = new Object();
    
    public String init() {
        synchronized(key) {
            // do some stuff
        }
        
        return null;
    }
}
