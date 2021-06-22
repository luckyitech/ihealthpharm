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
	
	private Integer itemSupplierId;
	
	private String activeS;
	
	private String supplierName;

	private String itemName;
	
	private String manufacturerName;

	private String manufacturerLicense;
	
	private String itemDescription;

	private Integer itemId;
	
	private Integer supplierId;
	
	private Integer supplierPriority;
	
	private String formulation;

	private String itemCode;
		
	private Double percentage;
	
	private Double unitRate;
	
	private Double discountPercentage;
	
	private String validity;
		
	private ItemsModel itemsModel;
	
	private SupplierModel supplierModel;
	
	private Integer itemsId;
	
	private Integer quantity;
	
	private Integer bonus;
	
	private String pack;
	
	private Double unitPurchasePrice;
	
	private Integer autoQuotationItemId;
	
	private Character autoQuotStatus;
	
	public ItemSupplierDTO(Integer itemSupplierId, Integer itemId, Integer supplierId, String activeS, String supplierName, String itemName) {
		this.itemSupplierId = itemSupplierId;
		this.itemId = itemId;
		this.supplierId = supplierId;
		this.activeS = activeS;
		this.supplierName = supplierName;
		this.itemName = itemName;
	}
	
	public ItemSupplierDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage, Integer itemsId) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
		this.itemId = itemsId;
	}
	
	public ItemSupplierDTO(Integer itemSupplierId,String activeS,String supplierName,String itemName,String manufacturerName,String manufacturerLicense,String itemDescription,Integer itemId,
			Integer supplierId,Integer supplierPriority,String formulation,String itemCode,Double unitRate,Double discountPercentage,String validity, ItemsModel itemsModel) {
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
		this.itemsModel = itemsModel;
		
	}
	
	public ItemSupplierDTO(Integer itemSupplierId,String supplierName,
			String itemName,String manufacturerName,String manufacturerLicense,String itemDescription,Integer itemId,
			Integer supplierId,Integer supplierPriority,String formulation,
			String itemCode,Double unitRate,Double discountPercentage,
			String validity, ItemsModel itemsModel,Integer quantity,
			Integer autoQuotationItemId,Character autoQuotStatus) {
		this.itemSupplierId=itemSupplierId;
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
		this.itemsModel = itemsModel;
		this.quantity=quantity;
		this.autoQuotationItemId=autoQuotationItemId;
		this.autoQuotStatus=autoQuotStatus;
	}
	
	public ItemSupplierDTO(Integer itemSupplierId,String activeS,String supplierName,String itemName,String manufacturerName,String manufacturerLicense,String itemDescription,Integer itemId,
			Integer supplierId,Integer supplierPriority,String formulation,String itemCode,Double unitRate,Double discountPercentage,String validity) {
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
			String formulation, Integer supplierId, String supplierName, String manufacturerName) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemId;
		this.formulation = formulation;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.manufacturerName = manufacturerName;
	}
	
	public ItemSupplierDTO(Integer itemId, String itemCode, String itemName, String itemDescription,
			Integer supplierId, String supplierName, String manufacturerName) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemId;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.manufacturerName = manufacturerName;
	}
	
	public ItemSupplierDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage, Integer itemsId, String manufacturerName, String formulation) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
		this.itemId = itemsId;
		this.manufacturerName = manufacturerName;
		this.formulation = formulation;
	}
	
	public ItemSupplierDTO(Double unitRate, Float discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage, Integer itemsId, String manufacturerName, String formulation) {
		this.unitRate = unitRate;
		if(discountPercentage != null) {
			this.discountPercentage = discountPercentage.doubleValue();
		}
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
		this.itemId = itemsId;
		this.manufacturerName = manufacturerName;
		this.formulation = formulation;
	}
	
	public ItemSupplierDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Integer itemsId, String manufacturerName) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemsId;
		this.manufacturerName = manufacturerName;
	}
	
	public ItemSupplierDTO(Double unitRate, Float discountPercentage, String itemCode, String itemName, String itemDescription, 
			Integer itemsId, String manufacturerName) {
		this.unitRate = unitRate;
		if(discountPercentage != null) {
			this.discountPercentage = discountPercentage.doubleValue();
		}
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemsId;
		this.manufacturerName = manufacturerName;
	}
	
	public ItemSupplierDTO(String itemCode, String itemName, String itemDescription, 
			Integer itemsId, String manufacturerName) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.itemId = itemsId;
		this.manufacturerName = manufacturerName;
	}

	public ItemSupplierDTO(Integer itemId, String itemCode, String itemName, String itemDescription,String formulation,String manufacturerName, 
			 Integer supplierId,String supplierName,  Integer quantity) {
		super();
		this.supplierName = supplierName;
		this.itemName = itemName;
		this.manufacturerName = manufacturerName;
		this.itemDescription = itemDescription;
		this.itemId = itemId;
		this.supplierId = supplierId;
		this.formulation = formulation;
		this.itemCode = itemCode;
		this.quantity = quantity;
	}
	
	public ItemSupplierDTO(Integer itemId, String itemCode, String itemName, String itemDescription, 
			String formulation,String manufacturerName, Integer supplierId, String supplierName, 
			Integer quantity,Integer bonus,
			String pack,ItemsModel itemsModel,Double discountPercentage,
			Double unitRate) {
		//super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.formulation = formulation;
		this.manufacturerName = manufacturerName;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.quantity=quantity;
		this.bonus=bonus;
		this.pack=pack;
		this.itemsModel=itemsModel;
		this.discountPercentage=discountPercentage;
		//this.unitPurchasePrice=unitPurchasePrice;
		this.unitRate=unitRate;
	}
	
}
