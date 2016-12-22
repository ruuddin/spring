http://docs.spring.io/spring-ws/docs/2.4.0.RELEASE/reference/htmlsingle/#what-is-spring-ws

- Requires Java 7 atleast. Spring 4.0.9+
- contract-first SOAP service development (Create WSDL first)
- powerful mapping: can distribute incoming XML to any object, depending on message payload, SOAP action header, or XPath expression
- XML api support: XML messages can be handled by JAXP API such as DOM, SAX, StaX, JDOM, dom4j, XOM
- WS-Security: sign SOAP messages, encrypt and decrypt them or authenticate them.

Add package to component scan in ApplicationConfiguration
Add MessageDispatcherServlet in web.xml
Add transformWsdlLocations context init param in web.xml
Add sws namespace to spring-servlet.xml
Adding endpoint: Annotate class with @Endpoint, this makes it suitable for handling XML messages and
	 a special @Component eligible for spring component scanning.
@PayloadRoot (see HolidayEndpoint.handleHolidayRequest) is suitable for handling xml messages. HolidayRequest elements from the incoming 
	message is passed to this method.
@RequestPayload annotation indicates that the holidayRequest parameter should be mapped to the payload of the request message	