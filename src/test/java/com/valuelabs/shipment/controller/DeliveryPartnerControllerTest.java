package com.valuelabs.shipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuelabs.shipment.dto.DeliveryPartnerDTO;

import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.service.DeliveryPartnerService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeliveryPartnerController.class)
public class DeliveryPartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryPartnerService deliveryPartnerService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDeliveryPartner() throws Exception {
        DeliveryPartnerDTO deliveryPartnerDTO = new DeliveryPartnerDTO();
        deliveryPartnerDTO.setName("Suresh");

        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDTO.getName());

        when(deliveryPartnerService.addDeliveryPartner(any(DeliveryPartnerDTO.class))).thenReturn(deliveryPartner);

        mockMvc.perform(post("/delivery-partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryPartnerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(deliveryPartnerDTO.getName()));

        verify(deliveryPartnerService, times(1)).addDeliveryPartner(any(DeliveryPartnerDTO.class));
    }

    @Test
    public void testGetAllDeliveryPartners() throws Exception {
        DeliveryPartner deliveryPartner1 = new DeliveryPartner();
        deliveryPartner1.setName("Suresh");

        DeliveryPartner deliveryPartner2 = new DeliveryPartner();
        deliveryPartner2.setName("Jane Doe");

        when(deliveryPartnerService.getAllDeliveryPartners()).thenReturn(Arrays.asList(deliveryPartner1, deliveryPartner2));

        mockMvc.perform(get("/delivery-partner")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Suresh"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));

        verify(deliveryPartnerService, times(1)).getAllDeliveryPartners();
    }

    @Test
    public void testGetDeliveryPartnerById() throws Exception {
        Long deliveryPartnerId = 1L;
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName("Suresh");

        when(deliveryPartnerService.getDeliveryPartnerById(deliveryPartnerId)).thenReturn(Optional.of(deliveryPartner));

        mockMvc.perform(get("/delivery-partner/{id}", deliveryPartnerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());


    }

    @Test
    public void testDeleteDeliveryPartner() throws Exception {
        Long deliveryPartnerId = 1L;
        doNothing().when(deliveryPartnerService).deleteDeliveryPartner(deliveryPartnerId);

        mockMvc.perform(delete("/delivery-partner/{id}", deliveryPartnerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deliveryPartnerService, times(1)).deleteDeliveryPartner(deliveryPartnerId);
    }
}