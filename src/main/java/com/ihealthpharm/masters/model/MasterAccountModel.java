package com.ihealthpharm.masters.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="master_account")
@EqualsAndHashCode(of="MASTER_ACCOUNT_ID",callSuper=false)
public class MasterAccountModel extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MASTER_ACCOUNT_ID",length=11)
	private Integer masterAccountId;
	
	@Column(name="CREDIT_NUMBER",nullable = false)
	private String creditNumber;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerId;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;
	
	 @OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY,
	            mappedBy = "masterAccountId")
	private Set<FamilyAccountModel> familyAccounts = new HashSet<>();
}
