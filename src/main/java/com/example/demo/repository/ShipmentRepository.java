package com.example.demo.repository;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findByVehicle(Vehicle vehicle);
}
