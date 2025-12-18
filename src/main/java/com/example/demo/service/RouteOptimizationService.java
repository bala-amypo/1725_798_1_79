package com.example.project.service;

import com.example.project.entity.RouteOptimizationResult;
import com.example.project.model.RouteRequest;

public interface RouteOptimizationService {

    RouteOptimizationResult optimizeRoute(RouteRequest request);
}
