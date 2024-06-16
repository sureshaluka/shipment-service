package com.valuelabs.shipment.service.impl;

import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.repository.ShipmentRepository;
import com.valuelabs.shipment.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public Shipment createShipment(Shipment shipment) {
        log.info("in createShipment");
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getShipmentsForDeliveryPartner(Long deliveryPartnerId) {
        log.info("in getShipmentsForDeliveryPartner");
        return shipmentRepository.findByDeliveryPartnerId(deliveryPartnerId);
    }

    public Shipment updateShipmentStatus(Long shipmentId, String status) {
        log.info("in updateShipmentStatus");
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        shipment.setStatus(status);
        return shipmentRepository.save(shipment);
    }
}
