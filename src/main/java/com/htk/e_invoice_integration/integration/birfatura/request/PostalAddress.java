package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class PostalAddress {
    @JacksonXmlProperty(localName = "StreetName")
    private String streetName;
    @JacksonXmlProperty(localName = "CitySubdivisionName")
    private String citySubdivisionName;
    @JacksonXmlProperty(localName = "CityName")
    private String cityName;
    @JacksonXmlProperty(localName = "Country")
    private Country country;
}
