// ShipmentService.java
package com.example.demo.service;

import com.example.demo.entity.Shipment;
import java.util.List;

public interface ShipmentService {
    Shipment createShipment(Long vehicleId, Shipment shipment);
    Shipment getShipment(Long id);
    List<Shipment> getShipmentsByVehicle(Long vehicleId);
    Double getTotalWeightByVehicle(Long vehicleId);
}