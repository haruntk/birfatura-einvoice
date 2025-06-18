package com.htk.e_invoice_integration.mapper;

import org.mapstruct.Mapper;

import com.htk.e_invoice_integration.dto.InvoiceDTO;
import com.htk.e_invoice_integration.dto.InvoiceRequestDTO;
import com.htk.e_invoice_integration.dto.InvoiceResponseDTO;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
	InvoiceRequestDTO toInvoiceRequest(InvoiceDTO invoiceDto);
	InvoiceDTO toInvoiceDTO(InvoiceResponseDTO invoiceResponse);
}
