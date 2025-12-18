package com.example.demo.service.impl;

import com.example.demo.entity.Shipment;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repo;

    public ShipmentServiceImpl(ShipmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Shipment schedule(Shipment s) {

        if (s.getWeightKg() > s.getVehicle().getCapacityKg())
            throw new IllegalArgumentException("Weight exceeds vehicle capacity");

        if (s.getScheduledDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date cannot be in the past");

        return repo.save(s);
    }
}
