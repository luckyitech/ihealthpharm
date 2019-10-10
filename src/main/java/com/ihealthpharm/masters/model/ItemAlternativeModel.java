package com.ihealthpharm.masters.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name="item_alternative")
@Data
public class ItemAlternativeModel extends AuditModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5613194931239328395L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM_ALTERNATIVE_ID",length=11)
	private Integer itemAlternativeId;
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemId;
	
	@OneToOne
	@JoinColumn(name="ALTERNATIVE_ITEM_ID")
	private ItemsModel alternativeItemId;
	
	@Column(name = "ACTIVE_S",  columnDefinition = "default 'Y'")
	private String activeS = "Y";
	
	@Column(name = "AUDIT_ID")
	private Integer auditId;
}
