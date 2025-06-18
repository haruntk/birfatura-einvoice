package com.htk.e_invoice_integration.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDTO {
	private String invoiceNo;
	private LocalDate invoiceDate;
	private LocalTime invoiceTime;
	private String invoiceType;
	private Long orderId;
	private Long customerId;
	private Integer statusCode;
	private String uuid;
	private BigDecimal totalAmount;
	private String pdfUrl;
}
