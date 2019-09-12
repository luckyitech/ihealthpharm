package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemGenericNamesModel;

public interface ItemGenericNamesService {

	ItemGenericNamesModel saveItemGenericNamesData(ItemGenericNamesModel itemGenericNamesModel);

	ItemGenericNamesModel updateItemGenericNameData(ItemGenericNamesModel itemGenericNamesModel);

	List<ItemGenericNamesModel> updateItemGenericNamesData(List<ItemGenericNamesModel> itemGroupModels);

	List<ItemGenericNamesModel> findItemGenericNameByActive();

	ItemGenericNamesModel findItemGenericNameById(int itemGenericNameId);

	void deleteItemGenericNameById( int itemGenericNameId);

	void deleteMultipleItemGenericNamesById(int[] itemGenericNameIds);
	
	List<ItemGenericNamesModel> getAllGenerics();
	
	List<ItemGenericNamesModel> findAllItemGenericNamesData(String medicalOrNonMedical,String searchTerm,Integer itemGroupId);

}
