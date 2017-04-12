package com.spring.webservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@Configuration
@EnableWs
public class SpringWebServiceConfig {

    @Bean
    public WebServiceTemplate webServiceTemplate(){
        
        WebServiceTemplate wsTemplate = new WebServiceTemplate();
        wsTemplate.setDefaultUri("http://localhost:8080/WebService");
        return wsTemplate;
    }
    
    //Also see spring-servlet.xml -> static-wsdl
    @Bean
    public SimpleWsdl11Definition holiday() {
        return new SimpleWsdl11Definition(new ClassPathResource("holiday.wsdl"));
    }
    
    //Commented this out because the dynamic-wsdl version declared in spring-servlet.xml outputs clear
    /*
     * 
    @Bean
    public DefaultWsdl11Definition holiday() {//bean id = holiday
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

        definition.setPortTypeName("HumanResource");
        definition.setLocationUri("/holidayService/");
        definition.setTargetNamespace("http://mycompany.com/hr/definitions");
        definition.setSchemaCollection(new CommonsXsdSchemaCollection(new Resource[] { new ClassPathResource("/hr.xsd")}));

        return definition;
    }*/
}
