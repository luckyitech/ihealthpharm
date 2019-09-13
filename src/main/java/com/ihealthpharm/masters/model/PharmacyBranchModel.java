package com.ihealthpharm.masters.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name="pharmacy_branch")
public class PharmacyBranchModel {


	@Id
	@Column(name="PHARMACY_BRANCH_ID",length=11)
	private int pharmacyBranchId;

	@Column(name="PHARMACY_BRANCH_NM",length=250)
	private String pharmaBranchName;

	@ManyToOne
	@JoinColumn(name="PHARMACY_ID",nullable = false, updatable = true, insertable = true)
	@JsonBackReference
	private PharmacyModel pharmacy;

	@Column(name="PHONE_NBR",length=20)
	private String phoneNumber;

	@Column(name="FAX_NBR",length=20)
	private String fax;


	@Column(name="EMAIL_ID",length=50)
	private String emailId;

	@Column(name="WEBSITE_URL",length=250)
	private String websiteUrl;


	@Column(name="OTHER_DETAILS_DESC",length=250)
	private String otherDetailsDesc;

	@Column(name="ADDRESS_LINE1",length=250)
	private String addressLine1;

	@Column(name="ACTIVE_S",length=1)
	private char activeS;

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

	@Column(name="ADDRESS_LINE2",length=250)
	private String addressLine2;

	@Column(name="CITY_NM",length=50)
	private String cityName;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROVINCES_ID")
	private StateModel provinenceId;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countryId;

	@Column(name="ZIP_CD",length=20)
	private String zipCode;

	@Column(name="24_HOURS",length=1)
	private char hours;

	@Column(name ="AUDIT_ID",length=11)
	private Integer auditId;

	@OneToMany(mappedBy="pharmacyBranch",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<PharmacyStockModel> stockPointId;

}
