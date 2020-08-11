package com.ihealthpharm.masters.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="master_account")
@EqualsAndHashCode(of="MASTER_ACCOUNT_ID",callSuper=false)
public class MasterAccountModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6640335887264138901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MASTER_ACCOUNT_ID",length=11)
	private Integer masterAccountId;
	
	@Column(name="CREDIT_NUMBER",nullable = false)
	private String creditNumber;
	
	@Column(name="CREDIT_LIMIT",nullable = false)
	private Integer creditLimit;
	
	@Column(name="CREDIT_DAYS",nullable = false)
	private Integer creditDays;
	
	@Column(name="CREDIT_LIMIT_LEFT")
	private Integer creditLimitLeft;
	
	@Column(name="CREDIT_DAYS_LEFT")
	private Integer creditDaysLeft;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerId;
	
	@Column(name="PHARMACY_ID")
	private Integer pharmacyId;
	
	@Column(name="ENTRY_TYPE")
	private String entryType ;
	
	@Column(name="ACC_REC_NO")
	private String accRevNo;
	
	@Column(name="SALES_BILL_NO")
	private String salesBillNo ;
	
	@JsonManagedReference
	 @OneToMany(cascade = CascadeType.ALL,
	            mappedBy = "masterAccountId",orphanRemoval = true)
	private List<FamilyAccountModel> familyAccounts = new ArrayList<>();
}
