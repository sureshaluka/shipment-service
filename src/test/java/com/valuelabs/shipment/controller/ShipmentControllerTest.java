package com.valuelabs.shipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.service.CustomerService;
import com.valuelabs.shipment.service.DeliveryPartnerService;
import com.valuelabs.shipment.service.ShipmentService;
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

@WebMvcTest(ShipmentController.class)
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @MockBean
    private DeliveryPartnerService deliveryPartnerService;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void testAddDeliveryPartner() throws Exception {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setId(1L);
        deliveryPartner.setName("Suresh");
        deliveryPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);

        Customer customer = new Customer();
        customer.setName("Suresh");
        customer.setEmail("sureshaluka@gmail.com");

        customer = customerService.addCustomer(customer);

        Shipment shipment = new Shipment();
        shipment.setStatus("Active");
        shipment.setDeliveryPartner(deliveryPartner);
        shipment.setCustomer(customer);

        given(shipmentService.createShipment(shipment)).willReturn(shipment);

        mockMvc.perform(post("/shipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shipment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Active"));
    }

    @Test
    public void testGetShipmentsForDeliveryPartner() throws Exception {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setId(1L);
        deliveryPartner.setName("Suresh");
        deliveryPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);

        Customer customer = new Customer();
        customer.setName("Suresh");
        customer.setEmail("sureshaluka@gmail.com");

        customer = customerService.addCustomer(customer);

        Shipment shipment = new Shipment();
        shipment.setStatus("Active");
        shipment.setDeliveryPartner(deliveryPartner);
        shipment.setCustomer(customer);

        given(shipmentService.getShipmentsForDeliveryPartner(1L)).willReturn(Arrays.asList(shipment));

        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("Active"));
    }
}