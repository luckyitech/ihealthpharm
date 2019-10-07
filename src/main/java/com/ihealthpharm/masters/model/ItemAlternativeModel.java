package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="item_alternative")
@Data
public class ItemAlternativeModel extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM_ALTERNATIVE_ID",length=11)
	private Integer itemAlternativeId;
	
	@Column(name="ITEM_ID")
	private ItemsModel itemId;
	
	@Column(name="ALTERNATIVE_ITEM_ID")
	private ItemsModel alternativeItemId;
	
	@Column(name = "ACTIVE_S",  columnDefinition = "default 'Y'")
	private String activeS = "Y";
	
	@Column(name = "AUDIT_ID")
	private Integer auditId;
}
