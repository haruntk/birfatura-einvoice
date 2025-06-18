package com.htk.e_invoice_integration.integration.birfatura.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.htk.e_invoice_integration.dto.InvoiceRequestDTO;
import com.htk.e_invoice_integration.dto.InvoiceResponseDTO;

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
	
	public InvoiceResponseDTO sendInvoice(InvoiceRequestDTO requestDto) {
		return null;
	}

}
