package com.ihealthpharm.masters.dto;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Data;
@Data
public class AlternativeItemsRequestDTO {

	List<ItemsModel> itemAlternativeModels;
	ItemsModel item;
	
}
