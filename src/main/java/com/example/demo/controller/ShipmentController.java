package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<Shipment> create(@PathVariable Long vehicleId,
                                           @RequestBody Shipment shipment) {
        return ResponseEntity.ok(service.createShipment(vehicleId, shipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getShipment(id));
    }
}
