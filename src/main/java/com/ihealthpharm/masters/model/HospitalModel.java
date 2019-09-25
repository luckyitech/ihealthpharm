package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author Vikas
 *tarun changed somedata
 */


@Data
@Entity(name = "hospital")
public class HospitalModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOSPITAL_ID")
	private Integer hospitalId;
	
	@Column(name = "NAME")
	private String hospitalName;
	
	@Column(name = "LICENSE")
	private String license;
	
	@Column(name = "PHONE_NBR")
	private String phoneNumber;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "FAX")
	@Size(max=20)
	private String fax;
	
	@Column(name = "ACTIVE_S",  columnDefinition = "default 'Y'")
	private String activeS = "Y";
	
	@Column(name = "ADDRESS_LINE1")
	@Size(max=250)
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE2")
	@Size(max=250)
	private String addressLine2;
	
	@Column(name = "CITY_NM")
	@Size(max=50)
	private String cityName;
	
	
	@ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn(name="PROVINCES_ID")
    private StateModel state;
	
	@Column(name = "ZIP_CD")
	@Size(max=10)
	private String zipCode;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="COUNTRY_ID")
    private CountryModel country;
	
	@Column(name = "WEBSITE")
	@Size(max=50)
	private String website;
	
	@Column(name = "HELPLINE")
	@Size(max=50)
	private String helpLine;

	@Column(name = "CREATION_TS")
	private Date creationTimeStamp = new Date();
	
	@Column(name = "CREATION_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "LAST_UPDATE_TS")
	private Date lastUpdatedTimeStamp = new Date();
	
	@Column(name = "LAST_UPDATE_USER_ID")
	private Integer lastUpdatedUserId;
	
	@Column(name = "AUDIT_ID")
	private Integer auditId;
	
	@Column(name="CONTACT_NBR")
	private String contactNumber;
	

}
