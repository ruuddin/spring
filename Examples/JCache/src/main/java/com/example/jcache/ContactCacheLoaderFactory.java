
package com.example.jcache;

import javax.cache.configuration.Factory;

import com.example.spring.ApplicationContextSingleton;

public class ContactCacheLoaderFactory implements Factory<ContactCacheLoader> {

    @Override
    public ContactCacheLoader create() {
        return ApplicationContextSingleton.getApplicationContext()
                .getBean(ContactCacheLoader.class);
    }

}
