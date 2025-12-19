package com.example.demo.repository;

import com.example.demo.entity.Vehicle;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUser(User user);

    Vehicle findByVehicleNumber(String vehicleNumber);
}
