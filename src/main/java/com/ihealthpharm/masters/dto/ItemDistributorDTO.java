package com.ihealthpharm.masters.dto;

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
		
	//private Double percentage;
	
	private Double unitRate;
	
	private Double discountPercentage;
	
	private String validity;
		
	
	/*public ItemDistributorDTO(int itemDistributorId, int itemsId, int distributorsId, String activeS, String distributorName, String itemName) {
		this.itemDistributorId = itemDistributorId;
		this.itemId = itemsId;
		this.distributorId = distributorsId;
		this.activeS = activeS;
		this.distributorName = distributorName;
		this.itemName = itemName;
	}
	
	public ItemDistributorDTO(Double unitRate, Double discountPercentage, String itemCode, String itemName, String itemDescription, 
			Double percentage) {
		this.unitRate = unitRate;
		this.discountPercentage = discountPercentage;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemName = itemName;
		this.percentage = percentage;
	}*/
}
