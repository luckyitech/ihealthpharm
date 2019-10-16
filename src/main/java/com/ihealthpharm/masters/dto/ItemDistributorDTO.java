package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemDistributorDTO {
	
	
	private int itemDistributorId;
	
	private String activeS;
	
	private String distributorName;

	private String itemName;
	
	private String manufacturerName;

	private String manufacturerLicense;
	
	private String itemDescription;

	private Integer itemId;
	
	private Integer distributorId;
	
	private int distributorPriority;
	
	private String formulation;

	private String itemCode;
		
	private Double percentage;
	
	private Double unitRate;
	
	private Double discountPercentage;
	
	private String validity;
		
	private ItemsModel itemsModel;
	
	private DistributorModel distributorModel;
	
	private int itemsId;
	
	public ItemDistributorDTO(int itemDistributorId, int itemsId, int distributorsId, String activeS, String distributorName, String itemName) {
		this.itemDistributorId = itemDistributorId;
		this.itemId = itemsId;
		this.distributorId = distributorsId;
		this.activeS = activeS;
		this.distributorName = distributorName;
		this.itemName = itemName;
	}
	
	public ItemDistributorDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage, int itemsId) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
		this.itemId = itemsId;
	}
	
	public ItemDistributorDTO(int itemDistributorId,String activeS,String distributorName,String itemName,String manufacturerName,String manufacturerLicense,String itemDescription,Integer itemId,
			Integer distributorId,int distributorPriority,String formulation,String itemCode,Double unitRate,Double discountPercentage,String validity) {
		this.itemDistributorId=itemDistributorId;
		this.activeS=activeS;
		this.distributorName=distributorName;
		this.itemName=itemName;
		this.manufacturerName=manufacturerName;
		this.manufacturerLicense=manufacturerLicense;
		this.itemDescription=itemDescription;
		this.itemId=itemId;
		this.distributorId=distributorId;
		this.distributorPriority=distributorPriority;
		this.formulation=formulation;
		this.itemCode=itemCode;
		this.unitRate=unitRate;
		this.discountPercentage=discountPercentage;
		this.validity=validity;
	}
}
