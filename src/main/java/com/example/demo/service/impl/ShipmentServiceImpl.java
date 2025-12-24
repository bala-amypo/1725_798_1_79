// ShipmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    
    @Override
    @Transactional
    public Shipment createShipment(Long vehicleId, Shipment shipment) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleId));
        
        if (shipment.getWeightKg() <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        
        if (shipment.getWeightKg() > vehicle.getCapacityKg()) {
            throw new IllegalArgumentException("Shipment weight exceeds vehicle capacity");
        }
        
        if (shipment.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date cannot be in the past");
        }
        
        Location pickup = locationRepository.findById(shipment.getPickupLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pickup location not found"));
        
        Location drop = locationRepository.findById(shipment.getDropLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Drop location not found"));
        
        shipment.setVehicle(vehicle);
        shipment.setPickupLocation(pickup);
        shipment.setDropLocation(drop);
        
        return shipmentRepository.save(shipment);
    }
    
    @Override
    public Shipment getShipment(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + id));
    }
    
    @Override
    public List<Shipment> getShipmentsByVehicle(Long vehicleId) {
        return shipmentRepository.findByVehicleId(vehicleId);
    }
    
    @Override
    public Double getTotalWeightByVehicle(Long vehicleId) {
        Double total = shipmentRepository.getTotalWeightByVehicle(vehicleId);
        return total != null ? total : 0.0;
    }
}