package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemCategoryModel;

public interface ItemCategoryService {

	ItemCategoryModel saveItemCategoryData(ItemCategoryModel itemCategoryModel);

	ItemCategoryModel updateItemCategoryData(ItemCategoryModel itemCategoryModel);

	List<ItemCategoryModel> updateItemCategoriesData(List<ItemCategoryModel> itemCategoryModels);

	List<ItemCategoryModel> findItemCategoryByActive();

	ItemCategoryModel findItemCategoryById(int itemCategoryId);

	void deleteItemCategoryById( int itemCategoryId);

	void deleteMultipleItemCategoriesById(int[] itemCategoryIds);

	List<ItemCategoryModel> findAllItemCategoryData(String medicalOrNonMedical,String searchTerm);

	List<ItemCategoryModel> findAllCategories();
}
