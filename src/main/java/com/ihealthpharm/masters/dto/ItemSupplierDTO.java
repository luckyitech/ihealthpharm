package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemSupplierDTO {
	
	private int itemSupplierId;
	
	private String activeS;
	
	private String supplierName;

	private String itemName;
	
	private String manufacturerName;

	private String manufacturerLicense;
	
	private String itemDescription;

	private Integer itemId;
	
	private Integer supplierId;
	
	private int supplierPriority;
	
	private String formulation;

	private String itemCode;
		
	private Double percentage;
	
	private Double unitRate;
	
	private Double discountPercentage;
	
	private String validity;
		
	private ItemsModel itemsModel;
	
	private SupplierModel supplierModel;
	
	private int itemsId;
	
	private Integer quantity;
	
	public ItemSupplierDTO(int itemSupplierId, int itemId, int supplierId, String activeS, String supplierName, String itemName) {
		this.itemSupplierId = itemSupplierId;
		this.itemId = itemId;
		this.supplierId = supplierId;
		this.activeS = activeS;
		this.supplierName = supplierName;
		this.itemName = itemName;
	}
	
	public ItemSupplierDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage, int itemsId) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
		this.itemId = itemsId;
	}
	
	public ItemSupplierDTO(int itemSupplierId,String activeS,String supplierName,String itemName,String manufacturerName,String manufacturerLicense,String itemDescription,Integer itemId,
			Integer supplierId,int supplierPriority,String formulation,String itemCode,Double unitRate,Double discountPercentage,String validity) {
		this.itemSupplierId=itemSupplierId;
		this.activeS=activeS;
		this.supplierName=supplierName;
		this.itemName=itemName;
		this.manufacturerName=manufacturerName;
		this.manufacturerLicense=manufacturerLicense;
		this.itemDescription=itemDescription;
		this.itemId=itemId;
		this.supplierId=supplierId;
		this.supplierPriority=supplierPriority;
		this.formulation=formulation;
		this.itemCode=itemCode;
		this.unitRate=unitRate;
		this.discountPercentage=discountPercentage;
		this.validity=validity;
	}
	
	public ItemSupplierDTO(String itemCode, String itemName, String itemDescription, Integer quantity) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	
	public ItemSupplierDTO(Integer itemId, String itemCode, String itemName, String itemDescription) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemId;
	}
	
	public ItemSupplierDTO(Integer itemId, String itemCode, String itemName, String itemDescription, 
			String formulation, Integer supplierId, String supplierName) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemId;
		this.formulation = formulation;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
	}
}
