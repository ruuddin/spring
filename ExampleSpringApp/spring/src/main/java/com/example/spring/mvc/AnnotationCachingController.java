package com.example.spring.mvc;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.Address;
import com.example.spring.AddressRepository;

/**
 * Using JCache through annotations. For manually maintaining a cache see {@link CachingExampleRestController}
 * @author riazuddin
 *
 */
@RestController
@RequestMapping(value = "/jcache/addresses", produces = "application/json")
@CacheDefaults(cacheName = "addresses")
public class AnnotationCachingController {

    private AddressRepository addressRepository;
    
    public AnnotationCachingController(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    
    @RequestMapping(value = "/{id}", consumes = "application/json", method = RequestMethod.POST)
    @CachePut
    public boolean post(@PathVariable("id") 
        @CacheKey
        Integer id, 
        @RequestBody 
        @CacheValue
        Address address) throws Exception {
        addressRepository.save(address);
        return true;
    }
    
    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    @CacheResult
    public Address get(@PathVariable Integer addressId) throws Exception {
        return addressRepository.findOne(addressId);
    }
    
    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
    @CacheRemove
    public void delete(@PathVariable Integer addressId) throws Exception {
        Address address = addressRepository.findOne(addressId);
        addressRepository.delete(address);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    @CacheRemoveAll
    public void deleteAll() throws Exception {
        addressRepository.deleteAll();
    }
    
    
}
