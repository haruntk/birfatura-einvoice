package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Delivery {
    @JacksonXmlProperty(localName = "DeliveryAddress", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Address deliveryAddress;

    @JacksonXmlProperty(localName = "CarrierParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private CarrierParty carrierParty;

    @JacksonXmlProperty(localName = "DeliveryParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private DeliveryParty deliveryParty;
}
