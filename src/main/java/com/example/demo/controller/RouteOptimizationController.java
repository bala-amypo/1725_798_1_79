package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteOptimizationController {

    private final RouteOptimizationService routeService;

    public RouteOptimizationController(RouteOptimizationService routeService) {
        this.routeService = routeService;
    }

    // POST optimize route
    @PostMapping("/optimize/{shipmentId}")
    public RouteOptimizationResult optimize(@PathVariable Long shipmentId) {
        return routeService.optimizeRoute(shipmentId);
    }

    // GET optimization result
    @GetMapping("/{resultId}")
    public RouteOptimizationResult getResult(@PathVariable Long resultId) {
        return routeService.getResult(resultId);
    }
}
