package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy")
@EqualsAndHashCode(of="pharmacyId",callSuper=false)
public class PharmacyModel extends AuditModel{

	private static final long serialVersionUID = 2083116791806694813L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_ID",length=11)
	private Integer pharmacyId;
	
	@Column(name="MAIN_PHARMACY_ID",length=11)
	private Integer mainPharmacyId;

	@Column(name="PHARMACY_NM",length=250)
	private String pharmacyName;
	
	@Column(name="TAX_ID",length=20)
	private String taxId;

	@Column(name="PHONE_NBR",length=20)
	private String phoneNumber;

	@Column(name="EMAIL_ID",length=50)
	private String emailId;
	
	@Column(name ="AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
	@Column(name="AUTHORIZED_PERSON_FIRST_NM",length=50)
	private String autherizedPersonFirstName;
	
	@Column(name="AUTHORIZED_PERSON_MIDDLE_NM",length=50)
	private String autherizedPersonMiddleName;
	
	@Column(name="AUTHORIZED_PERSON_LAST_NM",length=50)
	private String autherizedPersonLastName;
	
	@Column(name="AUTHORIZED_PERSON_EMAIL_ID",length=50)
	private String authPersonEmail;
	
	@Column(name="AUTHORIZED_PERSON_PHONE_NBR",length=20)
	private String autherizedPersonNumber;

	@Column(name="PHARMACY_LOGO_PATH",length=500)
	private String pharmacyLogoPath;
	
	@Column(name="WEBSITE_URL",length=500)
	private String websiteUrl;
	
	@Column(name="FAX_NBR",length=20)
	private String fax;
	
	@Column(name="PURCHASE_ORDER_APPROVAL",length=1)
	private char purchaseOrderApproval;


}
