package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Vikas
 *	AllergyModel Models refers the type of the Drug Allergies
 *	Setter, getters by default added no need to add manually
 */

@Getter
@Setter
@ToString
@Entity(name = "allergies")
@EqualsAndHashCode(of = "allergyId", callSuper = false)
public class AllergyModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allergy_id")
	private Integer allergyId;
	
	@Column(name = "allergy_name")
	private String allergyName;
	
	@Column(name = "ACTIVE_S")
	private String activeS;
	
	
	
	
}
