package com.valuelabs.shipment.service.impl;

import com.valuelabs.shipment.dto.DeliveryPartnerDTO;
import com.valuelabs.shipment.entity.DeliveryPartner;
import com.valuelabs.shipment.repository.DeliveryPartnerRepository;
import com.valuelabs.shipment.service.DeliveryPartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartner addDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO) {
        log.info("in addDeliveryPartner");
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDTO.getName());
        return deliveryPartnerRepository.save(deliveryPartner);
    }



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

    @Override
    public Optional<DeliveryPartner> getDeliveryPartnerById(Long deliveryPartnerId) {
        log.info("in getDeliveryPartnerById");
        return deliveryPartnerRepository.findById(deliveryPartnerId);
    }


}
