package com.ihealthpharm.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the invoice_status database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name="invoice_status")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class InvoiceStatusModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INVOICE_STATUS_ID")
	private Integer invoiceStatusId;

	@Column(name="STATUS", length=20)
	private String status;

}