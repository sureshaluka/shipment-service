package com.valuelabs.shipment.service;


import com.valuelabs.shipment.dto.CustomerDTO;
import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.repository.CustomerRepository;
import com.valuelabs.shipment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Suresh");
        customerDTO.setEmail("suresh@gmail.com");

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.addCustomer(customerDTO);
        assertNotNull(result);
        assertEquals(customerDTO.getName(), result.getName());
        assertEquals(customerDTO.getEmail(), result.getEmail());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Suresh");
        customer1.setEmail("suresh@gmail.com");

        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");
        customer2.setEmail("jane.doe@example.com");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
        assertEquals("Suresh", customers.get(0).getName());
        assertEquals("Jane Doe", customers.get(1).getName());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;
        doNothing().when(customerRepository).deleteById(customerId);
        customerService.deleteCustomer(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }
}
