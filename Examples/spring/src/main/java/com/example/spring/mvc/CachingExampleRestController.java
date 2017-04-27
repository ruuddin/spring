package com.example.spring.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.cache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.Contact;
/**
 * Manually handling JCache. For maintaining a cache through annotations see {@link AnnotationCachingController}
 * @author riazuddin
 *
 */
@RestController
@RequestMapping(value= "/jcache/contacts", produces = "application/json")
public class CachingExampleRestController {
    
    private static final Log log = LogFactory.getLog(CachingExampleRestController.class);
    
    private Cache<Integer, Contact> contactCache;
    
    @Autowired
    public CachingExampleRestController(Cache<Integer, Contact> contactCache){
        this.contactCache = contactCache;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contact get(@PathVariable("id") Integer id) {
        log.info("get for id "+id);
        return contactCache.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes= "application/json")
    public boolean update(@RequestBody List<Contact> contacts) {
        log.info("put with contact "+ contacts);
        Map<Integer, Contact> contactsMap = new HashMap<>();
        
        for(Contact c : contacts)
            contactsMap.put(c.getId(), c);
        
        contactCache.putAll(contactsMap);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, consumes= "application/json")
    public boolean add(@RequestBody List<Contact> contacts) {
        log.info("post with contact "+ contacts);
        Map<Integer, Contact> contactsMap = new HashMap<>();
        
        for(Contact c : contacts)
            contactsMap.put(c.getId(), c);
        
        contactCache.putAll(contactsMap);
        return true;
    }
}
