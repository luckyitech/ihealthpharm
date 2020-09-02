package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="family_account")
@EqualsAndHashCode(of="familyAccountId",callSuper=false)
public class FamilyAccountModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2420948989126747435L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FAMILY_ACCOUNT_ID",length=11)
	private Integer familyAccountId;
	
	@Column(name="CREDIT_NUMBER",nullable = false)
	private String creditNumber;
	
	@Column(name="CREDIT_LIMIT",nullable = false)
	private Integer creditLimit;
	
	@Column(name="CREDIT_DAYS",nullable = false)
	private Integer creditDays;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerId;
	
	@Column(name="PHARMACY_ID")
	private Integer pharmacyId;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "MASTER_ACCOUNT_ID", nullable = false)
	private MasterAccountModel masterAccountId;
}
