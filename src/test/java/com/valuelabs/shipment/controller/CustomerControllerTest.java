package com.valuelabs.shipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuelabs.shipment.dto.CustomerDTO;
import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.exceptions.ResourceNotFoundException;
import com.valuelabs.shipment.service.CustomerService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
   public  void testAddCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Suresh");
        customerDTO.setEmail("suresh@gmail.com");

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        when(customerService.addCustomer(any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.email").value(customerDTO.getEmail()));

        verify(customerService, times(1)).addCustomer(any(CustomerDTO.class));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setName("Suresh");
        customer1.setEmail("suresh@gmail.com");

        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");
        customer2.setEmail("jane.doe@example.com");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Suresh"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setName("Suresh");
        customer.setEmail("suresh@gmail.com");

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.email").value(customer.getEmail()));

        verify(customerService, times(1)).getCustomerById(customerId);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }
}