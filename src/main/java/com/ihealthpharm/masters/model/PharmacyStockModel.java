package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy_stock_points")
@EqualsAndHashCode(of="stockPointId",callSuper=false)
public class PharmacyStockModel extends AuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8278019257547205835L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_STOCK_POINTS_ID",length=11)
	private int stockPointId;

	@ManyToOne
	@JoinColumn(name="PHARMACY_BRANCH_ID")
	@JsonBackReference
	private PharmacyBranchModel pharmacyBranch;
	
	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
	@Column(name="STOCK_POINT_NM",length=50)
	private String stockPointName;
	
	@Column(name="STOCK_POINT_TYPE",length=20)
	private String stockPointType;
	
	@Column(name="STOCK_POINT_ALIAS_NM",length=20)
	private String stockPointAliasName;
	
	
	@Column(name="TRANSACTION_TYPE",length=2)
	private char transactionType;
	
	@Column(name="RETURN_APPLICABLE",length=1)
	private char returnApplicable;
	
	
	@Column(name="OT_TAX_APPLICABLE",length=1)
	private char otTaxApplicable;
	
	@Column(name="PAYMENT_TYPE",length=20)
	private String paymentType;
	
	@Column(name="DISCOUNT_FACILITY",length=1)
	private char discountFacility;
	
}
