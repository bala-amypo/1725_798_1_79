package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    
    private final ShipmentService shipmentService;
    
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    
    @PostMapping("/vehicle/{vehicleId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Shipment> createShipment(
            @PathVariable Long vehicleId,
            @Valid @RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(vehicleId, shipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentService.getShipment(id);
        return ResponseEntity.ok(shipment);
    }
    
    @GetMapping("/vehicle/{vehicleId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Shipment>> getShipmentsByVehicle(@PathVariable Long vehicleId) {
        List<Shipment> shipments = shipmentService.getShipmentsByVehicle(vehicleId);
        return ResponseEntity.ok(shipments);
    }
    
    @GetMapping("/vehicle/{vehicleId}/total-weight")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Double> getTotalWeightByVehicle(@PathVariable Long vehicleId) {
        Double totalWeight = shipmentService.getTotalWeightByVehicle(vehicleId);
        return ResponseEntity.ok(totalWeight);
    }
    
    @GetMapping("/validate-capacity")
    public ResponseEntity<String> validateCapacity(
            @RequestParam Double weight,
            @RequestParam Double capacity) {
        if (weight <= 0) {
            return ResponseEntity.badRequest().body("Weight must be positive");
        }
        if (weight > capacity) {
            return ResponseEntity.badRequest().body("Weight exceeds capacity");
        }
        return ResponseEntity.ok("Weight is within capacity");
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Shipment service is healthy");
    }
}