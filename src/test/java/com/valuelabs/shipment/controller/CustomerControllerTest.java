package com.valuelabs.shipment.controller;

import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.exceptions.ResourceNotFoundException;
import com.valuelabs.shipment.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;



    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Suresh");
        customer.setEmail("sureshaluka@gmail.com");

        given(customerService.addCustomer(customer)).willReturn(customer);

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Suresh\", \"email\":\"sureshaluka@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Suresh"));
    }

    @Test
    public void testGetCustomerById() throws Exception {


        given(customerService.getCustomerById(100L)).willThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/customer/100"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Suresh");
        customer.setEmail("sureshaluka@gmail.com");

        given(customerService.getAllCustomers()).willReturn(Arrays.asList(customer));

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Suresh"));
    }
}