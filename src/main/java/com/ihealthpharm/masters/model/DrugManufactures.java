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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Vikas
 *	DrugManufactures Persists the Drug and Manufactures details 
 *	Setter, getters by default added no need to add manually
 */
@Data
@Entity(name = "drug_manufactures")
@EqualsAndHashCode(of = "drugManufactureId", callSuper = false)
public class DrugManufactures extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drug_manufacture_id")
	private Integer drugManufactureId;
	
	// Drug Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="drug_id")
	@JsonBackReference
	private ItemsModel drugModel;
	
	// manufacturer Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="manufacture_id")
	private ManufacturerModel manufacturer;
	
	@Column(name = "ACTIVE_S")
	private String activeS;
	

	
}
