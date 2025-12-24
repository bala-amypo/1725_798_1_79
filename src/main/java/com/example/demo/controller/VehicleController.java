package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    private final VehicleService vehicleService;
    
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    @PostMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<Vehicle> createVehicle(
            @PathVariable Long userId,
            @Valid @RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.addVehicle(userId, vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.findById(id);
        return ResponseEntity.ok(vehicle);
    }
    
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<List<Vehicle>> getVehiclesByUser(@PathVariable Long userId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByUser(userId);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/efficient/{minEfficiency}")
    public ResponseEntity<List<Vehicle>> getEfficientVehicles(
            @PathVariable Double minEfficiency) {
        List<Vehicle> vehicles = vehicleService.findByFuelEfficiencyGreaterThanEqual(minEfficiency);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Vehicle service is healthy");
    }
}