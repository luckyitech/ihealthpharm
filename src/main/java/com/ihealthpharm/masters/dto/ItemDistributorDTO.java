package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ItemDistributorDTO {
	
	
	private int itemDistributorId;
	
	private int itemsId;
	
	private int distributorsId;
	
	private String activeS;
	
	private String distributorName;
	
	private String itemName;
	
	private String itemCode;
	
	private String itemDescription;
	
	private Double percentage;
	
	private Double unitRate;
	
	private Double discountPercentage;
		
	private ItemsModel itemsModel;
	
	private DistributorModel distributorModel;
	
	public ItemDistributorDTO(int itemDistributorId, int itemsId, int distributorsId, String activeS, String distributorName, String itemName) {
		this.itemDistributorId = itemDistributorId;
		this.itemsId = itemsId;
		this.distributorsId = distributorsId;
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
		this.itemsId = itemsId;
	}
}
