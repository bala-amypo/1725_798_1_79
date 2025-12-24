package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String vehicleNumber;
    
    @Positive
    @Column(nullable = false)
    private Double capacityKg;
    
    @Positive
    @Column(nullable = false)
    private Double fuelEfficiency;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    // Constructors
    public Vehicle() {
    }
    
    public Vehicle(Long id, String vehicleNumber, Double capacityKg, Double fuelEfficiency, User user) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.capacityKg = capacityKg;
        this.fuelEfficiency = fuelEfficiency;
        this.user = user;
    }
    
    // Builder pattern method
    public static VehicleBuilder builder() {
        return new VehicleBuilder();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public Double getCapacityKg() {
        return capacityKg;
    }
    
    public void setCapacityKg(Double capacityKg) {
        this.capacityKg = capacityKg;
    }
    
    public Double getFuelEfficiency() {
        return fuelEfficiency;
    }
    
    public void setFuelEfficiency(Double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    // Builder class
    public static class VehicleBuilder {
        private Long id;
        private String vehicleNumber;
        private Double capacityKg;
        private Double fuelEfficiency;
        private User user;
        
        public VehicleBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public VehicleBuilder vehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }
        
        public VehicleBuilder capacityKg(Double capacityKg) {
            this.capacityKg = capacityKg;
            return this;
        }
        
        public VehicleBuilder fuelEfficiency(Double fuelEfficiency) {
            this.fuelEfficiency = fuelEfficiency;
            return this;
        }
        
        public VehicleBuilder user(User user) {
            this.user = user;
            return this;
        }
        
        public Vehicle build() {
            return new Vehicle(id, vehicleNumber, capacityKg, fuelEfficiency, user);
        }
    }
}