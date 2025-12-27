package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vehicles",
    uniqueConstraints = @UniqueConstraint(columnNames = "vehicleNumber")
)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleNumber;

    @Column(nullable = false)
    private Double capacityKg;

    @Column(nullable = false)
    private Double fuelEfficiency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vehicle() {}

    public Vehicle(Long id, String vehicleNumber, Double capacityKg,
                   Double fuelEfficiency, User user) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.capacityKg = capacityKg;
        this.fuelEfficiency = fuelEfficiency;
        this.user = user;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String vehicleNumber;
        private Double capacityKg;
        private Double fuelEfficiency;
        private User user;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder vehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; return this; }
        public Builder capacityKg(Double capacityKg) { this.capacityKg = capacityKg; return this; }
        public Builder fuelEfficiency(Double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; return this; }
        public Builder user(User user) { this.user = user; return this; }

        public Vehicle build() {
            return new Vehicle(id, vehicleNumber, capacityKg, fuelEfficiency, user);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public Double getCapacityKg() { return capacityKg; }
    public void setCapacityKg(Double capacityKg) { this.capacityKg = capacityKg; }

    public Double getFuelEfficiency() { return fuelEfficiency; }
    public void setFuelEfficiency(Double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
