package com.example.spring.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.Contact;
import com.example.spring.PhoneService;

/**
 * @Controller or @RestController can be used interchangebly.
 * @ResponseBody can be skipped if @RestController is used.
 * 
 * @author riazuddin
 *
 */
@Controller
@RequestMapping("/phonebookapp")
public class PhoneBookRestController {

	@Autowired
	private PhoneService phoneServiceBean;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Contact> getPhoneBook() {
		return phoneServiceBean.getAllContacts();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Iterable<Contact> addContact(@RequestParam("name") String name,
			@RequestParam("phone") String phone, Model model) {
		System.out.println("Adding: " + name + ", " + phone);
		phoneServiceBean.addContact(name, phone);
		
		return getPhoneBook();
	}

	/**
	 * An example of generating xML content. The xml is available at /contacts
	 * and /contacts.xml
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contacts")
	public String contactsAsXml(Model model) {
		model.addAttribute(phoneServiceBean.findContact("Uddin"));

		return "marshallingView";
	}

	/**
	 * An alternative to contactsAsXml method. It does not require declaring
	 * view resolvers, marshallingView bean and jaxb marshaller. Just annotating
	 * the Contact class is enough.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/xmlcontacts", produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public Contact contactsAsXml() {
		return phoneServiceBean.findContact("Uddin");
	}

	@RequestMapping(value = "/jsoncontacts", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<Contact> getAllAsJson() {
		return phoneServiceBean.getAllContacts();
	}
	
	@RequestMapping(value = "/jsoncontact/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Contact getContactAsJson(@PathVariable("name") String name) {
		return phoneServiceBean.findContact(name);
	}
}