package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity(name="provider")
public class ProviderModel extends AuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="PROVIDER_ID")
	private Integer providerId ;
	
	@Column(name = "FIRST_NM")
	private String firstName;
	
	@Column(name = "MIDDLE_NM")
	private String middleName;
	
	@Column(name = "LAST_NM")
	private String lastName;
	
	@Column(name = "PHONE_NBR")
	private String phoneNumber;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
		
	@Column(name = "ACTIVE_S")
	private char activeS;
	
	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;
	
	@Column(name = "CITY_NM")
	private String cityName;
	
	@OneToOne
	@JoinColumn(name="PROVINCES_ID")
	private StateModel state;
	
	@Column(name = "ZIP_CD")
	private String zipCode;
	
	@Column(name = "CREDIT")
	private String credit;
	
	@Column(name = "LICENSE_NUMBER")
	private String licenseNumber;
	
	@Column(name = "DEA_NUMBER")
	private String deaNumber;
	
	@Column(name = "GENDER_CD")
	private String   genderCode;
	 
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "SPECIALITY")
	private String speciality;
	
	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel country;
	
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "PROVIDER_TYPE_LOOKUP_ID")
	private ProviderLookupTypeModel providerLookupTypeModel;

}
