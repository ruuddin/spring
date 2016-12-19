
package com.caching;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.spring.Contact;
import com.training.spring.ContactRepository;

@Component
public class ContactCacheLoader implements CacheLoader<Integer, Contact> {

    private static final Log log = LogFactory.getLog(ContactCacheLoader.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact load(Integer key) throws CacheLoaderException {
        log.info("Load key " + key);
        return contactRepository.findOne(key);
    }

    @Override
    public Map<Integer, Contact> loadAll(Iterable<? extends Integer> keys)
            throws CacheLoaderException {
        log.info("Load all keys " + keys);
        Iterable<Contact> contacts = contactRepository.findAll((Iterable<Integer>)keys);
        return StreamSupport.stream(contacts.spliterator(), false)
                .collect(Collectors.toMap(Contact::getId, Function.identity()));
    }

}
