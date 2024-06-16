package com.valuelabs.shipment.service;

import com.valuelabs.shipment.entity.DeliveryPartner;

import java.util.List;

public interface DeliveryPartnerService {


    public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner);

    public List<DeliveryPartner> getAllDeliveryPartners();

    public void deleteDeliveryPartner(Long id);


}
