package com.example.java.concurrency;

public class Test implements Runnable{

    private int i;
    public Test(int i){
        this.i = i;
    }
    public void waiting(){
        try {
            Thread.sleep(1000 * i);
            System.out.println(i);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        waiting();
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i =0 ; i < 100; i++){
            (new Thread(new Test(i+1))).start();
        }
    }
}
