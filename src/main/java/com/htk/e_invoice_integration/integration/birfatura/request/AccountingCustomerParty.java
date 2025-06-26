package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountingCustomerParty {
    @JacksonXmlProperty(localName = "Party", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Party party;
}
