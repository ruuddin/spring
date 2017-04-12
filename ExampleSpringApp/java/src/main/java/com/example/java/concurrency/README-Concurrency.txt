Synchronization
	Options to Synchronize code
	- synchronizing static method of a class C creates a lock on C.class object
	- synchronizing non static method of C creates a lock on that object of C.
	- Best option: Good idea to hide the object used for synchronization (static or not). Dedicated object to synchronize.
		See SynchronizeObject.java
		RaceCondition01.java: Resolved Race condition - if the lock is not placed, then the threads from main method will overwrite
			each others data, thus resulting in an incorrect value. The block of code in getValue() is synchronized to satisfy happens-before
			condition between synchronized read and writes. If this is removed, then there will be concurrency issues in production env, 
			something not reproducible in dev EclipseIDE.

	Synchronizing more that one method: To synchronize both methodA and methodB in a class C such that ThreadA can 
		access methodA and ThreadB can access methodB at the same time, create separate lock objects 
		for each method. By not doing so, the lock will be created on the instance of C and only one 
		thread will be able to access a synchronized method at a time.
	
	Reentrant lock: If a thread holds a lock, it can enter a block synchronized on the lock it is holding. 
	Deadlock: Situation where a thread T1 holds a key needed by T2, and T2 holds the key needed by T1.

Stopping a thread: Call interrupt() method, and then check for Thread.currentThread.isInterrupted() in the method logic.

Producer/Consumer pattern: Producer produces values in buffer, Consumer consumes values from this buffer. There can be more than
	one producers/consumers. Producers and Consumers run in their own threads, therefore synchronization is important.
	ProducerConsumer.java: Correct way of implementing a producer and consumer trying to read and write from the same buffer.