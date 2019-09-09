package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemService {

	ItemsModel saveItemsData(@Valid ItemsModel itemsModel);
	
	ItemsModel updateItemData(@Valid ItemsModel itemsModel);
	 
	 List<ItemsModel> updateItemsData(@Valid List<ItemsModel> itemsModels);
	
	 List<ItemsModel> findItemsByActive();
	
	 ItemsModel findItemsById(int itemId);
	
	 void deleteItemsById( int itemId);
	 
	 void deleteMultipleItemsById(int[] itemIds);
	 
	 public List<ItemsModel> findAllItems();

}
