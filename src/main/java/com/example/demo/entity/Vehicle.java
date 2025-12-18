package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(unique = true)
    private String vehicleNumber;

    private Double capacityKg;

    private Double fuelEfficiency;

    public Vehicle() {}

    // getters & setters
}
