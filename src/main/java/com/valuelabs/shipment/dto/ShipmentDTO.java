package com.valuelabs.shipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
