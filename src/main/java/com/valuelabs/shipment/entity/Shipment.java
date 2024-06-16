package com.valuelabs.shipment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceLocation;
    private String targetLocation;
    private String item;
    private String status;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private DeliveryPartner deliveryPartner;

}