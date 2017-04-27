package com.example.java.generics;

import java.io.Serializable;

public class GenericBox<T> {

    private T t;
    
    public void set(T t) {this.t = t;}
    public T get() { return t;}
    
    public <U extends N, N> void calculateA(U u){
        System.out.println(u);
    }
    
    public <U extends Number> void calculateB(U u){
        System.out.println(u.byteValue());
    }
    
    public <U extends Number & Serializable & Runnable> void multipleBounds(U u){
        System.out.println(u.byteValue());
        u.run();
    }
    
    /**
     * Generic method example
     * 
     * @param key
     * @param value
     * @return
     */
    public static <K,V> boolean genericMethod(K key, V value) {
        return false;
    }
    
    public static <K,V> boolean genericMethod2(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey());
    }
    
    public static void main(String[] args) {
        
        //Generic method invocation example
        GenericBox.genericMethod(10, "yes");
        
        Pair<Integer, Integer> p1 = new Pair<>(10, 10);
        Pair<Integer, Integer> p2 = new Pair<>(10, 20);
        
        GenericBox.genericMethod2(p1, p2);
    }
}

class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}