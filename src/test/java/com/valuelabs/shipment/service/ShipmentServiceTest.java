package com.valuelabs.shipment.service;


import com.valuelabs.shipment.dto.ShipmentDTO;
import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.repository.CustomerRepository;
import com.valuelabs.shipment.repository.DeliveryPartnerRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   /* @Test
    public void testCreateShipment() {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setItem("Laptop");
        shipmentDTO.setSourceLocation("New York");
        shipmentDTO.setTargetLocation("Los Angeles");
        shipmentDTO.setDeliveryPartnerId(1L);

        Shipment shipment = new Shipment();
        shipment.setItem(shipmentDTO.getItem());
        shipment.setSourceLocation(shipmentDTO.getSourceLocation());
        shipment.setTargetLocation(shipmentDTO.getTargetLocation());

        shipment.setStatus("Pending");

        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        Shipment result = shipmentService.createShipment(shipmentDTO);
        assertNotNull(result);
        assertEquals(shipmentDTO.getItem(), result.getItem());
        assertEquals(shipmentDTO.getSourceLocation(), result.getSourceLocation());
        assertEquals(shipmentDTO.getTargetLocation(), result.getTargetLocation());

        assertEquals("Pending", result.getStatus());

        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }*/

    @Test
    void testGetShipmentsForDeliveryPartner() {
        Long deliveryPartnerId = 1L;

        Shipment shipment1 = new Shipment();
        shipment1.setItem("Laptop");
        shipment1.setSourceLocation("New York");
        shipment1.setTargetLocation("Los Angeles");


        Shipment shipment2 = new Shipment();
        shipment2.setItem("Phone");
        shipment2.setSourceLocation("Boston");
        shipment2.setTargetLocation("San Francisco");


        when(shipmentRepository.findByDeliveryPartnerId(deliveryPartnerId)).thenReturn(Arrays.asList(shipment1, shipment2));

        List<Shipment> shipments = shipmentService.getShipmentsForDeliveryPartner(deliveryPartnerId);
        assertEquals(2, shipments.size());
        assertEquals("Laptop", shipments.get(0).getItem());
        assertEquals("Phone", shipments.get(1).getItem());

        verify(shipmentRepository, times(1)).findByDeliveryPartnerId(deliveryPartnerId);
    }

    @Test
    void testUpdateShipmentStatus() {
        Long shipmentId = 1L;
        String status = "Delivered";

        Shipment shipment = new Shipment();
        shipment.setItem("Laptop");
        shipment.setSourceLocation("New York");
        shipment.setTargetLocation("Los Angeles");

        shipment.setStatus("Pending");

        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        Shipment result = shipmentService.updateShipmentStatus(shipmentId, status);
        assertNotNull(result);
        assertEquals(status, result.getStatus());

        verify(shipmentRepository, times(1)).findById(shipmentId);
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }
}
