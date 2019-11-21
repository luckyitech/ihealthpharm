package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemService {

	ItemsModel saveItemsData(@Valid ItemsModel itemsModel);

	ItemsModel updateItemData(@Valid ItemsModel itemsModel);

	List<ItemsModel> updateItemsData(@Valid List<ItemsModel> itemsModels);

	List<ItemsModel> findItemsByActive();

	ItemsModel findItemsById(int itemId);

	void deleteItemsById( int itemId);

	void deleteMultipleItemsById(int[] itemIds);

	List<ItemsModel> findAllItems();
	
	// based on medical and itemName
	List<ItemsModel> findAllByMedicalAndItemName(String medicalOrNonMedical, String searchTerm);
	
	// based on medical and itemDescription
	List<ItemsModel> findAllByMedicalAndItemDesc(String medicalOrNonMedical,String searchTerm);


	List<ItemsModel> findAllByItemName(String searchTerm);

	List<ItemsModel> findAllByItemDescription(String searchTerm);

	List<ItemsModel> findAllGerericNamesBySearch(String searchTerm);

	List<ItemsModel> findAllByItemGroupCodeSearch(String searchTerm);

	List<ItemsModel> findAllByItemCode(String searchTerm);
	
	List<ItemsModel> findBySearchKey(String searchTerm);
	
	List<ItemsModel> findBySearchKey(String searchTerm, String searchCode);
	
	List<ItemsModel> getLimitedItems();

	List<ItemsModel> findItemsByName(String itemName);
	
}
