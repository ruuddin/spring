package com.training.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.training.spring");

		PhoneService phoneService = (PhoneService)ctx.getBean("phoneServiceBean");
		
		System.out.println("Adding new contact:");
		phoneService.addContact("Riaz Uddin", "901-333-4546");
		
		Iterable<Contact> contacts = phoneService.getAllContacts();
		
		System.out.println("Listing contacts: ");
		for(Contact entry : contacts)
			System.out.println("\tName: "+entry.getName()+" , Phone: "+entry.getPhone());
		
		((AbstractApplicationContext)ctx).close();
	}
}
