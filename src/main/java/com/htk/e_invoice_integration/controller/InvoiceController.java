package com.htk.e_invoice_integration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htk.e_invoice_integration.dto.InvoiceDTO;
import com.htk.e_invoice_integration.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InvoiceController {
	private final InvoiceService invoiceService;
	
	@PostMapping("sendDocument")
	public ResponseEntity<InvoiceDTO> sendDocument(@RequestBody InvoiceDTO invoiceDto) {
		invoiceService.createInvoice(invoiceDto);
		return ResponseEntity.ok(invoiceDto);
	}

}
