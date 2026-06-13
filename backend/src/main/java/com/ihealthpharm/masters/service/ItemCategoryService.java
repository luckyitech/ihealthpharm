package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemCategoryModel;

public interface ItemCategoryService {

	ItemCategoryModel saveItemCategoryData(ItemCategoryModel itemCategoryModel);

	ItemCategoryModel updateItemCategoryData(ItemCategoryModel itemCategoryModel);

	List<ItemCategoryModel> updateItemCategoriesData(List<ItemCategoryModel> itemCategoryModels);

	List<ItemCategoryModel> findItemCategoryByActive();

	ItemCategoryModel findItemCategoryById(Integer itemCategoryId);

	void deleteItemCategoryById( Integer itemCategoryId);

	void deleteMultipleItemCategoriesById(Integer[] itemCategoryIds);

	List<ItemCategoryModel> findAllItemCategoryData(String medicalOrNonMedical,String searchTerm);

	List<ItemCategoryModel> findAllCategories();

	List<ItemCategoryModel> findAllCategoriesMapWithItems();
}
