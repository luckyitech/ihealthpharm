package com.ihealthpharm.masters.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Entity (name="CUSTOMER")
@Data
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
	    private int auditId;

	    @Column(name="CITY_NM",length=50)
	    private String cityNm;

	    @Column(name="COUNTRY_NM",length=50)
	    private String countryNm;

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

	    @Column(name="MAXIMUM_DRUGS_PER_PRESCRIPTION",length=11)
	    private int maximumDrugsPerPrescription;

	    @Column(name="ORGANISATION",length=50)
	    private String organisation;

	   
	    @Column(name="PHONE_NO",length=20)
	    private String phoneNo;

	    @Column(name="PIN_NO",length=20)
	    private String pinNo;

	    @Column(name="STATE",length=50)
	    private String state;
	    
	    @Column(name="MEMBERSHIP_CARD_PERSONAL_ID",length=11)
	    private int membershipCardPersonalId;
	    
	    @Column(name="INSURANCE_POLICY_ID",length=11)
	    private int insurancePolicyId;
	    
	    @Column(name="PHARMACY_ID",length=11)
	    private int pharmacyId;

	}