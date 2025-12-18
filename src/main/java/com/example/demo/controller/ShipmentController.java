package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService service;

    public ShipmentController(ShipmentService s) {
        this.service = s;
    }

    @PostMapping
    public Shipment schedule(@RequestBody Shipment shipment) {
        return service.schedule(shipment);
    }
}
