package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemFormModel;

public interface ItemFormService {



	ItemFormModel saveItemFormData(ItemFormModel itemFormModel);

	ItemFormModel updateItemFormData(ItemFormModel itemFormModel);

	List<ItemFormModel> updateItemFormsData(List<ItemFormModel> itemFormModels);

	List<ItemFormModel> findItemFormByActive();

	ItemFormModel findItemFormById(int itemFormModel);

	void deleteItemFormById( int itemFormModel);

	void deleteMultipleItemFormsById(int[] itemFormModels);
	
	List<ItemFormModel> findAllItemFormsData(String medicalOrNonMedical,String searchTerm);

	List<ItemFormModel> findAllItemForms();

}
