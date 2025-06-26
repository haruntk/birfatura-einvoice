package com.htk.e_invoice_integration.integration.birfatura.response;

import lombok.Data;

@Data
public class SendDocumentResponse {
	private String invoiceNo;
	private String zipped;
	private String htmlString;
	private String pdfLink;
}
