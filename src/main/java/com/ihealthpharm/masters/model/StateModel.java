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

import lombok.Data;
import lombok.EqualsAndHashCode;
/*
@author tarun
*/

@Data
@Entity(name = "provinces_lookup")
@EqualsAndHashCode(of = "provinceId")
public class StateModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROVINCES_ID", length=11)
	private Integer provinceId;
	
	@Column(name = "PROVINCES_NM", length=255)
	private String provinceName;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="COUNTRY_ID")
    private CountryModel countryId;
	
	@Column(name = "CREATION_TS")
	private Date creationTimeStamp;
	
	@Column(name = "CREATION_USER_ID")
	private Integer createdUser;
	
	@Column(name = "LAST_UPDATE_TS")
	private Date lastUpdateTimeStamp;
	
	@Column(name = "LAST_UPDATE_USER_ID", length=50)
	private String lastUpdateUserId;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	
}
