package com.htk.e_invoice_integration.integration.birfatura.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htk.e_invoice_integration.integration.birfatura.common.SystemTypeCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDocumentRequestData {
	@JsonProperty("receiverTag")
	private String receiverTag;
	@JsonProperty("documentBytes")
	private String documentBytes;
	@JsonProperty("isDocumentNoAuto")
	boolean isDocumentNoAuto;
	@JsonProperty("systemTypeCodes")
	SystemTypeCodes systemTypeCodes;
}
