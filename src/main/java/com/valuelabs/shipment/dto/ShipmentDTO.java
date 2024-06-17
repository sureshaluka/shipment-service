package com.valuelabs.shipment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class ShipmentDTO {

    @NotBlank(message = "Item is mandatory")
    @Size(max = 100, message = "Item should not exceed 100 characters")
    private String item;

    @NotBlank(message = "Source location is mandatory")
    @Size(max = 255, message = "Source location should not exceed 255 characters")
    private String sourceLocation;

    @NotBlank(message = "Target location is mandatory")
    @Size(max = 255, message = "Target location should not exceed 255 characters")
    private String targetLocation;

    private Long deliveryPartnerId;

    private Long customerId;


}
