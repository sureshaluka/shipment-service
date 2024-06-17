package com.valuelabs.shipment.service;


import com.valuelabs.shipment.dto.DeliveryPartnerDTO;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void testAddDeliveryPartner() {
        DeliveryPartnerDTO deliveryPartnerDTO = new DeliveryPartnerDTO();
        deliveryPartnerDTO.setName("John Doe");

        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDTO.getName());

        when(deliveryPartnerRepository.save(any(DeliveryPartner.class))).thenReturn(deliveryPartner);

        DeliveryPartner result = deliveryPartnerService.addDeliveryPartner(deliveryPartnerDTO);
        assertNotNull(result);
        assertEquals(deliveryPartnerDTO.getName(), result.getName());

        verify(deliveryPartnerRepository, times(1)).save(any(DeliveryPartner.class));
    }

    @Test
    public void testGetAllDeliveryPartners() {
        DeliveryPartner deliveryPartner1 = new DeliveryPartner();
        deliveryPartner1.setName("John Doe");

        DeliveryPartner deliveryPartner2 = new DeliveryPartner();
        deliveryPartner2.setName("Jane Doe");

        when(deliveryPartnerRepository.findAll()).thenReturn(Arrays.asList(deliveryPartner1, deliveryPartner2));

        List<DeliveryPartner> deliveryPartners = deliveryPartnerService.getAllDeliveryPartners();
        assertEquals(2, deliveryPartners.size());
        assertEquals("John Doe", deliveryPartners.get(0).getName());
        assertEquals("Jane Doe", deliveryPartners.get(1).getName());

        verify(deliveryPartnerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteDeliveryPartner() {
        Long deliveryPartnerId = 1L;
        doNothing().when(deliveryPartnerRepository).deleteById(deliveryPartnerId);
        deliveryPartnerService.deleteDeliveryPartner(deliveryPartnerId);
        verify(deliveryPartnerRepository, times(1)).deleteById(deliveryPartnerId);
    }
}
