package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class RouteOptimizationController {

    private final RouteOptimizationService service;

    public RouteOptimizationController(RouteOptimizationService service) {
        this.service = service;
    }

    @PostMapping("/optimize/{shipmentId}")
    public ResponseEntity<RouteOptimizationResult> optimize(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.optimizeRoute(shipmentId));
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<RouteOptimizationResult> getResult(@PathVariable Long resultId) {
        return ResponseEntity.ok(service.getResult(resultId));
    }
}
