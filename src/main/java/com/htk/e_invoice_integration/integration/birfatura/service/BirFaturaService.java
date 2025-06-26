package com.htk.e_invoice_integration.integration.birfatura.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.htk.e_invoice_integration.dto.InvoiceRequestDTO;
import com.htk.e_invoice_integration.dto.InvoiceResponseDTO;
import com.htk.e_invoice_integration.integration.birfatura.common.SystemTypeCodes;
import com.htk.e_invoice_integration.integration.birfatura.request.SendDocumentRequestData;
import com.htk.e_invoice_integration.integration.birfatura.response.ApiResponse_SendDocumentResponse;
import com.htk.e_invoice_integration.integration.birfatura.response.SendDocumentResponse;
import com.htk.e_invoice_integration.mapper.InvoiceMapper;

import lombok.RequiredArgsConstructor;

// BirFatura ile ilgili tum islemler
@Service
@RequiredArgsConstructor
public class BirFaturaService {
	
	@Value("${efatura.api.url}")
	private String api_url;
	
	@Value("${efatura.api.key}")
	private String api_key;
	
	@Value("${efatura.secret.key}")
	private String secret_key;
	
	@Value("${efatura.integration.key}")
	private String integration_key;
	
	private final RestClient restClient;
	private final XMLBuilderService xmlService;
	private final InvoiceMapper mapper;
	
	public InvoiceResponseDTO sendInvoice(InvoiceRequestDTO requestDto) {
		try {
			String xmlContent = xmlService.buildInvoiceXml(requestDto);
			String base64Xml = java.util.Base64.getEncoder().encodeToString(xmlContent.getBytes("UTF-8"));
			SendDocumentRequestData request = new SendDocumentRequestData();
			request.setReceiverTag(requestDto.getCustomerId().toString());
			request.setDocumentBytes(base64Xml);
			request.setDocumentNoAuto(true);
			request.setSystemTypeCodes(SystemTypeCodes.EFATURA);
			
			ApiResponse_SendDocumentResponse apiResponse = restClient.post()
			.uri(api_url + "/api/OutEBelgeV2/SendDocument")
			.contentType(MediaType.APPLICATION_JSON)
			.header("X-Api-Key", api_key)
			.header("X-Secret-Key", secret_key)
			.header("X-Integration-Key", integration_key)
			.body(request)
			.retrieve()
			.body(ApiResponse_SendDocumentResponse.class);
			SendDocumentResponse response = apiResponse.getResult();
			return mapper.toResponseDTO(response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
