package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class TaxSchemeWrapper {
    @JacksonXmlProperty(localName = "cac:TaxScheme")
    private TaxScheme taxScheme;
}
