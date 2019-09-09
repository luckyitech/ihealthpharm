package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy_stock_points")
@EqualsAndHashCode(of="stockPointId")

public class PharmacyStockModel {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_STOCK_POINTS_ID",length=11)
	private int stockPointId;


	@ManyToOne
	@JoinColumn(name="PHARMACY_BRANCH_ID")
	@JsonManagedReference
	private PharmacyBranchModel pharmacyBranch;
	
	@CreationTimestamp
	@Column(name="CREATION_TS")
	private Date creationTimeStamp;

	@Column(name="CREATION_USER_ID",length=50)
	private String creationUserId ;

	@UpdateTimestamp
	@Column(name="LAST_UPDATE_TS")
	private Date lastUpdateTimeStamp;

	@Column(name="LAST_UPDATE_USER_ID",length=50)
	private String lastUpdatedUserId;

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
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	
}
