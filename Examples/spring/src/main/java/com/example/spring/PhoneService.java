package com.example.spring;

public interface PhoneService {
	public Iterable<Contact> getAllContacts();

	public Contact findContact(String name);

	public Contact addContact(String name, String phone);

	public Contact updateContact(Contact c);

}
