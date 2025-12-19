package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Vehicle> addVehicle(@PathVariable Long userId,
                                              @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.addVehicle(userId, vehicle));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getVehiclesByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
