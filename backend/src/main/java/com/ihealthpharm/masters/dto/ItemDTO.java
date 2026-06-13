package com.ihealthpharm.masters.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDTO {
	
	private Integer itemId;
	
	private String medicalOrNonMedical;
	
	private String itemCode;
	
	private String itemName;
	
	private String drugDose;
	
	private String itemDescription;
	
	//private ItemFormModel itemForm;
	
//	private ItemsModel item;
	
	

	public ItemDTO(Integer itemId, String medicalOrNonMedical, String itemCode, String itemName,String itemDescription, String drugDose
			) {
		super();
		this.itemId = itemId;
		this.medicalOrNonMedical = medicalOrNonMedical;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.drugDose = drugDose;
		this.itemDescription = itemDescription;
		//this.itemForm = itemForm;
	}

}
