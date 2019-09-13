package com.ihealthpharm.masters.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name="pharmacy")
public class PharmacyModel implements Serializable{

	private static final long serialVersionUID = 2083116791806694813L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_ID",length=11)
	private Integer pharmacyId;

	@Column(name="PHARMACY_NM",length=250)
	private String pharmacyName;
	
	@Column(name="TAX_ID",length=20)
	private String taxId;

	@Column(name="PHONE_NBR",length=20)
	private String phoneNumber;
	

	@Column(name="EMAIL_ID",length=50)
	private String emailId;
	
	@CreationTimestamp
	@Column(name="CREATION_TS")
	private Date creationTimeStamp;

	@Column(name="CREATION_USER_ID",length=50)
	private String creationUserId ;

	@UpdateTimestamp
	@Column(name="LAST_UPDATE_TS")
	private Date lastUpdateTimeStamp;

	@Column(name="LAST_UPDATE_USER_ID",length=50)
	private String lastUpdatedUserId;

	@Column(name="ACTIVE_S",length=1)
	@Size(max=1)
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
	
	
	@Column(name="ADDRESS_LINE1",length=250)
	private String addressLine1;
	
	
	@Column(name="ADDRESS_LINE2",length=250)
	private String addressLine2;

	
	@Column(name="CITY_NM",length=50)
	private String cityName;

	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROVINCES_ID")
	private StateModel provinceId;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countryId;
	
	@Column(name="ZIP_CD",length=10)
	private String zipCode;

	
	@Column(name="WEBSITE_URL",length=500)
	private String websiteUrl;
	
	@Column(name="FAX_NBR",length=20)
	private String fax;
	
	@Column(name ="AUDIT_ID",length=11)
	private Integer auditId;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<PharmacyBranchModel> pharmacyBranchId;
	
	@OneToMany(mappedBy="pharmacyModel")
	@JsonBackReference
	private List<PurchaseOrderModel> purchaseordermodel;

	
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy",cascade=CascadeType.ALL)
	@JsonBackReference
	private List<PharmacyStockModel> pharmacyStocks;
*/
}
