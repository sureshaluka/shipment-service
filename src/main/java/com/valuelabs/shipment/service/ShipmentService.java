package com.valuelabs.shipment.service;

import com.valuelabs.shipment.entity.Shipment;

import java.util.List;

public interface ShipmentService {
    public Shipment createShipment(Shipment shipment) ;

    public List<Shipment> getShipmentsForDeliveryPartner(Long deliveryPartnerId) ;

    public Shipment updateShipmentStatus(Long shipmentId, String status);
}
