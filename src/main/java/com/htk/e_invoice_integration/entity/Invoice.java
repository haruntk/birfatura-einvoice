package com.htk.e_invoice_integration.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "invoice_no", nullable = false, length = 255)
	private String invoiceNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "invoice_date", nullable = false)
	private LocalDate invoiceDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
	@Column(name = "invoice_time", nullable = false)
	private LocalTime invoiceTime;

	@Column(name = "invoice_type", length = 255)
	private String invoiceType;

	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "status_code")
	private Integer statusCode;

	@Column(name = "uuid", nullable = false, length = 255)
	private String uuid;

	@Column(name = "total_amount", nullable = false, precision = 8, scale = 2)
	private BigDecimal totalAmount;

	@Column(name = "pdf_url", length = 255)
	private String pdfUrl;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdated() {
		this.updatedAt = LocalDateTime.now();
	}

	@PreRemove
	public void onDeleted() {
		this.deletedAt = LocalDateTime.now();
	}
}
