package com.valuelabs.shipment.service;


import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.repository.CustomerRepository;
import com.valuelabs.shipment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.addCustomer(customer);

        assertEquals("John Doe", savedCustomer.getName());
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void getAllCustomers() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        Assertions.assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
        Mockito.verify(customerRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteCustomer() {
        Long id = 1L;

        customerService.deleteCustomer(id);

        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(id);
    }
}
