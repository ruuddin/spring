package com.example.jcache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.spring.Address;
import com.example.spring.Contact;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.example.jcache", "com.example.spring" })
@EnableMBeanExport
@EnableTransactionManagement
@EnableCaching
public class ApplicationConfiguration {

    // Begin: JCache beans
    @Bean
    public CachingProvider createCachingProvider() {
        return Caching.getCachingProvider();
    }

    @Bean
    public CacheManager createCacheManager(CachingProvider cachingProvider) {
        return cachingProvider.getCacheManager();
    }
    
    @Bean(name = "contactCacheConfig")
    public MutableConfiguration<Integer, Contact> createContactCacheConfig(
            MutableCacheEntryListenerConfiguration<Integer, Contact> listenerConfig) {
        return new MutableConfiguration<Integer, Contact>()
                // @EnableConfig does not like setTypes
                // .setTypes(Integer.class, Contact.class)
                .setCacheWriterFactory(new ContactCacheWriterFactory()).setWriteThrough(true)
                .setCacheLoaderFactory(new ContactCacheLoaderFactory()).setReadThrough(true)
                .setStatisticsEnabled(true)
                .setManagementEnabled(true)
        // .addCacheEntryListenerConfiguration(listenerConfig) //permanent cache listener, other
        // option is to configure through JMX (on demand)
        ;
    }

    @Bean(name = "contactCache")
    public Cache<Integer, Contact> createContactCache(CacheManager cm,
            @Qualifier("contactCacheConfig") MutableConfiguration<Integer, Contact> config) {
        return cm.createCache("contacts", config);
    }

    @Bean
    public MutableCacheEntryListenerConfiguration<Integer, Contact> cacheEntryListener() {
        return new MutableCacheEntryListenerConfiguration<>(
                FactoryBuilder.factoryOf(ContactCacheEntryListener.class), null, true, true);
    }

    @Bean
    public JCacheCacheManager cacheCacheManager(CacheManager cacheManager) {
        return new JCacheCacheManager(cacheManager); // Spring specific JCacheCacheManager
    }

    @Bean(name = "addressesCacheConfig")
    public MutableConfiguration<Integer, Address> createAddressesCacheConfig() {
        MutableConfiguration<Integer, Address> config = new MutableConfiguration<Integer, Address>()
                .setStatisticsEnabled(true)
                .setManagementEnabled(true);
        return config;
    }

    @Bean(name = "addressesCache")
    public Cache<Integer, Address> createAddressesCache(CacheManager cacheManager,
            @Qualifier("addressesCacheConfig") MutableConfiguration<Integer, Address> config) {
        return cacheManager.createCache("addresses", config);
    }
    // End: Jcache beans
}
