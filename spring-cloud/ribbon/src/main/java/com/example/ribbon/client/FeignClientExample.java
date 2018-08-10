package com.example.ribbon.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="EUREKA-CLIENT-EXAMPLE")
public interface FeignClientExample {

    @GetMapping("/greeting")
    String greeting();
}
