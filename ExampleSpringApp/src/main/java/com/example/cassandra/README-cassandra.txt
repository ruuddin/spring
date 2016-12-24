Download Cassandra
Start Cassandra bin\cassandra.bat
start cqlsh: bin\cqlsh.bat

http://docs.datastax.com/en/developer/java-driver/3.1/manual/
CassandraConfig
	- Cluster is main entry point of the driver. Thread-safe. Create a single instance per target Cassandra cluster, and share it throughtout the application.
		closing a cluster also closes the sessions. Do this at shutdown.
	- Session is used to execute queries. thread-safe and should be reused. By default, a session isn’t tied to any specific keyspace. You’ll need to prefix table names in your queries. 
		You can also specify a keyspace name at construction time, it will be used as the default when table names are not qualified.
		Connection pools are created at the session level, so each new session will consume additional system resources.
		Changing default keyspace: session.execute("USE myKeyspace"); Be very careful though: if the session is shared by multiple threads, 
		switching the keyspace at runtime could easily cause unexpected query failures.
	- Connection pool
		- For each Session, there is one connection pool per connected host (a host is connected when it is up and not ignored by the load balancing policy).
		- Most connection pooling options can be configured during runtime. ex: cluster.getConfiguration().getPoolingOptions()
		- When activity goes up and there are n connections with n < max, the driver will add a connection when the number of concurrent 
			requests is more than (n - 1) * 128 + PoolingOptions.setNewConnectionThreshold (in layman’s terms, when all but the last connection are full 
			and the last connection is above the threshold). When activity goes down, the driver will “trash” connections if the maximum number 
			of requests in a 10 second time period can be satisfied by less than the number of connections opened. Trashed connections are kept open 
			but do not accept new requests. After a given timeout (defined by PoolingOptions.setIdleTimeoutSeconds), trashed connections are closed and 
			removed. If during that idle period activity increases again, those connections will be resurrected back into the active pool and reused. The main 
			intent of that is to not constantly recreate connections if activity changes quickly over an interval.
	- Custom codecs allow mapping from CQL types to Java types (See JsonCodec.java)
		- codecs should be thread-safe, fast and never block.
		- Register custom codecs using CodecRegistry. Once registered, it cannot be overridden/deregistered, can only add new ones.
		