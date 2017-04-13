package com.example.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

@Component
public class PhoneServiceBean implements PhoneService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private Validator validator;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Contact findContact(String name) {
		return contactRepository.findByName(name);
	}

	@Override
	@Transactional
	public Contact addContact(String name, String phone) {
		return contactRepository.save(new Contact(name, phone));
	}

	@Override
	@Transactional
	public Contact updateContact(Contact c) {
		return contactRepository.save(c);
	}
	
	@PostConstruct
	private void postConstruct(){
//		System.out.println("@PostConstruct: "+this.getClass().getName());
	}

	@PreDestroy
	private void preDestroy(){
//		System.out.println("@PreDestroy: "+this.getClass().getName());
	}
}
