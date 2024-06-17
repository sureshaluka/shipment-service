package com.valuelabs.shipment.controller;

import com.valuelabs.shipment.dto.ShipmentDTO;
import com.valuelabs.shipment.entity.Shipment;
import com.valuelabs.shipment.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
@Tag(name = "Shipment", description = "Shipment APIs")
@RequiredArgsConstructor
@Slf4j
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Operation(
            summary = "Created a new shipment",
            description = "Creates a new Shipment.",
            tags = { "Shipment", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ShipmentDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public Shipment createShipment(@Valid @RequestBody ShipmentDTO shipment) {
        log.info("in createShipment");
        return shipmentService.createShipment(shipment);
    }

    @Operation(
            summary = "Returns all Shipments",
            description = "Returns all Shipment.",
            tags = { "Shipment", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/delivery-partner/{id}")
    public List<Shipment> getShipmentsForDeliveryPartner(@PathVariable Long id) {
        log.info("in getShipmentsForDeliveryPartner");
        return shipmentService.getShipmentsForDeliveryPartner(id);
    }

    @Operation(
            summary = "Updates shipment details",
            description = "Updates shipment details.",
            tags = { "Shipment", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ShipmentDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}/status")
    public Shipment updateShipmentStatus(@PathVariable Long id, @RequestParam String status) {
        log.info("in updateShipmentStatus");
        return shipmentService.updateShipmentStatus(id, status);
    }
}