package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="customer")
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
	private Integer customerId;

	@Column( name = "PHONE_NBR", length=20)
	private String phoneNumber;

	@Column( name = "FIRST_NM",length=50)
	private String customerName;

	@Column( name = "LAST_NM",length=50)
	private String lastName;

	@Column( name = "GENDER_CD",length=1)
	private Character genderCode;

	@Column( name = "ACTIVE_S",length=1)
	private Character activeS;

	@Column( name = "DOB_DT")
	private String dateOfBirth;

	@Column( name = "EMAIL_ID",length=50)
	private String emailId;

	@Column( name = "ADDRESS_LINE1",length=250)
	private String addressLine1;

	@Column( name = "ADDRESS_LINE2",length=250)
	private String addressLine2;

	@Column(name = "CITY_NM",length=50)
	private String city;

	@OneToOne
	@JoinColumn(name="PROVINCES_ID")
	private StateModel state;

	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel country;

	@Column( name = "PIN_CD" ,length=20)
	private String pinCode;

	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name="CREDIT_LIMIT",length=25)
	private Float creditLimit;

	@Column(name="CREDIT_DAYS",length=11)
	private Float creditDays;

	@Column(name="DISCOUNT_PERCENTAGE",length=25)
	private Float discountPercentage;

	@Column(name="DISCOUNT_AMOUNT",length=25)
	private Float discountAmount;

	@Column(name="ORGANISATION",length=50)
	private String organisation;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;

	public CustomerModel(Integer customerId, String customerName,String phoneNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
	}

	public CustomerModel() {

	}

	public CustomerModel(Integer customerId, String phoneNumber, String customerName, String lastName,
			Character genderCode, Character activeS, String dateOfBirth, String emailId, String addressLine1,
			String addressLine2, String city, StateModel state, CountryModel country, String pinCode,
			Integer auditId, Float creditLimit, Float creditDays, Float discountPercentage, Float discountAmount,
			String organisation, PharmacyModel pharmacyModel) {
		super();
		this.customerId = customerId;
		this.phoneNumber = phoneNumber;
		this.customerName = customerName;
		this.lastName = lastName;
		this.genderCode = genderCode;
		this.activeS = activeS;
		this.dateOfBirth = dateOfBirth;
		this.emailId = emailId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
		this.auditId = auditId;
		this.creditLimit = creditLimit;
		this.creditDays = creditDays;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.organisation = organisation;
		this.pharmacyModel = pharmacyModel;
	}

}