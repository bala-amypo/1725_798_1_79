package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "route_optimization_results")
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private Double optimizedDistanceKm;

    @Column(nullable = false)
    private Double estimatedFuelUsageL;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    public RouteOptimizationResult() {}

    public RouteOptimizationResult(Long id, Shipment shipment,
                                   Double optimizedDistanceKm,
                                   Double estimatedFuelUsageL,
                                   LocalDateTime generatedAt) {
        this.id = id;
        this.shipment = shipment;
        this.optimizedDistanceKm = optimizedDistanceKm;
        this.estimatedFuelUsageL = estimatedFuelUsageL;
        this.generatedAt = generatedAt;
    }

    // -------- Builder --------
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Shipment shipment;
        private Double optimizedDistanceKm;
        private Double estimatedFuelUsageL;
        private LocalDateTime generatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder shipment(Shipment shipment) { this.shipment = shipment; return this; }
        public Builder optimizedDistanceKm(Double optimizedDistanceKm) { this.optimizedDistanceKm = optimizedDistanceKm; return this; }
        public Builder estimatedFuelUsageL(Double estimatedFuelUsageL) { this.estimatedFuelUsageL = estimatedFuelUsageL; return this; }
        public Builder generatedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; return this; }

        public RouteOptimizationResult build() {
            return new RouteOptimizationResult(
                id, shipment, optimizedDistanceKm, estimatedFuelUsageL, generatedAt
            );
        }
    }

    // -------- Getters & Setters --------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shipment getShipment() { return shipment; }
    public void setShipment(Shipment shipment) { this.shipment = shipment; }

    public Double getOptimizedDistanceKm() { return optimizedDistanceKm; }
    public void setOptimizedDistanceKm(Double optimizedDistanceKm) {
        this.optimizedDistanceKm = optimizedDistanceKm;
    }

    public Double getEstimatedFuelUsageL() { return estimatedFuelUsageL; }
    public void setEstimatedFuelUsageL(Double estimatedFuelUsageL) {
        this.estimatedFuelUsageL = estimatedFuelUsageL;
    }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
