package com.caching;

import javax.cache.configuration.Factory;

import com.training.spring.ApplicationContextSingleton;

public class ContactCacheWriterFactory implements Factory<ContactCacheWriter> {

    @Override
    public ContactCacheWriter create() {

        return ApplicationContextSingleton.getApplicationContext().getBean(ContactCacheWriter.class);
    }

}
