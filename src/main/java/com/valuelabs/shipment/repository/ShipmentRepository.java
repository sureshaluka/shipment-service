package com.valuelabs.shipment.repository;

import com.valuelabs.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByDeliveryPartnerId(Long deliveryPartnerId);
}