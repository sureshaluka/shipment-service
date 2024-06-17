package com.valuelabs.shipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuelabs.shipment.dto.ShipmentDTO;
import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.service.CustomerService;
import com.valuelabs.shipment.service.DeliveryPartnerService;
import com.valuelabs.shipment.service.ShipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentController.class)
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   /* @Test
    public void testCreateShipment() throws Exception {
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

        when(shipmentService.createShipment(any(ShipmentDTO.class))).thenReturn(shipment);

        mockMvc.perform(post("/shipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipmentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item").value(shipmentDTO.getItem()))
                .andExpect(jsonPath("$.sourceLocation").value(shipmentDTO.getSourceLocation()))
                .andExpect(jsonPath("$.targetLocation").value(shipmentDTO.getTargetLocation()));

        verify(shipmentService, times(1)).createShipment(any(ShipmentDTO.class));
    }*/

    @Test
    public void testGetShipmentsForDeliveryPartner() throws Exception {
        Long deliveryPartnerId = 1L;

        Shipment shipment1 = new Shipment();
        shipment1.setItem("Laptop");
        shipment1.setSourceLocation("New York");
        shipment1.setTargetLocation("Los Angeles");


        Shipment shipment2 = new Shipment();
        shipment2.setItem("Phone");
        shipment2.setSourceLocation("Boston");
        shipment2.setTargetLocation("San Francisco");

        when(shipmentService.getShipmentsForDeliveryPartner(deliveryPartnerId)).thenReturn(Arrays.asList(shipment1, shipment2));

        mockMvc.perform(get("/shipment/delivery-partner/{id}", deliveryPartnerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].item").value("Laptop"))
                .andExpect(jsonPath("$[1].item").value("Phone"));

        verify(shipmentService, times(1)).getShipmentsForDeliveryPartner(deliveryPartnerId);
    }



    @Test
    void testUpdateShipmentStatus() throws Exception {
        Long shipmentId = 1L;
        String status = "Pending";

        Shipment shipment = new Shipment();
        shipment.setItem("Laptop");
        shipment.setSourceLocation("New York");
        shipment.setTargetLocation("Los Angeles");

        shipment.setStatus("Pending");

        when(shipmentService.updateShipmentStatus(shipmentId, status)).thenReturn(shipment);

        mockMvc.perform(put("/shipment/{id}/status", shipmentId)
                        .param("status", status)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(status));

        verify(shipmentService, times(1)).updateShipmentStatus(shipmentId, status);
    }
}