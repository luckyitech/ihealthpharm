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
	
	
	private Integer itemDistributorId;
	
	private String activeS;
	
	private String distributorName;

	private String itemName;
	
	private String manufacturerName;
	private String manufacturerLicense;
	
	private String itemDescription;

	private Integer itemId;
	
	private Integer distributorId;
	
	private int distributorPriority;
	
}
