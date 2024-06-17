package com.valuelabs.shipment.service.impl;

import com.valuelabs.shipment.dto.ShipmentDTO;
import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.exceptions.ResourceNotFoundException;
import com.valuelabs.shipment.repository.CustomerRepository;
import com.valuelabs.shipment.repository.DeliveryPartnerRepository;
import com.valuelabs.shipment.repository.ShipmentRepository;
import com.valuelabs.shipment.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final CustomerRepository customerRepository;

    public Shipment createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setItem(shipmentDTO.getItem());
        shipment.setSourceLocation(shipmentDTO.getSourceLocation());
        shipment.setTargetLocation(shipmentDTO.getTargetLocation());
        if(Objects.nonNull(shipmentDTO.getDeliveryPartnerId())){

            shipment.setDeliveryPartner(deliveryPartnerRepository
                    .findById(shipmentDTO.getDeliveryPartnerId()).orElseThrow(()-> new ResourceNotFoundException(" DeliveryPartner not found for the given Id "+shipmentDTO.getDeliveryPartnerId())));
        }

        if(Objects.nonNull(shipmentDTO.getCustomerId())){
            shipment.setCustomer(customerRepository.findById(shipmentDTO.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException(" Customer not found "+shipmentDTO.getCustomerId())));
        }
        shipment.setStatus("Pending");
        return shipmentRepository.save(shipment);
    }


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
