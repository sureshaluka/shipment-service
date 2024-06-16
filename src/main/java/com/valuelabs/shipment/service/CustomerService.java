package com.valuelabs.shipment.service;

import com.valuelabs.shipment.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public Customer addCustomer(Customer customer) ;

    public List<Customer> getAllCustomers();

    public void deleteCustomer(Long id);

    boolean existsById(Long id);

    Optional<Customer> getCustomerById(Long id);
}
