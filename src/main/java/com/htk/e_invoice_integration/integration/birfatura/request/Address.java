package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Address {
    @JacksonXmlProperty(localName = "StreetName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String streetName;

    @JacksonXmlProperty(localName = "BuildingName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String buildingName;

    @JacksonXmlProperty(localName = "BuildingNumber", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String buildingNumber;

    @JacksonXmlProperty(localName = "CitySubdivisionName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String citySubdivisionName;

    @JacksonXmlProperty(localName = "CityName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String cityName;

    @JacksonXmlProperty(localName = "PostalZone", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String postalZone;

    @JacksonXmlProperty(localName = "Country", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Country country;
}
