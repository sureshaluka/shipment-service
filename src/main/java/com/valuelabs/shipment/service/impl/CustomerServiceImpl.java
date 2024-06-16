package com.valuelabs.shipment.service.impl;

import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.repository.CustomerRepository;
import com.valuelabs.shipment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        log.info("in addCustomer service");
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        log.info("in getAllCustomers service");
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        log.info("in deleteCustomer service");
        customerRepository.deleteById(id);
    }
}