package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemAlternativeService {

	public ItemAlternativeModel saveItemAlternative(List<ItemsModel> itemAlternativeModel, ItemsModel item);
	
	public ItemAlternativeModel updateItemAlternative(List<ItemAlternativeModel> itemAlternativeModel);
	
	public ItemAlternativeModel findByItemAlternativeId(Integer itemAlternativeId);
	
	public List<AlternativeItemDTO> findByItemId(ItemsModel item);
	
	public List<ItemAlternativeModel> findAll();
	
	public void delete(Integer itemAlternativeId);

	 void updateItemAlternativeBasedOnItemModel( List<ItemsModel> itemAlternativeModel, ItemsModel item);
	
	
}
