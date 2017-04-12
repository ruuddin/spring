
package com.example.java.concurrency;

/**
 * Steps to safely implement Producer and Consumer
 * 1. Synchronize the blocks inside produce() and consume() method, else there will be race condition.
 * 2. When the buffer is full/empty the producer/consumer thread should call wait() (i.e., release the lock) and 
 *      call notify() (i.e., awake the thread waiting for the same lock)
 *      wait() and notify() should be in synchronized block, else the program will crash. These methods work on the same lock.
 *      If there are more than one threads waiting for a lock, only one will be picked up.
 * 
 * @author riazuddin
 *
 */
public class ProducerConsumer {

    private static int[] buffer;
    private static int count;

    private static Object lock = new Object();

    static class Producer {
        void produce() {
            synchronized (lock) {
                if (isFull(buffer)) {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                lock.notify();
            }
        }
    }

    static class Consumer {
        void consume() {
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count--] = 0;
                lock.notify();
            }
        }

    }

    private static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    private static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        Producer p = new Producer();
        Consumer c = new Consumer();

        Runnable producerTask = () -> {
            for (int i = 0; i < 50; i++) {
                p.produce();
            }

            System.out.println("Done producing");
        };

        Runnable consumerTask = () -> {
            for (int i = 0; i < 45; i++) {
                c.consume();
            }

            System.out.println("Done consuming");
        };

        Thread consumerThread = new Thread(consumerTask);
        Thread producerThread = new Thread(producerTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer " + count);
    }
}
