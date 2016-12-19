package com.caching;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.cache.Cache;
import javax.cache.Cache.Entry;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.spring.Contact;
import com.training.spring.ContactRepository;

@Component
public class ContactCacheWriter implements CacheWriter<Integer, Contact>{
    
    private static final Log log = LogFactory.getLog(ContactCacheWriter.class);

    private ContactRepository contactRepository;
    
    @Autowired
    public ContactCacheWriter(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    @Override
    public void delete(Object key) throws CacheWriterException {
        log.info("delete "+key);
        contactRepository.delete((Integer)key);
    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {
        log.info("deleteAll "+keys);
        contactRepository.deleteAll((Collection<Integer>)keys);
    }

    @Override
    public void write(Entry<? extends Integer, ? extends Contact> entry)
            throws CacheWriterException {
        log.info("write "+entry);
        contactRepository.save(entry.getValue());
    }

    @Override
    public void writeAll(Collection<Entry<? extends Integer, ? extends Contact>> entries)
            throws CacheWriterException {
        log.info("writeAll "+entries);
        Set<Contact> contacts = entries.stream().map(Cache.Entry::getValue).collect(Collectors.toSet());
        contactRepository.save(contacts);
    }

}
