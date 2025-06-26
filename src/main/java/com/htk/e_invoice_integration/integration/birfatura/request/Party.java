package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class Party {
    @JacksonXmlProperty(localName = "WebsiteURI", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String websiteURI;

    @JacksonXmlProperty(localName = "PartyIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PartyIdentification> identifications;

    @JacksonXmlProperty(localName = "PartyName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private PartyName partyName;

    @JacksonXmlProperty(localName = "PostalAddress", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Address postalAddress;

    @JacksonXmlProperty(localName = "PartyTaxScheme", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private PartyTaxScheme taxScheme;

    @JacksonXmlProperty(localName = "Contact", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Contact contact;
}
