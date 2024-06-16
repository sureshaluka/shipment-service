package com.valuelabs.shipment.service.impl;

import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.repository.DeliveryPartnerRepository;
import com.valuelabs.shipment.service.DeliveryPartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) {
        log.info("in addDeliveryPartner");
        return deliveryPartnerRepository.save(deliveryPartner);
    }

    public List<DeliveryPartner> getAllDeliveryPartners() {
        log.info("in getAllDeliveryPartners");
        return deliveryPartnerRepository.findAll();
    }

    public void deleteDeliveryPartner(Long id) {
        log.info("in deleteDeliveryPartner");
        deliveryPartnerRepository.deleteById(id);
    }
}
