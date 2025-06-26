package com.htk.e_invoice_integration.integration.birfatura.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.htk.e_invoice_integration.dto.InvoiceRequestDTO;
import com.htk.e_invoice_integration.integration.birfatura.request.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class XMLBuilderService {
	
	private final XmlMapper xmlMapper;
	
	public String buildInvoiceXml(InvoiceRequestDTO request) throws Exception {
	        Invoice invoice = buildInvoice(request);
	        return xmlMapper.writeValueAsString(invoice);
	}
	
    private Invoice buildInvoice(InvoiceRequestDTO request) {
        Invoice invoice = new Invoice();

        // UBL Extensions
        invoice.setUblExtensions(createUBLExtensions());

        // Temel bilgiler
        invoice.setId(request.getInvoiceNo());
        invoice.setUuid(request.getUuid());
        invoice.setIssueDate(request.getInvoiceDate().toString());
        invoice.setIssueTime(formatIssueTime(request.getInvoiceTime()));
        invoice.setInvoiceTypeCode(request.getInvoiceType() != null ? request.getInvoiceType() : "SATIS");
        invoice.setNotes(createNotes(request));
        invoice.setLineCountNumeric(1); // Should be calculated based on lines

        // References
        if (request.getOrderId() != null) {
            invoice.setOrderReference(createOrderReference(request.getOrderId(), request.getInvoiceDate()));
        }
        invoice.setAdditionalDocumentReferences(createAdditionalDocumentReferences(request));

        // Parties and Signature
        invoice.setSignature(createSignature());
        invoice.setSupplierParty(createSupplierParty());
        if (request.getCustomerId() != null) {
            invoice.setCustomerParty(createCustomerParty(request.getCustomerId()));
        }
        invoice.setDelivery(createDelivery(request.getCustomerId()));

        // Financials
        invoice.setTaxTotal(createTaxTotal(request.getTotalAmount()));
        invoice.setLegalMonetaryTotal(createLegalMonetaryTotal(request.getTotalAmount()));
        invoice.setInvoiceLines(createInvoiceLines(request));

        return invoice;
    }
	
    private List<UBLExtension> createUBLExtensions() {
        UBLExtension extension = new UBLExtension();
        extension.setExtensionContent(new ExtensionContent());
        return Collections.singletonList(extension);
    }
	
    private OrderReference createOrderReference(Long orderId, LocalDate issueDate) {
        OrderReference orderRef = new OrderReference();
        orderRef.setId(orderId.toString());
        orderRef.setIssueDate(issueDate.toString());
        return orderRef;
    }
    
    private String formatIssueTime(LocalTime time) {
        if (time == null) {
            time = LocalTime.now();
        }
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS")) + "+03:00";
    }
    
    private List<String> createNotes(InvoiceRequestDTO request) {
        List<String> notes = new ArrayList<>();
        notes.add("Yalnız " + convertAmountToWords(request.getTotalAmount()) + "<br/>");
        String eInvoiceNote = "<br>E-Fatura izni kapsamında elektronik ortamda iletilmiştir. <br/>" +
                "Ödeme Yöntemi: Kredi Kartı İle Ödendi - Sipariş No:" + request.getOrderId() + 
                " - Kargo Kampanya Kodu:3321733760237447 - Kargo Şirketi:Aras - " +
                "Teslimat Bilgileri: TEST EFİRMA Beytepe Mahallesi, Çankaya/Ankara Çankaya Ankara";
        notes.add(eInvoiceNote);
        return notes;
    }
	
    private List<AdditionalDocumentReference> createAdditionalDocumentReferences(InvoiceRequestDTO request) {
        List<AdditionalDocumentReference> references = new ArrayList<>();
        String issueDateStr = request.getInvoiceDate().toString();

        references.add(createDocRef(request.getUuid(), issueDateStr, "CUST_INV_ID", null));
        references.add(createDocRef("0100", issueDateStr, "OUTPUT_TYPE", null));
        references.add(createDocRef("99", issueDateStr, "TRANSPORT_TYPE", null));
        references.add(createDocRef("ELEKTRONIK", issueDateStr, "EREPSENDT", null));
        references.add(createDocRef("0", issueDateStr, "SendingType", "KAGIT"));
        references.add(createDocRef("FIT" + request.getInvoiceNo(), issueDateStr, null, "XSLT"));
        references.add(createDocRef("urn:mail:defaultpk@deneme.com", issueDateStr, "recvpk", null));

        return references;
    }

    private AdditionalDocumentReference createDocRef(String id, String issueDate, String docTypeCode, String docType) {
        AdditionalDocumentReference ref = new AdditionalDocumentReference();
        ref.setId(id);
        ref.setIssueDate(issueDate);
        if (docTypeCode != null) ref.setDocumentTypeCode(docTypeCode);
        if (docType != null) ref.setDocumentType(docType);
        return ref;
    }
	
    private Signature createSignature() {
        Signature signature = new Signature();
        signature.setId(new Identifier("VKN_TCKN", "1234567801"));

        SignatoryParty signatoryParty = new SignatoryParty();
        
        PartyIdentification partyId = new PartyIdentification();
        partyId.setId(new Identifier("VKN", "1234567801"));
        signatoryParty.setPartyIdentification(partyId);

        Address address = new Address();
        address.setStreetName("Kuşkavağı, Belediye Cd. No:78, 07070 Konyaaltı/Antalya");
        address.setCitySubdivisionName("Konyaaltı");
        address.setCityName("Antalya");
        Country country = new Country();
        country.setName("TÜRKİYE");
        address.setCountry(country);
        signatoryParty.setPostalAddress(address);
        
        signature.setSignatoryParty(signatoryParty);

        DigitalSignatureAttachment dsAttachment = new DigitalSignatureAttachment();
        ExternalReference extRef = new ExternalReference();
        extRef.setUri("#Signature");
        dsAttachment.setExternalReference(extRef);
        signature.setDigitalSignatureAttachment(dsAttachment);

        return signature;
    }
	
	private AccountingSupplierParty createSupplierParty() {
        AccountingSupplierParty supplierParty = new AccountingSupplierParty();
        Party party = new Party();
        party.setWebsiteURI(null);

        List<PartyIdentification> identifications = new ArrayList<>();
        PartyIdentification vknId = new PartyIdentification();
        vknId.setId(new Identifier("VKN", "1234567801"));
        identifications.add(vknId);

        PartyIdentification tradeRegId = new PartyIdentification();
        tradeRegId.setId(new Identifier("TICARETSICILNO", null));
        identifications.add(tradeRegId);

        PartyIdentification mersisId = new PartyIdentification();
        mersisId.setId(new Identifier("MERSISNO", null));
        identifications.add(mersisId);
        party.setIdentifications(identifications);

        PartyName partyName = new PartyName();
        partyName.setName("Test Firma");
        party.setPartyName(partyName);

        Address address = new Address();
        address.setStreetName("Kuşkavağı, Belediye Cd. No:78, 07070 Konyaaltı/Antalya");
        address.setCitySubdivisionName("Konyaaltı");
        address.setCityName("Antalya");
        Country country = new Country();
        country.setName("Türkiye");
        address.setCountry(country);
        party.setPostalAddress(address);

        PartyTaxScheme partyTaxScheme = new PartyTaxScheme();
        TaxScheme taxScheme = new TaxScheme();
        taxScheme.setName("Antalya");
        partyTaxScheme.setTaxScheme(taxScheme);
        party.setTaxScheme(partyTaxScheme);

        Contact contact = new Contact();
        contact.setTelephone("05555555555");
        contact.setElectronicMail("info@firma.com");
        party.setContact(contact);
        
        supplierParty.setParty(party);
        return supplierParty;
    }
	
    private AccountingCustomerParty createCustomerParty(Long customerId) {
        AccountingCustomerParty customerParty = new AccountingCustomerParty();
        Party party = new Party();

        PartyIdentification partyId = new PartyIdentification();
        partyId.setId(new Identifier("VKN", "1234567891"));
        party.setIdentifications(Collections.singletonList(partyId));

        PartyName partyName = new PartyName();
        partyName.setName("Test Firması");
        party.setPartyName(partyName);

        Address address = new Address();
        address.setStreetName("Beytepe Mahallesi, Çankaya/ankara");
        address.setCitySubdivisionName("Çankaya");
        address.setCityName("Ankara");
        Country country = new Country();
        country.setName("Türkiye");
        address.setCountry(country);
        party.setPostalAddress(address);
        
        PartyTaxScheme partyTaxScheme = new PartyTaxScheme();
        TaxScheme taxScheme = new TaxScheme();
        taxScheme.setName("Ankara Kurumlar Vergi Dairesi Müdürlüğü");
        partyTaxScheme.setTaxScheme(taxScheme);
        party.setTaxScheme(partyTaxScheme);
        
        party.setContact(new Contact());

        customerParty.setParty(party);
        // Contact
        Contact contact = new Contact();
        contact.setTelephone(null);
        contact.setElectronicMail(null);
        party.setContact(contact);

        customerParty.setParty(party);
        return customerParty;
    }
	
    private Delivery createDelivery(Long customerId) {
        Delivery delivery = new Delivery();

        Address deliveryAddress = new Address();
        deliveryAddress.setStreetName("Talatpaşa Cad.");
        deliveryAddress.setCitySubdivisionName("Ümraniye");
        deliveryAddress.setCityName("İstanbul");
        Country deliveryCountry = new Country();
        deliveryCountry.setName("Türkiye");
        deliveryAddress.setCountry(deliveryCountry);
        delivery.setDeliveryAddress(deliveryAddress);

        // Carrier Party
        CarrierParty carrierParty = new CarrierParty();
        PartyIdentification carrierPartyId = new PartyIdentification();
        carrierPartyId.setId(new Identifier("VKN", "1234567890"));
        carrierParty.setPartyIdentification(carrierPartyId);
        
        PartyName carrierName = new PartyName();
        carrierName.setName("Aras Kargo");
        carrierParty.setPartyName(carrierName);
        
        Address carrierAddress = new Address();
        carrierAddress.setStreetName("Rüzgarlıbahçe Mah. Yavuz Sultan Selim Cad.");
        carrierAddress.setBuildingName("Aras Plaza");
        carrierAddress.setBuildingNumber("2");
        carrierAddress.setCitySubdivisionName("Beykoz");
        carrierAddress.setCityName("İstanbul");
        carrierAddress.setPostalZone("34000");
        Country carrierCountry = new Country();
        carrierCountry.setName("Türkiye");
        carrierAddress.setCountry(carrierCountry);
        carrierParty.setPostalAddress(carrierAddress);
        
        delivery.setCarrierParty(carrierParty);


        // Delivery Party
        DeliveryParty deliveryParty = new DeliveryParty();
        
        PartyIdentification deliveryPartyId = new PartyIdentification();
        deliveryPartyId.setId(new Identifier(null, null)); 
        deliveryParty.setPartyIdentification(deliveryPartyId);

        PartyName deliveryPartyName = new PartyName();
        deliveryPartyName.setName("Teslimat yapılacak isim");
        deliveryParty.setPartyName(deliveryPartyName);

        Address deliveryPartyAddress = new Address();
        deliveryPartyAddress.setStreetName("Talatpaşa Cad. Park Sok.");
        deliveryPartyAddress.setBuildingNumber("35-1");
        deliveryPartyAddress.setCitySubdivisionName("Ümraniye");
        deliveryPartyAddress.setCityName("İstanbul");
        deliveryPartyAddress.setPostalZone("34000");
        Country deliveryPartyCountry = new Country();
        deliveryPartyCountry.setName("Türkiye");
        deliveryPartyAddress.setCountry(deliveryPartyCountry);
        deliveryParty.setPostalAddress(deliveryPartyAddress);

        Person person = new Person();
        person.setFirstName("Teslim alacak kişi isim");
        person.setFamilyName("Teslim alacak kişi soyisim");
        deliveryParty.setPerson(person);

        delivery.setDeliveryParty(deliveryParty);
        return delivery;
    }
	
    private TaxTotal createTaxTotal(BigDecimal totalAmount) {
        // Bu değerler örnek XML'e göre sabit girilmiştir.
        // Gerçekte bu değerler request'ten veya hesaplamalardan gelmelidir.
        TaxTotal taxTotal = new TaxTotal();
        taxTotal.setTaxAmount(new Amount("TRY", new BigDecimal("0.20")));

        TaxSubtotal subtotal = new TaxSubtotal();
        subtotal.setTaxableAmount(new Amount("TRY", new BigDecimal("1.00")));
        subtotal.setTaxAmount(new Amount("TRY", new BigDecimal("0.20")));
        subtotal.setPercent(new BigDecimal("20"));
        
        TaxCategory category = new TaxCategory();
        TaxScheme scheme = new TaxScheme();
        scheme.setName("KDV");
        scheme.setTaxTypeCode("0015");
        category.setTaxScheme(scheme);
        subtotal.setTaxCategory(category);
        
        taxTotal.setTaxSubtotal(Collections.singletonList(subtotal));
        return taxTotal;
    }
	
    private LegalMonetaryTotal createLegalMonetaryTotal(BigDecimal totalAmount) {
        // Bu değerler örnek XML'e göre sabit girilmiştir.
        LegalMonetaryTotal total = new LegalMonetaryTotal();
        total.setLineExtensionAmount(new Amount("TRY", new BigDecimal("1.00")));
        total.setTaxExclusiveAmount(new Amount("TRY", new BigDecimal("1.00")));
        total.setTaxInclusiveAmount(new Amount("TRY", new BigDecimal("1.20")));
        total.setAllowanceTotalAmount(new Amount("TRY", new BigDecimal("0.00")));
        total.setPayableAmount(new Amount("TRY", new BigDecimal("1.20")));
        return total;
    }
    private List<InvoiceLine> createInvoiceLines(InvoiceRequestDTO request) {
        // Bu değerler örnek XML'e göre sabit girilmiştir.
        InvoiceLine line = new InvoiceLine();
        line.setId("1");
        line.setInvoicedQuantity(new Quantity("NIU", new BigDecimal("1.0000")));
        line.setLineExtensionAmount(new Amount("TRY", new BigDecimal("1.00")));

        line.setTaxTotal(createTaxTotal(request.getTotalAmount()));

        Item item = new Item();
        item.setName("Cep Telefonu Aksesuarı");
        
        ItemIdentification sellersId = new ItemIdentification();
        sellersId.setId("56898T10Stani1805");
        item.setSellersItemIdentification(sellersId);
        
        line.setItem(item);

        Price price = new Price();
        price.setPriceAmount(new Amount("TRY", new BigDecimal("1.000000")));
        line.setPrice(price);

        return Collections.singletonList(line);
    }
    private String convertAmountToWords(BigDecimal amount) {
        // Bu metodun implementasyonu gereklidir. Örnek olarak sabit bir değer dönülüyor.
        return "BirTürkLirasıYirmiKuruş";
    }
}
