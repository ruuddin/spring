Basics
	RunnableThread.java: creating thread using Runnable and Thread.
	Interrupt - indication for a thread to stop what it is doing and do something else.
	Atomic access: Atomic actions cannot be interleaved. so they can be used without fear for thread interference. very simple expressions can define 
	complex actions that can decompose into other actions. These are not atomic. Ex - a++.
		Reads and writes are atomic for reference variables and for most primitive variables (except long and double)
		Reads and writes are atomic for all variables declared volatile. threads will always see the latest value of the variable.
		Since Java 5, AtomicInteger and AtomicLong are provided which have atomic methods getAndDecrement(), getAndIncrement() and getAndSet()
	variable declared with volatile guarantees that any thread that reads the field will see the most recent value. it does not do any locking on the variable.
	Concurrency issues: visibiliy and access problems.
		visibiliy issue occurs if thread A reads shared data which is later changed by thread B, and thread A is unaware of this change.
		access problem can occur if several threads access and change the same shared data at the same time.
		These problems can lead to 
			liveness failure: program does not react anymore due to problems in the concurrent access of data
			safety failure: program creates incorrect data.
		
Synchronization
	Interference: two threads acting on same data, interleave.
	memory consistency errors: different threads have inconsistent views of whaat should be the same data.
	Intrinsic locks (monitors) enforce exclusive access to an object's state and establish happens-before relationships. Every object has an intrinsic lock.
	when thread invokes synchronized method, it acquires instrinsic lock for that method's object and releases it when the method returns.
	
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
	Starvation: a thread is not able to gain access to shared resources and is unable to make progress.

Stopping a thread: Call interrupt() method, and then check for Thread.currentThread.isInterrupted() in the method logic.

Producer/Consumer pattern: Producer produces values in buffer, Consumer consumes values from this buffer. There can be more than
	one producers/consumers. Producers and Consumers run in their own threads, therefore synchronization is important.
	ProducerConsumer.java: Correct way of implementing a producer and consumer trying to read and write from the same buffer.

Executors: (ExecutorsExample01.java) capable of running asynchronous tasks and typically manage a pool of threads, so we dont have to create new threads manually.
	provides factory methods of different kinds. Executors have to be stopped explicitly. shutdown() and shutdownNow()
ExecutorService: Higher level replacement for working with threads directly.
Callables are functional interfaces just like Runnable but they return a value. the executor service cannot return the result of the callable directly. Instead the 
	executor returns a special result of type Future
Future can be used to retrieve the actual result at a later point in time. any call to future.get() will block and wait until the callable has been terminated. 
	If the callable runs forever, future.get(1, TimeUnit.SECONDS) can be used as a timeout. Every non terminated future will throw exception if executor is shutdown.