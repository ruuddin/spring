package com.example.ribbon;

import com.example.ribbon.client.FeignClientExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RoomReservationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignClientExample ribbonClient;

    //IMPORTANT: add / at the end in browser to see the response, else it return 404
    @GetMapping("/ribbon-greeting")
    public String greeting() {
        ResponseEntity<String> response = this.restTemplate
            .exchange("http://EUREKA-CLIENT-EXAMPLE/greeting",
                      HttpMethod.GET,
                      null,
                      new ParameterizedTypeReference<String>() {});

        return response.getBody();
    }

    @GetMapping("/feign-client-greeting")
    public String feignGreeting() {
        return this.ribbonClient.greeting();
    }
}
