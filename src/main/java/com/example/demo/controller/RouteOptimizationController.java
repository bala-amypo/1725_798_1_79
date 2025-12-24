package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
    
    private final RouteOptimizationService routeOptimizationService;
    
    public RouteController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }
    
    @PostMapping("/optimize/{shipmentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<RouteOptimizationResult> optimizeRoute(@PathVariable Long shipmentId) {
        RouteOptimizationResult result = routeOptimizationService.optimizeRoute(shipmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @GetMapping("/results/{id}")
    public ResponseEntity<RouteOptimizationResult> getResultById(@PathVariable Long id) {
        RouteOptimizationResult result = routeOptimizationService.getResult(id);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/calculate-distance")
    public ResponseEntity<Map<String, Object>> calculateDistance(
            @RequestParam Double lat1,
            @RequestParam Double lon1,
            @RequestParam Double lat2,
            @RequestParam Double lon2) {
        
        double distance = calculateHaversineDistance(lat1, lon1, lat2, lon2);
        
        Map<String, Object> response = new HashMap<>();
        response.put("distanceKm", Math.round(distance * 100.0) / 100.0);
        response.put("pickup", Map.of("lat", lat1, "lon", lon1));
        response.put("drop", Map.of("lat", lat2, "lon", lon2));
        response.put("unit", "kilometers");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/simulate-optimization")
    public ResponseEntity<Map<String, Object>> simulateOptimization(
            @RequestParam Double distance,
            @RequestParam Double fuelEfficiency,
            @RequestParam Double weight) {
        
        double fuelUsage = distance / fuelEfficiency;
        double timeMinutes = (distance / 60.0) * 60; // 60 km/h average
        
        Map<String, Object> response = new HashMap<>();
        response.put("optimizedDistanceKm", distance);
        response.put("estimatedFuelUsageL", Math.round(fuelUsage * 100.0) / 100.0);
        response.put("estimatedTimeMinutes", Math.round(timeMinutes * 100.0) / 100.0);
        response.put("weightKg", weight);
        response.put("fuelEfficiency", fuelEfficiency);
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Route Optimization");
        response.put("message", "Service is operational");
        return ResponseEntity.ok(response);
    }
    
    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c;
    }
}