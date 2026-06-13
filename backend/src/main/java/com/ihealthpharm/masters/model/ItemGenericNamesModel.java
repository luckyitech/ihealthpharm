package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun
 *	Setter, getters by default added no need to add manually
 */
@Data
@Entity(name = "items_generic_names")
@EqualsAndHashCode(of = "itemGenericNameId", callSuper = false)
public class ItemGenericNamesModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_GENERIC_NAME_ID",length=11)
	private Integer itemGenericNameId;
	
	@Column(name = "GENERIC_NAME",length=500)
	private String genericName;
	
	@Column(name="GENERIC_CD",length=20)
	private String genericCode;
	
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;
	
	
	@Column(name="MEDICAL_OR_NON_MEDICAL",length=1)
	private String medicalOrNonMedical;
	
	@OneToOne
	@JoinColumn(name = "ITEM_GROUP_ID")
	private ItemGroupModel itemGroupId;
	
}
