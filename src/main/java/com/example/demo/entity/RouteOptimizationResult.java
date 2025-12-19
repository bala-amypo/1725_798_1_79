package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shipment shipment;

    private Double optimizedDistanceKm;
    private Double estimatedFuelUsage;
    private LocalDateTime generatedAt;

    public RouteOptimizationResult() {}

    public RouteOptimizationResult(Long id, Shipment shipment, Double optimizedDistanceKm,
                                   Double estimatedFuelUsage, LocalDateTime generatedAt) {
        this.id = id;
        this.shipment = shipment;
        this.optimizedDistanceKm = optimizedDistanceKm;
        this.estimatedFuelUsage = estimatedFuelUsage;
        this.generatedAt = generatedAt;
    }

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public Shipment getShipment(){ return shipment; }
    public void setShipment(Shipment shipment){ this.shipment = shipment; }

    public Double getOptimizedDistanceKm(){ return optimizedDistanceKm; }
    public void setOptimizedDistanceKm(Double optimizedDistanceKm){ this.optimizedDistanceKm = optimizedDistanceKm; }

    public Double getEstimatedFuelUsage(){ return estimatedFuelUsage; }
    public void setEstimatedFuelUsage(Double estimatedFuelUsage){ this.estimatedFuelUsage = estimatedFuelUsage; }

    public LocalDateTime getGeneratedAt(){ return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt){ this.generatedAt = generatedAt; }
}
