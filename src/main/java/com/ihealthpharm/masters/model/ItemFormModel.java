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
 *
 */

@Data
@Entity(name = "items_forms")
@EqualsAndHashCode(of = "itemformId", callSuper = false)
public class ItemFormModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_FORM_ID",length=11)
	private Integer itemformId;
	
	@Column(name = "FORM",length=100)
	private String form;

	@Column(name="FORM_CD",length=20)
	private String formCode;
	
	@Column(name = "MEDICAL_OR_NON_MEDICAL",length=1)
	private String medicalOrNonMedical;
	
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
}
