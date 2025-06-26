package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class InvoiceLine {
    @JacksonXmlProperty(localName = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String id;

    @JacksonXmlProperty(localName = "InvoicedQuantity", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Quantity invoicedQuantity;

    @JacksonXmlProperty(localName = "LineExtensionAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount lineExtensionAmount;

    @JacksonXmlProperty(localName = "TaxTotal", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private TaxTotal taxTotal;

    @JacksonXmlProperty(localName = "Item", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Item item;

    @JacksonXmlProperty(localName = "Price", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Price price;
}
