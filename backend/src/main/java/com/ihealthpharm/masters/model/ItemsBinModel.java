package com.ihealthpharm.masters.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="items_bin")
@Data
@EqualsAndHashCode(of = "itemBinId", callSuper = false)
public class ItemsBinModel extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3756334297987714713L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ITEM_BIN_ID")
	private Integer itemBinId;
	

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ITEM_ID")
	private ItemsModel items;
	
	@Column(name="BIN_NO")
	private String binNumber;
	
}
