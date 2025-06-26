package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Price {
    @JacksonXmlProperty(localName = "PriceAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount priceAmount;
}