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
 *	Setter, getters by default added no need to add manually
 */

@Data
@Entity(name = "items_group")
@EqualsAndHashCode(of = "itemGroupId", callSuper = false)
public class ItemGroupModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_GROUP_ID",length=11)
	private Integer itemGroupId;
	
	@Column(name = "GROUP_NAME",length=100)
	private String groupName;
	
	@Column(name="GROUP_CD",length=20)
	private String groupCode;
	
	@Column(name = "GROUP_DESC",columnDefinition="text")
	private String groupDescription;

	@Column(name = "MEDICAL_OR_NON_MEDICAL",length=1)
	private String medicalOrNonMedical;
	
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;
	
}
