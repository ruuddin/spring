package com.example.java.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class LockExample {
    ReentrantLock rl = new ReentrantLock(); //same as synchronized block, with some additional methods.
    int count = 0;
    
    ReadWriteLock rwl = new ReentrantReadWriteLock();
    Map<String, String> map = new HashMap<>();
    
    public static void main(String[] args) {
        LockExample test = new LockExample();
//        test.readWriteLock();
//        test.stampedLock();
        test.semaphore();
    }
    
    /**
     * Semaphore is useful when multiple threads can acquire locks concurrently. Other locks give exclusive access to variables or resources,
     *  but semaphore can maintain a set of permits to allow concurrent access to threads.
     */
    private void semaphore(){
        ExecutorService es = Executors.newFixedThreadPool(10);
        Semaphore s = new Semaphore(5); //no. of threads that can concurrently access the code.
        Runnable longTask = () -> {
            boolean permit = false;
            try {
                permit = s.tryAcquire(1, TimeUnit.SECONDS); // try to acquire a lock, and timeout in
                                                            // 1s
                if (permit) {
                    System.out.println("Semaphore acquired " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
                else {
                    System.out
                            .println("Semaphore not available " + Thread.currentThread().getName());
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                s.release();
            }
        };
        IntStream.range(1, 20).forEach(i -> es.submit(longTask));
        es.shutdown();
    }
    
    StampedLock sl = new StampedLock();
    /**
     * StampedLock - locking methods return a stamp as long value, used to release a lock or check if its still valid.
     *  dont implement reentrant locking. Each call to lock returns a new stamp and blocks if no lock is available, even
     *  if the same thread already holds the lock.
     *  
     *  Optimistic read - tryOptimisticRead() returns a stamp without blocking the current thread, no matter if the lock is actually available.
     *      Does not prevent other threads to obtain write lock, compared to other lock types. The write lock will be given without waiting for the 
     *      optimistic read lock to be released. From this point the optimistic read lock becomes invalid. Therefore, after reading mutable shared 
     *      variable, always check if the stamp was valid.
     *      If the write lock is active, tryOptimisticRead() returns 0.
     *  
     */
    private void stampedLock(){
        ExecutorService e = Executors.newFixedThreadPool(2);
        Runnable write = () -> {
            long stamp = sl.writeLock();
            System.out.println("Writing "+stamp);
            try {
                Thread.sleep(3000);
                map.put("sl", "" + stamp);
            }
            catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            finally {
                sl.unlockWrite(stamp);
            }
        };
        Runnable read = () -> {
            long stamp = sl.readLock();
            try {
                System.out.println("Read "+map.get("sl"));
            }
            finally {
                sl.unlockRead(stamp);
            }
        };

        Runnable optimisticRead = () -> {
            long stamp = sl.tryOptimisticRead();
            try {
                boolean valid = sl.validate(stamp);
                System.out.println("Read Valid? "+valid);
                if(valid)
                    System.out.println("OptimisticRead "+map.get("sl"));
            }
            finally {
                sl.unlockRead(stamp);
            }
        };
        
        e.submit(write);
        e.submit(read);
        e.submit(read);
        e.submit(optimisticRead);
        e.submit(write);
        e.submit(optimisticRead);
        e.shutdown();
    }
    
    /**
     * If a thread has acquired the lock, subsequect calls by another thread are blocked until the lock is released.
     * Only one thread can hold the lock at anytime.
     * 
     */
    private void reentrant(){
        
        rl.lock();
        try{
            count++;
        }finally{
            rl.unlock();
        }
    }
    
    /**
     * All reader threads are blocked until writing is complete. Read tasks can run in parallel as long as no write locks are held.
     */
    private void readWriteLock(){
        ExecutorService e = Executors.newFixedThreadPool(2);
        Runnable write = () -> {
            rwl.writeLock().lock();
            try{
                long t = System.currentTimeMillis();
                System.out.println("Writing "+t);
                Thread.sleep(4000);
                map.put("one", ""+t);
            }
            catch (InterruptedException e1) {
                e1.printStackTrace();
            }finally{
                rwl.writeLock().unlock();
            }
        };
        
        Runnable read = () -> {
            rwl.readLock().lock();
            try{
                System.out.println("Read "+System.currentTimeMillis());
                System.out.println(map.get("one"));
            }finally{
                rwl.readLock().unlock();
            }
        };
        
        e.execute(write);
        e.execute(read);
        e.execute(read);
        e.execute(write);
        e.execute(read);
        e.execute(read);
        
        e.shutdown();
    }
}
