Spring

	REST
	- tested from eclipse: Create a tomcat server, deploy and start server
	- URL: http://<host>/ExampleSpringApp/phonebookapp/
	- Add new contact through a REST client: http://localhost:8080/ExampleSpringApp/phonebookapp/add?name=Riaz&phone=9014135046

	Repositories
	- Annotated using @Repository (see ContactRepository)
	- @EnableJpaRepository in ApplicationConfiguration to configure repositories in a package
	- getByName method - In built query creation of Spring. Just name the property by which to query the table.
	JMX
	- Add @EnableMBeanExport to ApplicationConfiguration
	- See com.caching.AuditManager
	- Open JConsole -> Caching to view the managed bean.
