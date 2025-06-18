package com.htk.e_invoice_integration.service;

import org.springframework.stereotype.Service;

import com.htk.e_invoice_integration.dto.InvoiceDTO;
import com.htk.e_invoice_integration.dto.InvoiceRequestDTO;
import com.htk.e_invoice_integration.integration.birfatura.service.BirFaturaService;
import com.htk.e_invoice_integration.mapper.InvoiceMapper;

import lombok.RequiredArgsConstructor;

// Faturaya ait is kurallarini yonetir
@Service
@RequiredArgsConstructor
public class InvoiceService {
	private final InvoiceMapper mapper;
	
	private final BirFaturaService birFaturaService;
	
	public InvoiceDTO createInvoice(InvoiceDTO invoiceDto) {
		InvoiceRequestDTO requestDto = mapper.toInvoiceRequest(invoiceDto);
		birFaturaService.sendInvoice(requestDto);
		return invoiceDto;
	}

}
