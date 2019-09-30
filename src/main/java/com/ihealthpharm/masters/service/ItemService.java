package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.UnitOfMeasurementModel;

public interface ItemService {

	ItemsModel saveItemsData(@Valid ItemsModel itemsModel);

	ItemsModel updateItemData(@Valid ItemsModel itemsModel);

	List<ItemsModel> updateItemsData(@Valid List<ItemsModel> itemsModels);

	List<ItemsModel> findItemsByActive();

	ItemsModel findItemsById(int itemId);

	void deleteItemsById( int itemId);

	void deleteMultipleItemsById(int[] itemIds);

	List<ItemsModel> findAllItems();




	//searches based on item fields



	//2.based on medical and itemName
	List<ItemsModel> findAllByMedicalAndItemName(String medicalOrNonMedical, String searchTerm);
	//.based on medical and itemDescription
	List<ItemsModel> findAllByMedicalAndItemDesc(String medicalOrNonMedical,String searchTerm);



	//based on itemName
	List<ItemsModel> findAllByItemName(String searchTerm);

	//.based on ItemDescription
	List<ItemsModel> findAllByItemDescription(String searchTerm);

	//based on itemgenericname
	List<ItemsModel> findAllGerericNamesBySearch(String searchTerm);

	//based on itemGroupCode
	List<ItemsModel> findAllByItemGroupCodeSearch(String searchTerm);





	//UOM method
	List<UnitOfMeasurementModel> findAllUOMMethod();

	//UOM BASED ON SEARCH
	List<UnitOfMeasurementModel> findAllUOMMethodsOnSerch(String searchTerm);










}
