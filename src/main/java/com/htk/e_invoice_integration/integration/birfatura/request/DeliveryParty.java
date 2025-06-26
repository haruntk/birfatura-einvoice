package com.htk.e_invoice_integration.integration.birfatura.request;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class DeliveryParty {
    @JacksonXmlProperty(localName = "PartyIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private PartyIdentification partyIdentification;

    @JacksonXmlProperty(localName = "PartyName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private PartyName partyName;

    @JacksonXmlProperty(localName = "PostalAddress", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Address postalAddress;

    @JacksonXmlProperty(localName = "Person", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Person person;
}