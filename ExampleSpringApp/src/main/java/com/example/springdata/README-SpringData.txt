Spring Data
1. define a domain class-specific repository interface. The interface must extend Repository and be typed to the domain class and an ID type. 
	If you want to expose CRUD methods for that domain type, extend CrudRepository instead of Repository. 
	Typically, your repository interface will extend Repository, CrudRepository or PagingAndSortingRepository.
	if you do not want to extend Spring Data interfaces, you can also annotate your repository interface with @RepositoryDefinition.
	If you prefer to be selective about the methods being exposed, simply copy the ones you want to expose from CrudRepository into your domain repository.
2. Annotate the Configuration class with @EnableJpaRepositories(basePackages="..."). Base packages define the starting points for scanning for repository interface definitions 
	which implies to have repository definitions located in the appropriate packages. By default, annotation-driven configuration uses the package of 
	the configuration class. Each bean is registered under a bean name that is derived from the interface name, 
	so an interface of UserRepository would be registered under userRepository. 
3. Query lookup strategies
	- CREATE_IF_NOT_FOUND (default lookup strategy): combines CREATE and USER_DECLARED_QUERY.
	- CREATE: attempts to construct a store-specific query from query method name. See ContactRepository.findByName
	- USE_DECLARED_QUERY: tries to find a declared query and iwll throw an exception in case it cant find one. Query can be defined by an annotation somewhere or
		declared by other means.
	- The results of query methods can be limited via the keywords first or top, which can be used interchangeably. An optional numeric value 
		can be appended to top/first to specify the maximum result size to be returned. 
		If the number is left out, a result size of 1 is assumed. Ex: List<User> findFirst10ByLastname(String lastname, Sort sort);