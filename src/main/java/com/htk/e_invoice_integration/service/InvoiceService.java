package com.htk.e_invoice_integration.service;

import org.springframework.stereotype.Service;

import com.htk.e_invoice_integration.dto.InvoiceDTO;
import com.htk.e_invoice_integration.integration.birfatura.service.BirFaturaService;

import lombok.RequiredArgsConstructor;

// Faturaya ait is kurallarini yonetir
@Service
@RequiredArgsConstructor
public class InvoiceService {
	
	private final BirFaturaService birFaturaService;
	
	public InvoiceDTO createInvoice(InvoiceDTO invoiceDto) {
		
		birFaturaService.sendInvoice();
		
		return invoiceDto;
		
	}

}
