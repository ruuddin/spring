JCache
	- Get a contact: http://<host>/ExampleSpringApp/jcache/contacts/{id}
	- Add new contact: http://<host>/ExampleSpringApp/jcache/contacts (POST request)
		JSON message: [{ "name": "Riaz", "phone": "9010000000", "dob": "20/06/1980", "id": 1 }, { "name": "Uddin", "phone": "9019999999", "dob": "20/06/1980","id":2 }]
	- Update contact (PUT request - change some properties of contact):
		JSON message: [{ "name": "Riaz", "phone": "9010000000", "dob": "20/06/1980", "id": 1 }, { "name": "Uddin", "phone": "9019999999", "dob": "20/06/1980","id":2 }]
	Testing JCache
		Create few contacts with POST
		Do some gets. Result: Calls dont go to DB. See the logs
		Update with PUT. Result: Call goes thru cache to the DB.
		Do some gets. Result: Calls dont go to DB.
		Restart the app.
		Do some gets. Result: First call to an object goes to DB. Same call does not go to DB.
	- Listener (See ContactCacheEntryListener): Listen to CRUD events on the cache.
		See AuditManager: To start or stop CacheListener through JMX (runtime)
		Open JConsole -> Caching to view the managed bean (startAudit() to start listening, stopAudit() to stop listening)
	- JCache annotations (Spring specific): see Address class and its related configurations in ApplicationConfiguration
		No cache writers or loaders defined
		AnnotationCachingController: @CacheDefault, @CachePut(@CacheKey, @CacheValue), @CacheResult(look into cache first bfore going to db)
	- SetStatistics (setStatisticsEnabled), Enable management (setManagementEnabled):
		Open JConsole -> javax.caching -> Cache Statistics -> urn... -> addresses -> Attributes
		Reset statistics: .... addresses -> Operations -> clear
		Check for different statistics for hists and misses. Run some GET and POSt requests, refresh the JConsole to view the changed statistics.