package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class UBLExtension {
    @JacksonXmlProperty(localName = "ExtensionContent", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
    private ExtensionContent extensionContent;
}