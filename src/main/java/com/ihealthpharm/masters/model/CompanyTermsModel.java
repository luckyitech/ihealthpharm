package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="company_terms_and_conditions")
@Data
@EqualsAndHashCode(of="companyTermsId")
public class CompanyTermsModel extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPANY_TERMS_AND_CONDITIONS_ID",length=11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyTermsId;
	
	@Column(name="TERMS_AND_CONDITIONS")
	private String termsAndConditions;
			
}
