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

    private Double distanceKm;

    private Double fuelUsedLiters;

    private LocalDateTime generatedAt;

    public RouteOptimizationResult() {}

    public RouteOptimizationResult(Shipment shipment, Double distanceKm, Double fuelUsedLiters, LocalDateTime generatedAt) {
        this.shipment = shipment;
        this.distanceKm = distanceKm;
        this.fuelUsedLiters = fuelUsedLiters;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getFuelUsedLiters() {
        return fuelUsedLiters;
    }

    public void setFuelUsedLiters(Double fuelUsedLiters) {
        this.fuelUsedLiters = fuelUsedLiters;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
