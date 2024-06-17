package com.valuelabs.shipment.service;

import com.valuelabs.shipment.dto.CustomerDTO;
import com.valuelabs.shipment.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public Customer addCustomer(CustomerDTO customerDTO) ;

    public List<Customer> getAllCustomers();

    public void deleteCustomer(Long id);

    boolean existsById(Long id);

    Optional<Customer> getCustomerById(Long id);
}
