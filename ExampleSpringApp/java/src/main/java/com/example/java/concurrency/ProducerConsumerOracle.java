
package com.example.java.concurrency;

import java.util.Random;

/**
 * This example is taking from Java tutorial from Oracle website.
 * 
 * @author riazuddin
 */
public class ProducerConsumerOracle {

    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }

    static class Drop {
        private String message;
        
        // True if consumer should wait
        // for producer to send message,
        // false if producer should wait for
        // consumer to retrieve message.
        private boolean empty = true;

        public synchronized void put(String message) {
            // Wait until message has
            // been retrieved.
            while (!empty) {
                try {
                    wait();
                }
                catch (InterruptedException e) {}
            }
            // Toggle status.
            empty = false;
            // Store message.
            this.message = message;
            
            // Notify consumer that status
            // has changed. notifies all threads waiting for the lock.
            notifyAll();
        }

        public synchronized String get() {
            // Wait until message is
            // available.
            while (empty) {
                try {
                    wait();
                }
                catch (InterruptedException e) {}
            }
            // Toggle status.
            empty = true;
            // Notify producer that
            // status has changed.
            notifyAll();
            return message;
        }
    }

    static class Producer implements Runnable {

        private Drop drop;
        private String[] messages = new String[] { "Riaz", "Uzair", "Khadeejah", "Manha" };

        public Producer(Drop drop){
            this.drop = drop;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (int i = 0; i < messages.length; i++) {
                drop.put(messages[i]);
                try {
                    Thread.sleep(random.nextInt(5000));
                }
                catch (InterruptedException e) {}
            }

            drop.put("DONE");
        }

    }

    static class Consumer implements Runnable {

        private Drop drop;

        public Consumer(Drop drop){
            this.drop = drop;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (String message = drop.get(); !message.equals("DONE"); message = drop.get()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                try {
                    Thread.sleep(random.nextInt(5000));
                }
                catch (InterruptedException e) {}
            }
            System.out.println("Consumer is done");
        }

    }
}
