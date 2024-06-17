package com.valuelabs.shipment.controller;

import com.valuelabs.shipment.dto.DeliveryPartnerDTO;
import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.service.DeliveryPartnerService;
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

@RestController
@RequestMapping("/delivery-partner")
@Tag(name = "DeliveryPartner", description = "DeliveryPartner APIs")
@RequiredArgsConstructor
@Slf4j
public class DeliveryPartnerController {

    private final DeliveryPartnerService deliveryPartnerService;

    @Operation(
            summary = "Add a new delivery partner",
            description = "Add a new delivery partner",
            tags = { "DeliveryPartner", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DeliveryPartnerDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public DeliveryPartner addDeliveryPartner(@RequestBody DeliveryPartnerDTO deliveryPartner) {
        log.info("in addDeliveryPartner");
        return deliveryPartnerService.addDeliveryPartner(deliveryPartner);
    }

    @Operation(
            summary = "Get all delivery partners",
            description = "Get all delivery partners.",
            tags = { "DeliveryPartner", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public List<DeliveryPartner> getAllDeliveryPartners() {
        log.info("in getAllDeliveryPartners");
        return deliveryPartnerService.getAllDeliveryPartners();
    }

    @Operation(
            summary = "Delete a delivery partner by ID",
            description = "Delete a delivery partner by ID",
            tags = { "DeliveryPartner", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public void deleteDeliveryPartner(@PathVariable Long id) {
        log.info("in deleteDeliveryPartner");
        deliveryPartnerService.deleteDeliveryPartner(id);
    }
}