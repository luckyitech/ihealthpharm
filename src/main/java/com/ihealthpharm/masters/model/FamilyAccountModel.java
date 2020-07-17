package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="family_account")
@EqualsAndHashCode(of="FAMILY_ACCOUNT_ID",callSuper=false)
public class FamilyAccountModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FAMILY_ACCOUNT_ID",length=11)
	private Integer familyAccountId;
	
	@Column(name="CREDIT_NUMBER",nullable = false)
	private String creditNumber;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerId;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTER_ACCOUNT_ID", nullable = false)
	private MasterAccountModel masterAccountId;
}
