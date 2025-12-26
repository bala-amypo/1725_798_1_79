package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "pickup_location_id", nullable = false)
    private Location pickupLocation;

    @ManyToOne
    @JoinColumn(name = "drop_location_id", nullable = false)
    private Location dropLocation;

    @Column(nullable = false)
    private Double weightKg;

    @Column(nullable = false)
    private LocalDate scheduledDate;

    public Shipment() {}

    public Shipment(Long id, Vehicle vehicle, Location pickupLocation,
                    Location dropLocation, Double weightKg, LocalDate scheduledDate) {
        this.id = id;
        this.vehicle = vehicle;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.weightKg = weightKg;
        this.scheduledDate = scheduledDate;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Vehicle vehicle;
        private Location pickupLocation;
        private Location dropLocation;
        private Double weightKg;
        private LocalDate scheduledDate;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder vehicle(Vehicle vehicle) { this.vehicle = vehicle; return this; }
        public Builder pickupLocation(Location pickupLocation) { this.pickupLocation = pickupLocation; return this; }
        public Builder dropLocation(Location dropLocation) { this.dropLocation = dropLocation; return this; }
        public Builder weightKg(Double weightKg) { this.weightKg = weightKg; return this; }
        public Builder scheduledDate(LocalDate scheduledDate) { this.scheduledDate = scheduledDate; return this; }

        public Shipment build() {
            return new Shipment(id, vehicle, pickupLocation, dropLocation, weightKg, scheduledDate);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public Location getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(Location pickupLocation) { this.pickupLocation = pickupLocation; }

    public Location getDropLocation() { return dropLocation; }
    public void setDropLocation(Location dropLocation) { this.dropLocation = dropLocation; }

    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }

    public LocalDate getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDate scheduledDate) { this.scheduledDate = scheduledDate; }
}
