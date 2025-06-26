package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaxTotal {
    @JacksonXmlProperty(localName = "TaxAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount taxAmount;

    @JacksonXmlProperty(localName = "TaxSubtotal", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TaxSubtotal> taxSubtotal;
}