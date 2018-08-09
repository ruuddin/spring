package com.example.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rooms")
@Api(value = "rooms", description = "Data service operations on rooms", tags = "rooms")
public class RoomController {

    private RoomServices roomServices;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public RoomController(RoomServices roomServices) {
        super();
        this.roomServices = roomServices;
    }

    @GetMapping
    @ApiOperation(value = "Get all rooms in the system", nickname = "getAllRooms")
    public List<Room> getRooms(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        if (StringUtils.isNotBlank(roomNumber)) {
            return Collections.singletonList(this.roomServices.findByRoomNumber(roomNumber));
        }

        return this.roomServices.getAllRooms();
    }

    @GetMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
        @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
