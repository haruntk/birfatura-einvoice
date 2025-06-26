package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JacksonXmlRootElement(localName = "Invoice", namespace = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @JacksonXmlProperty(localName = "UBLExtensions", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<UBLExtension> ublExtensions;

    @JacksonXmlProperty(localName = "UBLVersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String ublVersionId = "2.1";

    @JacksonXmlProperty(localName = "CustomizationID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String customizationId = "TR1.2";

    @JacksonXmlProperty(localName = "ProfileID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String profileId = "TICARIFATURA";

    @JacksonXmlProperty(localName = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String id;

    @JacksonXmlProperty(localName = "CopyIndicator", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private boolean copyIndicator = false;

    @JacksonXmlProperty(localName = "UUID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String uuid;

    @JacksonXmlProperty(localName = "IssueDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String issueDate;

    @JacksonXmlProperty(localName = "IssueTime", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String issueTime;

    @JacksonXmlProperty(localName = "InvoiceTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String invoiceTypeCode = "SATIS";

    @JacksonXmlProperty(localName = "Note", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> notes;

    @JacksonXmlProperty(localName = "DocumentCurrencyCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private String documentCurrencyCode = "TRY";

    @JacksonXmlProperty(localName = "LineCountNumeric", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    private int lineCountNumeric;

    @JacksonXmlProperty(localName = "OrderReference", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private OrderReference orderReference;

    @JacksonXmlProperty(localName = "AdditionalDocumentReference", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AdditionalDocumentReference> additionalDocumentReferences;

    @JacksonXmlProperty(localName = "Signature", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Signature signature;

    @JacksonXmlProperty(localName = "AccountingSupplierParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private AccountingSupplierParty supplierParty;

    @JacksonXmlProperty(localName = "AccountingCustomerParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private AccountingCustomerParty customerParty;

    @JacksonXmlProperty(localName = "Delivery", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private Delivery delivery;

    @JacksonXmlProperty(localName = "TaxTotal", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private TaxTotal taxTotal;

    @JacksonXmlProperty(localName = "LegalMonetaryTotal", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    private LegalMonetaryTotal legalMonetaryTotal;

    @JacksonXmlProperty(localName = "InvoiceLine", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<InvoiceLine> invoiceLines;
}
