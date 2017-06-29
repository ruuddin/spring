package com.example.memcache;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class MemcachedJava {
	public static void main(String[] args) {
		try {
			// Connecting to Memcached server on localhost
			MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			System.out.println("Connection to server sucessful.");
			
			//Ex: 1 set()
			//set data into memcached server with expiry 900ms
			Future fo = mcc.set("obj_key", 900, "Free Education");
			System.out.println("set status: "+fo.get());
			
			//retrieve value
			System.out.println("Get key: "+mcc.get("obj_key"));
			
			//Ex: 2 add() -- command to set the key to new value. If key already exists it returns false, else true
			fo = mcc.add("obj_key", 900, "memcached");
			System.out.println("add status: "+fo.get());
			// adding a new key to memcached server
	        fo = mcc.add("codingground", 900, "All Free Compilers");
	        // print status
	        System.out.println("add status:" + fo.get());
	        // retrieve and check the value from cache
	        System.out.println("codingground value in cache - " + mcc.get("codingground"));
			
	        //Ex 3: replace() - replace the value of existing key. Returns false if key does not exist.
	        fo = mcc.replace("obj_key", 900, "Largest Tutorials' Library");
	        System.out.println("replace status:" + fo.get());
	        System.out.println("obj_key value in cache - " + mcc.get("obj_key"));
	        
	        //Ex 4: append() - add data to existing key.
	        fo = mcc.append("obj_key", " for All");
	        System.out.println("append status:" + fo.get());
	        System.out.println("obj_key value in cache - " + mcc.get("obj_key"));
	        
	        //Ex 5: prepend() - add data before existing key's value.
	        fo = mcc.prepend("obj_key", "Free ");
	        System.out.println("prepend status:" + fo.get());
	        System.out.println("obj_key value in cache - " + mcc.get("obj_key"));
			
			//Ex 6: CAS - Check and Set/Compare and Swap - checks and set data iff no other client process has updated
			//it since last read by this client. Memcache assigns unique 64-bit CAS token to all items stored.
			// obtain CAS token value using gets method
	        CASValue casValue = mcc.gets("obj_key");
	        System.out.println("CAS token - " + casValue);
	        // try to update data using memcached cas method
	        CASResponse casresp = mcc.cas("obj_key", casValue.getCas(), 900, "Largest Tutorials-Library");
	        // display CAS Response
	        System.out.println("CAS Response - " + casresp);
	        // retrieve and check the value from cache
	        System.out.println("obj_key value in cache - " + mcc.get("obj_key"));

	        //Ex 7: delete() - delete data from memcache server
	        fo = mcc.delete("obj_key");
	        System.out.println("delete status:" + fo.get());
	        System.out.println("obj_key value: "+mcc.get("obj_key"));
	        
	        //Ex 8: increment()/decrement()
	        fo = mcc.set("number", 900, "1000");
	        System.out.println("set status:" + fo.get());
	        System.out.println("value in cache after increment - " + mcc.incr("number", 111));
	        System.out.println("value in cache after decrement - " + mcc.decr("number", 112));
	        
	        //Ex 9: stats - returns server statistics PID, version, connections etc.
	        System.out.println("Memcached Statistics - " + mcc.getStats());
	        
	        //Ex 10: flush() - clear all data
	        System.out.println("Clear data:" + mcc.flush().isDone());
	        
	        // Shutdowns the memcached client
 			mcc.shutdown();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
