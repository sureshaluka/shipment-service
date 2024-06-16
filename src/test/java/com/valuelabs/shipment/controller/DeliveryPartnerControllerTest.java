package com.valuelabs.shipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.service.DeliveryPartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void testGetAllDeliveryPartners() throws Exception {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setId(1L);
        deliveryPartner.setName("Suresh");

        given(deliveryPartnerService.getAllDeliveryPartners()).willReturn(Arrays.asList(deliveryPartner));

        mockMvc.perform(get("/delivery-partner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Suresh"));
    }

    @Test
    public void testAddDeliveryPartner() throws Exception {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setId(1L);
        deliveryPartner.setName("Suresh");

        given(deliveryPartnerService.addDeliveryPartner(deliveryPartner)).willReturn(deliveryPartner);

        mockMvc.perform(post("/delivery-partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deliveryPartner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Suresh"));
    }
}