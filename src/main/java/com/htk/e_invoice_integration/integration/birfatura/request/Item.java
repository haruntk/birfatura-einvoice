package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Item {
    @JacksonXmlProperty(localName = "Description", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String description;

    @JacksonXmlProperty(localName = "Name", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String name;

    @JacksonXmlProperty(localName = "BuyersItemIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private ItemIdentification buyersItemIdentification;

    @JacksonXmlProperty(localName = "SellersItemIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private ItemIdentification sellersItemIdentification;

    @JacksonXmlProperty(localName = "AdditionalItemIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private ItemIdentification additionalItemIdentification;
}