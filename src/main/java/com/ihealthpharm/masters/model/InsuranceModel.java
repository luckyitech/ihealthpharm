package com.ihealthpharm.masters.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="insurance")
@Data
@EqualsAndHashCode(of="insurancePolicyId",callSuper=false)
public class InsuranceModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240443844583760568L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="INSURANCE_POLICY_ID",length=11)
	private Integer insurancePolicyId;

	@Column(name="POLICY_CD",length=100)
	private String policyCode;

	@Column(name="POLICY_DESC",length=200)
	private String policyDescription;

	@Column(name="COMPANY_NM",length=100)
	private String companyName;

	@Column(name="POLICY_START")
	private String policyStartDate;

	@Column(name="POLICY_END")
	private String policyEndDate;

	@Column(name="POLICY_DURATION_IN_MONTHS",length=11)
	private int policyDurationInMonths;

	@Column(name="POLICY_AMOUNT_LIMIT")
	private Float policyAmountLimit;

	@Column(name="MEDICINES_NOT_COVERED",length=1000)
	private String medicinesNotCovered;

	@Column(name="TERMS_AND_CONDITIONS",length=250)
	private String termsAndConditions;

	@Lob
	@Column(name="TERMS_AND_CONDITIONS_FILE")
	private byte[] termsAndConditionsFile;


	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name = "ACTIVE_S",length=1)
	private char activeS;

	@Column(name="ADDRESS_LINE1",length=250)
	private String addressLine1;

	@Column(name="ADDRESS_LINE2",length=250)
	private String addressLine2;

	@Column(name="CONTACT_FIRST_NM")
	private String contactPersonFirstName;

	@Column(name="CONTACT_LAST_NM")
	private String conatctPersonLastName;

	@Column(name="CONTACT_PERSON_EMAIL_ID")
	private String contactPersonEmailId;

	@Column(name="CONTACT_PERSON_PHONE_NBR")
	private String contactPersonPhoneNumber;


	@Column(name="CITY_NM",length=50)
	private String cityName;

	@Column(name="ZIP_CD",length=10)
	private String zipCode;

	@OneToOne
	@JoinColumn(name="PROVINCES_ID")
	private StateModel state;

	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel country;

	@Column(name="CONTACT_NBR",length=20)
	private String contactNumber;

	@Column(name="EMAIL_ID",length=50)
	private String emailId;


	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyId;

	public void setPolicyStartDate(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String policyStart=simpleDateFormat.format(startDate);  
		this.policyStartDate = policyStart;
	}

	public void setPolicyEndDate(Date policyEnd)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=simpleDateFormat.format(policyEnd);  
		this.policyEndDate = endDate;
	}



}
