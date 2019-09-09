package com.ihealthpharm.masters.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Vikas
 *	DrugDistributors Persists the Drug and Distributors details 
 *	Setter, getters by default added no need to add manually
 * Audit Columns will be added from AuditModel
 */
@Getter
@Setter
@ToString
@Entity(name = "drug_allergies")
@EqualsAndHashCode(of = "drugAllergyId", callSuper = false)
public class DrugAllergies extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drug_allergy_id")
	private Integer drugAllergyId;
	
	// Drug Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="drug_id")
	@JsonBackReference
	private ItemsModel drugModel;
	
	// AllergyModel Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="allergy_id")
	private AllergyModel allergyId;
	
	@Column(name = "ACTIVE_S")
	private String activeS;
	
	
}
