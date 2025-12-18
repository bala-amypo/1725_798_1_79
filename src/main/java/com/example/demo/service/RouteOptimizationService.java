package com.example.project.service;

import com.example.project.entity.RouteOptimizationResult;
import com.example.project.model.RouteRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteOptimizationService {

    public RouteOptimizationResult optimizeRoute(RouteRequest request) {

        
        List<String> optimizedRoute = new ArrayList<>(request.getLocations());
        double totalDistance = calculateDummyDistance(optimizedRoute);

        RouteOptimizationResult result = new RouteOptimizationResult();
        result.setRoute(optimizedRoute);
        result.setTotalDistance(totalDistance);
        result.setMessage("Route optimized successfully!");

        return result;
    }

    private double calculateDummyDistance(List<String> locations) {
       n
        return locations.size() * 5.25; 
    }
}
