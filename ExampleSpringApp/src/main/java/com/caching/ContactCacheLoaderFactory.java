
package com.caching;

import javax.cache.configuration.Factory;

import com.training.spring.ApplicationContextSingleton;

public class ContactCacheLoaderFactory implements Factory<ContactCacheLoader> {

    @Override
    public ContactCacheLoader create() {
        return ApplicationContextSingleton.getApplicationContext()
                .getBean(ContactCacheLoader.class);
    }

}
