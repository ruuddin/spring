
package com.example.junit;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class ConsoleRunner {
    public static void main(String[] args) {
        JUnitCore ju = new JUnitCore();
        ju.addListener(new TextListener(System.out));
        
        ju.run(TrackingServiceTest.class);
    }
}
