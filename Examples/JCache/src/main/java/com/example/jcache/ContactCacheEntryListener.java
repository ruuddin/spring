
package com.example.jcache;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.spring.Contact;

public class ContactCacheEntryListener implements CacheEntryCreatedListener<Integer, Contact>,
        CacheEntryUpdatedListener<Integer, Contact>, CacheEntryExpiredListener<Integer, Contact>,
        CacheEntryRemovedListener<Integer, Contact> {

    private static final Log log = LogFactory.getLog(ContactCacheEntryListener.class);

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends Integer, ? extends Contact>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(entry -> log.info("Cache entry removed, for key " + entry.getKey()
        + " new value: " + entry.getValue() + " old value: " + entry.getOldValue()));
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends Integer, ? extends Contact>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(entry -> log.info("Cache entry expired, for key " + entry.getKey()
        + " new value: " + entry.getValue() + " old value: " + entry.getOldValue()));

    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends Integer, ? extends Contact>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(entry -> log.info("Cache entry updated, for key " + entry.getKey()
        + " new value: " + entry.getValue() + " old value: " + entry.getOldValue()));
    }

    @Override
    public void onCreated(
            Iterable<CacheEntryEvent<? extends Integer, ? extends Contact>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(entry -> log.info("Cache entry created, for key " + entry.getKey()
                + " new value: " + entry.getValue() + " old value: " + entry.getOldValue()));
    }

}
