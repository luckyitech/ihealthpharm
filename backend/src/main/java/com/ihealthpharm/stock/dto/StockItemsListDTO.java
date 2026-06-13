package com.ihealthpharm.stock.dto;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Data;

@Data
public class StockItemsListDTO {

	private List<ItemsModel> listOfItems;
	private PharmacyModel pharmacy;	
}
