package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService s) {
        this.service = s;
    }

    @PostMapping
    public Location add(@RequestBody Location l) {
        return service.add(l);
    }
}
