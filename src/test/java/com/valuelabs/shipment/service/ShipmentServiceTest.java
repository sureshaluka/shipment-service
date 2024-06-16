package com.valuelabs.shipment.service;


import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.repository.ShipmentRepository;
import com.valuelabs.shipment.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createShipment() {
        Shipment shipment = new Shipment();
        shipment.setItem("Laptop");

        when(shipmentRepository.save(shipment)).thenReturn(shipment);

        Shipment savedShipment = shipmentService.createShipment(shipment);

        assertEquals("Laptop", savedShipment.getItem());
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void getShipmentsForDeliveryPartner() {
        Shipment shipment = new Shipment();
        shipment.setItem("Laptop");

        when(shipmentRepository.findByDeliveryPartnerId(1L)).thenReturn(Arrays.asList(shipment));

        List<Shipment> shipments = shipmentService.getShipmentsForDeliveryPartner(1L);

        assertEquals(1, shipments.size());
        assertEquals("Laptop", shipments.get(0).getItem());
        verify(shipmentRepository, times(1)).findByDeliveryPartnerId(1L);
    }

    @Test
    void updateShipmentStatus() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStatus("Pending");

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

        Shipment updatedShipment = shipmentService.updateShipmentStatus(1L, "Delivered");

        assertEquals("Delivered", updatedShipment.getStatus());
        verify(shipmentRepository, times(1)).findById(1L);
        verify(shipmentRepository, times(1)).save(shipment);
    }
}
