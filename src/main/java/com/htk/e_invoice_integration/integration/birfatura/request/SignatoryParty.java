package com.htk.e_invoice_integration.integration.birfatura.request;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SignatoryParty {
    @JacksonXmlProperty(localName = "PartyIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private PartyIdentification partyIdentification;

    @JacksonXmlProperty(localName = "PostalAddress", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Address postalAddress;
}
