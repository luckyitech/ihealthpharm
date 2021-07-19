package com.ihealthpharm.stock.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Data;

@Data
@Entity(name="auto_quotations")
public class AutoQuotationsModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AUTO_QUOT_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer autoQuotId;
	
	@OneToOne
	@JoinColumn(name = "ITEM_ID")
	private ItemsModel itemId;
	
	@Column(name="ITEM_NM")
	private String itemName;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="ACTIVE_S")
	private Character activeS;
	
	@Column(name="SUPPLIER_ID")
	private Integer supplierId;
	
	@Column(name="ITEM_SUPPLIER_ID")
	private Integer itemSupplierId;
	
	@Column(name="SUPPLIER_PRIORITY",length=11)
	private Integer supplierPriority;
	
	
	@Column(name="UNIT_RATE")
	private Double unitRate;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
	
	@Column(name="VALIDITY")
	private String validity;
	
	@Column(name="BARCODE")
	private String barcode;
	
	@Column(name="BONUS")
	private Integer bonus;
}
