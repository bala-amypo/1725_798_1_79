// ShipmentRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByVehicleId(Long vehicleId);
    
    @Query("SELECT s FROM Shipment s WHERE s.pickupLocation.id = :locationId OR s.dropLocation.id = :locationId")
    List<Shipment> findByLocationId(Long locationId);
    
    @Query("SELECT SUM(s.weightKg) FROM Shipment s WHERE s.vehicle.id = :vehicleId")
    Double getTotalWeightByVehicle(Long vehicleId);
}