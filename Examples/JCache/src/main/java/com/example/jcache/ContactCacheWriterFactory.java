package com.example.jcache;

import javax.cache.configuration.Factory;

import com.example.spring.ApplicationContextSingleton;

public class ContactCacheWriterFactory implements Factory<ContactCacheWriter> {

    @Override
    public ContactCacheWriter create() {

        return ApplicationContextSingleton.getApplicationContext().getBean(ContactCacheWriter.class);
    }

}
