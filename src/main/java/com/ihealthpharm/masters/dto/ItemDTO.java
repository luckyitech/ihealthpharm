package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.ItemFormModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.ManufacturerModel;

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
	
//	private ItemsModel item;
	
	private ItemFormModel itemForm;
	
	private ManufacturerModel manufacturer;

	public ItemDTO(Integer itemId, String medicalOrNonMedical, String itemCode, String itemName, String drugDose,
			ItemFormModel itemForm, ManufacturerModel manufacturer) {
		super();
		this.itemId = itemId;
		this.medicalOrNonMedical = medicalOrNonMedical;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.drugDose = drugDose;
		this.itemForm = itemForm;
		this.manufacturer = manufacturer;
	}

}
