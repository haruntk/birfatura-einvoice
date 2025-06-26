package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxSubtotal {
    @JacksonXmlProperty(localName = "TaxableAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount taxableAmount;

    @JacksonXmlProperty(localName = "TaxAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount taxAmount;

    @JacksonXmlProperty(localName = "Percent", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private BigDecimal percent;

    @JacksonXmlProperty(localName = "TaxCategory", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private TaxCategory taxCategory;
}
