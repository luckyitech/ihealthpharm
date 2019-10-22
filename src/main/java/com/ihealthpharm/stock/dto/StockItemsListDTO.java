package com.ihealthpharm.stock.dto;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Data;

@Data
public class StockItemsListDTO {

	private List<ItemsModel> items;

	public StockItemsListDTO(List<ItemsModel> items) {
		super();
		this.items = items;
	}
	
}
