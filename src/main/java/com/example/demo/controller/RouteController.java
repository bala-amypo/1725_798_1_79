// Rename file to RouteController.java (or rename class to RouteOptimizationController)
// I'll update the controller code:

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
public class RouteController {  // Keep the class name as RouteController
    
    private final RouteOptimizationService routeOptimizationService;
    
    public RouteController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }
    
    // ... rest of the code remains the same as before ...
}