package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "latin_short_codes")
@EqualsAndHashCode(of = "latinShortCodeId", callSuper = false)
public class LatinShortCodesModel extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9123814982529703709L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LATIN_SHORT_CODE_ID",length=11)
	private Integer latinShortCodeId;
	
	
	@Column(name="LATIN_SHORT_CODE",length=20)
	private String latinShortCode;
	
	@Column(name="LATIN_SHORT_CODE_DESC",length=100)
	private String latinShortCodeDesc;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column(name = "ACTIVE_S",length=1)
	private char activeS;
	

}
