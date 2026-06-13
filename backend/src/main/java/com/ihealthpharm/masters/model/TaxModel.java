package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun
 * Setter, getters by default added no need to add manually
 */

@Data
@Entity(name="tax")
@EqualsAndHashCode(of = "taxId", callSuper = false)
public class TaxModel extends AuditModel {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7204193725381295683L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TAX_ID",length=11)
	private Integer taxId;

	@Column(name="TAX_CD")
	private String taxCode;
	
	@Column(name="TAX_DESC")
	private String taxDesc;
	
	@Column(name="PERCENTAGE")
	private Double percentage;
	
}
