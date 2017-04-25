package com.example.java.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample01 {

    public static void main(String[] args) throws Exception {
        executor();
        callableAndFuture();
    }

    private static void executor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
           String tName = Thread.currentThread().getName();
           System.out.println("Hello "+tName);
        });
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally{
            if(!executor.isTerminated())
                System.err.println("cancel non-finished tasks");
            
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
    
    public static void callableAndFuture() throws InterruptedException, ExecutionException{
        Callable<Integer> task = () -> {
            try{
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }catch(InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        };
        
        ExecutorService e = Executors.newFixedThreadPool(1);
        Future<Integer> f = e.submit(task);
        
        System.out.println("future done? "+f.isDone());
        Integer result = f.get(); //blocking method
        
        System.out.println("future done? "+f.isDone());
        System.out.println("result "+result);
        
        e.shutdown();
    }
}
