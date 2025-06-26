package com.htk.e_invoice_integration.integration.birfatura.response;

import lombok.Data;

@Data
public class ApiResponse_SendDocumentResponse {
	private boolean success;
	private String message;
	private String code;
	private SendDocumentResponse result;
}
