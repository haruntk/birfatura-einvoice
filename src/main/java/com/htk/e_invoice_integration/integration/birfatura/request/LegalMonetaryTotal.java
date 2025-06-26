package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class LegalMonetaryTotal {
    @JacksonXmlProperty(localName = "LineExtensionAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount lineExtensionAmount;

    @JacksonXmlProperty(localName = "TaxExclusiveAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount taxExclusiveAmount;

    @JacksonXmlProperty(localName = "TaxInclusiveAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount taxInclusiveAmount;

    @JacksonXmlProperty(localName = "AllowanceTotalAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount allowanceTotalAmount;

    @JacksonXmlProperty(localName = "PayableAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private Amount payableAmount;
}