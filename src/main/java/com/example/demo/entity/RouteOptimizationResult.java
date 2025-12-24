package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "route_optimization_results")
public class RouteOptimizationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false, unique = true)
    private Shipment shipment;
    
    @Column(nullable = false)
    private Double optimizedDistanceKm;
    
    @Column(nullable = false)
    private Double estimatedFuelUsageL;
    
    @Column(nullable = false)
    private Double estimatedTimeMinutes;
    
    @Column(nullable = false)
    private LocalDateTime generatedAt;
    
    // Constructors
    public RouteOptimizationResult() {
        this.generatedAt = LocalDateTime.now();
    }
    
    public RouteOptimizationResult(Long id, Shipment shipment, Double optimizedDistanceKm, 
                                  Double estimatedFuelUsageL, Double estimatedTimeMinutes) {
        this.id = id;
        this.shipment = shipment;
        this.optimizedDistanceKm = optimizedDistanceKm;
        this.estimatedFuelUsageL = estimatedFuelUsageL;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.generatedAt = LocalDateTime.now();
    }
    
    // Builder pattern method
    public static RouteOptimizationResultBuilder builder() {
        return new RouteOptimizationResultBuilder();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Shipment getShipment() {
        return shipment;
    }
    
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
    
    public Double getOptimizedDistanceKm() {
        return optimizedDistanceKm;
    }
    
    public void setOptimizedDistanceKm(Double optimizedDistanceKm) {
        this.optimizedDistanceKm = optimizedDistanceKm;
    }
    
    public Double getEstimatedFuelUsageL() {
        return estimatedFuelUsageL;
    }
    
    public void setEstimatedFuelUsageL(Double estimatedFuelUsageL) {
        this.estimatedFuelUsageL = estimatedFuelUsageL;
    }
    
    public Double getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }
    
    public void setEstimatedTimeMinutes(Double estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }
    
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    // Builder class
    public static class RouteOptimizationResultBuilder {
        private Long id;
        private Shipment shipment;
        private Double optimizedDistanceKm;
        private Double estimatedFuelUsageL;
        private Double estimatedTimeMinutes;
        private LocalDateTime generatedAt = LocalDateTime.now();
        
        public RouteOptimizationResultBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public RouteOptimizationResultBuilder shipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }
        
        public RouteOptimizationResultBuilder optimizedDistanceKm(Double optimizedDistanceKm) {
            this.optimizedDistanceKm = optimizedDistanceKm;
            return this;
        }
        
        public RouteOptimizationResultBuilder estimatedFuelUsageL(Double estimatedFuelUsageL) {
            this.estimatedFuelUsageL = estimatedFuelUsageL;
            return this;
        }
        
        public RouteOptimizationResultBuilder estimatedTimeMinutes(Double estimatedTimeMinutes) {
            this.estimatedTimeMinutes = estimatedTimeMinutes;
            return this;
        }
        
        public RouteOptimizationResultBuilder generatedAt(LocalDateTime generatedAt) {
            this.generatedAt = generatedAt;
            return this;
        }
        
        public RouteOptimizationResult build() {
            RouteOptimizationResult result = new RouteOptimizationResult(id, shipment, optimizedDistanceKm, 
                                                                       estimatedFuelUsageL, estimatedTimeMinutes);
            if (generatedAt != null) {
                result.setGeneratedAt(generatedAt);
            }
            return result;
        }
    }
}