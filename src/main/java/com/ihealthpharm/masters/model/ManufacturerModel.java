package com.ihealthpharm.masters.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun
 * Setter, getters by default added no need to add manually
 */
@Data
@Entity(name="manufacturer")
@EqualsAndHashCode(of="manufacturerId",callSuper=false)

public class ManufacturerModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075784897508523329L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MANUFACTURER_ID",length=11)
	private int manufacturerId;
	
	@Column(name="NAME",length=100)
	private String name;
	
	@Column(name="LICENSE",length=100)
	private String licence;
	
	@Column(name="PHONE_NBR",length=20)
	private String phoneNumber;
	
	@Column(name="EMAIL_ID",length=50)
	private String emailId;
	
	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
	@Column(name="FAX",length=20)
	private String fax;
	
	@Column(name="CONTACT_PERSON_EMAIL_ID",length=50)
	private String contactPersonEmail;
	
	@Column(name="CONTACT_PERSON_PHONE_NBR",length=20)
	private String contactPersonPhoneNumber;
	
	@Column(name="CONTACT_PERSON_FIRST_NM",length=50)
	private String contactPersonFirstName;

	@Column(name="WEBSITE",length=500)
	private String website;
	
	@Column(name="ADDRESS_LINE1",length=250)
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE2",length=250)
	private String addressLine2;
	
	@Column(name="CITY_NM",length=50)
	private String cityName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROVINCES_ID")
	private StateModel provinceId;
	
	@Column(name="ZIP_CD",length=10)
	private String zipCode;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countryId;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column(name="CONTACT_PERSON_MIDDLE_NM",length=50)
	private String contactPersonMiddleName;
	
	@Column(name="CONTACT_PERSON_LAST_NM",length=50)
	private String contactPersonLastName;
	
}

