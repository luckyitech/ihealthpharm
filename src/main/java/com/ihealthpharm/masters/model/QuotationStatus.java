package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="quotation_status")
public class QuotationStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="QUOTATION_STATUS_ID", length=11)
	private int quotationStatusId;
	
	@Column(name ="STATUS", length=20)
	private String Status;
}
