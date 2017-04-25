package com.example.java.concurrency;

//implementing Runnable
public class RunnableThread implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }
    
    public static void main(String args[]){
        (new Thread(new RunnableThread())).start();
        (new HelloThread()).start();
    }
    
    //extending Thread class
    static class HelloThread extends Thread{
        public void run(){
            System.out.println("Hello from HelloThread!");
        }
    }
}
