package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Location> create(@RequestBody Location location) {
        return ResponseEntity.ok(service.createLocation(location));
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        return ResponseEntity.ok(service.getAllLocations());
    }
}
