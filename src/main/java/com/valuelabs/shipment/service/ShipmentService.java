package com.valuelabs.shipment.service;

import com.valuelabs.shipment.dto.ShipmentDTO;
import com.valuelabs.shipment.entity.Shipment;

import java.util.List;

public interface ShipmentService {

    public Shipment createShipment(ShipmentDTO shipmentDTO) ;

    public List<Shipment> getShipmentsForDeliveryPartner(Long deliveryPartnerId) ;

    public Shipment updateShipmentStatus(Long shipmentId, String status);
}
