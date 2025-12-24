package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    private final LocationService locationService;
    
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }
    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        // For simplicity, we'll just get from repository
        // In real app, you'd have a findById service method
        return ResponseEntity.ok(Location.builder().id(id).build());
    }
    
    @GetMapping("/validate")
    public ResponseEntity<String> validateLocation(@RequestParam Double lat, @RequestParam Double lon) {
        if (lat < -90 || lat > 90) {
            return ResponseEntity.badRequest().body("Invalid latitude. Must be between -90 and 90.");
        }
        if (lon < -180 || lon > 180) {
            return ResponseEntity.badRequest().body("Invalid longitude. Must be between -180 and 180.");
        }
        return ResponseEntity.ok("Location coordinates are valid");
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Location service is healthy");
    }
}