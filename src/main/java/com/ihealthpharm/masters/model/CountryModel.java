package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity(name = "country_lookup")
@EqualsAndHashCode(of = "countryId")
public class CountryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_ID", length=11)
	private Integer countryId;
	
	@Column(name = "COUNTRY_NM", length=50)
	private String countryName;
	
	@Column(name = "COUNTRY_CD",length=10)
	private String countryCode;

	@Column(name = "CREATION_TS")
	private Date creationTimeStamp;
	
	@Column(name = "CREATION_USER_ID")
	private Integer createdUserId;
	  
	@Column(name = "LAST_UPDATE_TS")
	private Date lastUpdatedTimeStamp;
	
	@Column(name = "LAST_UPDATE_USER_ID")
	private Integer lastUpdatedUserId;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;

}
