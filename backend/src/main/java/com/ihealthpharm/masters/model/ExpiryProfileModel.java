package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="expiry_profile")
@EqualsAndHashCode(of = "expiryProfileId", callSuper = false)
public class ExpiryProfileModel extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1111276975228969311L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EXPIRY_PROFILE_ID",length=11)
	private Integer expiryProfileId;
	
	
	@Column(name="EXPIRY_CD")
	private String expiryCode;
	
	@Column(name="EXPIRY_DAYS")
	private String expiryDays;
	
	
}
