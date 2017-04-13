
package com.example.jcache;

import javax.cache.Cache;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import com.example.spring.Contact;


@ManagedResource(objectName = "Caching:bean = AuditManager")
@Service
public class AuditManager {

    private MutableCacheEntryListenerConfiguration<Integer, Contact> config;

    private Cache<Integer, Contact> cache;

    public AuditManager(
            MutableCacheEntryListenerConfiguration<Integer, Contact> config,
            Cache<Integer, Contact> cache){
        this.config = config;
        this.cache = cache;
    }
    
    @ManagedOperation
    public void startAudit(){
        cache.registerCacheEntryListener(config);
    }
    
    @ManagedOperation
    public void stopAudit(){
        cache.deregisterCacheEntryListener(config);
    }
}
