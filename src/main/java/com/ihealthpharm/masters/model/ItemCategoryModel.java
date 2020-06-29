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
 *	DrugType Models refers the type of the Drug
 *	Setter, getters by default added no need to add manually
 */

@Data
@Entity(name = "items_categories")
@EqualsAndHashCode(of = "itemCategoryId", callSuper = false)
public class ItemCategoryModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_CATEGORIE_ID",length=11)
	private Integer itemCategoryId;
	
	@Column(name = "CATEGORIE_NM",length=100)
	private String categoryName;
	
	@Column(name="CATEGORIE_CD",length=20)
	private String categoryCode;
	
	@Column(name = "CATEGORIE_DESC",length=1)
	private String categoryDescription;

	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column(name = "MEDICAL_OR_NON_MEDICAL",length=1)
	private String medicalOrNonMedical;
	
	@Column(name = "MARGIN_PERCENTAGE",length=11)
	private Integer marginPercentage;
}
