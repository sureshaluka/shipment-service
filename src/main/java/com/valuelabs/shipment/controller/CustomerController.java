package com.valuelabs.shipment.controller;


import com.valuelabs.shipment.entity.Customer;
import com.valuelabs.shipment.exceptions.ResourceNotFoundException;
import com.valuelabs.shipment.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Customer", description = "Customer APIs")
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    @Operation(
            summary = "Add a new customer",
            description = "Add a new customer.",
            tags = { "customer", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        log.info("entered in addCustomer");
        return customerService.addCustomer(customer);
    }
    @Operation(
            summary = "Get customer by id",
            description = "Get customer by id.",
            tags = { "customer", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Operation(
            summary = "Get all customers",
            description = "Get all customers",
            tags = { "customer", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public List<Customer> getAllCustomers() {
        log.info("returns allCustomer");
        return customerService.getAllCustomers();
    }

    @Operation(
            summary = "Delete a customer by ID",
            description = "Delete a customer by ID",
            tags = { "customer", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Void.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        log.info("deletes allCustomer");
        if (!customerService.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerService.deleteCustomer(id);
    }
}