package com.ihealthpharm.masters.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity (name="CUSTOMER")
@Data
@EqualsAndHashCode(of="customerId",callSuper=false)
public class CustomerModel extends AuditModel{
    
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="CUSTOMER_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	    private int customerId;
	  
		@Column(name="ACTIVE_S",length=1, columnDefinition = "'Y'")
	    private char activeS;

	    @Column(name="ADDRESS_LINE1",length=250)
	    private String addressLine1;

	    @Column(name="ADDRESS_LINE2",length=250)
	    private String addressLine2;

	    @Column(name="AGE",length=11)
	    private int age;

	    @Column(name="AUDIT_ID",length=11)
	    private Integer auditId;

	    @Column(name="CITY_NM",length=50)
	    private String cityNm;

	    @Column(name="CREDIT_LIMIT",length=25)
	    private float creditLimit;

	    @Column(name="CUSTOMER_CODE",length=30)
	    private String customerCode;

	    @Column(name="CUSTOMER_LAST_NAME",length=100)
	    private String customerLastName;

	    @Column(name="CUTOMER_FIRST_NAME",length=100)
	    private String cutomerFirstName;

	    @Column(name="DISCOUNT_AMOUNT",length=25)
	    private float discountAmount;
	    
	    @Column(name="CREDIT_DAYS",length=11)
	    private float creditDays;
	    

	    @Column(name="DISCOUNT_PERCENTAGE",length=25)
	    private float discountPercentage;

	    @Column(name="DOB",length=25)
	    private LocalDate dob;

	    @Column(name="EMAIL_ID",length=50)
	    private String emailId;

	    @Column(name="GENDER",length=6)
	    private String gender;

	    
	    @Column(name="ORGANISATION",length=50)
	    private String organisation;

	   
	    @Column(name="PHONE_NO",length=20)
	    private String phoneNo;

	    @Column(name="PIN_NO",length=20)
	    private String pinNo;
	    
	    @OneToOne
		@JoinColumn(name="PROVINCES_ID")
		private StateModel state;

		@OneToOne
		@JoinColumn(name="COUNTRY_ID")
		private CountryModel country;
	    
	    @OneToOne
	    @JoinColumn(name="MEMBERSHIP_CARD_PERSONAL_ID")
	    private CustomerMembershipModel customerMembershipModel;
	   
	    @OneToOne
	    @JoinColumn(name="INSURANCE_POLICY_PERSONAL_ID")
	    private CustomerInsuranceModel customerInsuranceModel;
	   
	    @OneToOne
		@JoinColumn(name="PHARMACY_ID")
	    private PharmacyModel pharmacyModel;
	    

		 

	}