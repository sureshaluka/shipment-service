package com.valuelabs.shipment.service;


import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.repository.DeliveryPartnerRepository;
import com.valuelabs.shipment.service.impl.DeliveryPartnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeliveryPartnerServiceTest {

    @Mock
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @InjectMocks
    private DeliveryPartnerServiceImpl deliveryPartnerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addDeliveryPartner() {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName("Jane Doe");

        when(deliveryPartnerRepository.save(deliveryPartner)).thenReturn(deliveryPartner);

        DeliveryPartner savedDeliveryPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);

        assertEquals("Jane Doe", savedDeliveryPartner.getName());
        verify(deliveryPartnerRepository, times(1)).save(deliveryPartner);
    }

    @Test
    void getAllDeliveryPartners() {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName("Jane Doe");

        when(deliveryPartnerRepository.findAll()).thenReturn(Arrays.asList(deliveryPartner));

        List<DeliveryPartner> deliveryPartners = deliveryPartnerService.getAllDeliveryPartners();

        assertEquals(1, deliveryPartners.size());
        assertEquals("Jane Doe", deliveryPartners.get(0).getName());
        verify(deliveryPartnerRepository, times(1)).findAll();
    }

    @Test
    void deleteDeliveryPartner() {
        Long id = 1L;

        deliveryPartnerService.deleteDeliveryPartner(id);

        verify(deliveryPartnerRepository, times(1)).deleteById(id);
    }
}
