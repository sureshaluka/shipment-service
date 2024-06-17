package com.valuelabs.shipment.service;

import com.valuelabs.shipment.dto.DeliveryPartnerDTO;
import com.valuelabs.shipment.entity.DeliveryPartner;

import java.util.List;
import java.util.Optional;

public interface DeliveryPartnerService {


    public DeliveryPartner addDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO);

    public List<DeliveryPartner> getAllDeliveryPartners();

    public void deleteDeliveryPartner(Long id);


    Optional<DeliveryPartner> getDeliveryPartnerById(Long deliveryPartnerId);
}
