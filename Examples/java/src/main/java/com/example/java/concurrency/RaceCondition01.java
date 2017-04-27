
package com.example.java.concurrency;

public class RaceCondition01 {

    public static void main(String[] args) throws InterruptedException {
        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable r = () -> {
            for (int i = 0; i < 1_000; i++)
                longWrapper.incrementValue();
        };

        Thread[] threads = new Thread[1_000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }

        // join is to ensure that the statements following join are executed
        // after the threads have finished execution.
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("Value = " + longWrapper.getValue());
    }
}

class LongWrapper {
    private long l;
    private Object lock = new Object();

    public LongWrapper(
            long l){
        this.l = l;
    }

    public long getValue() {
        synchronized (lock) {
            return l;
        }
    }

    public void incrementValue() {
        synchronized (lock) {
            l = l + 1;
        }
    }
}
