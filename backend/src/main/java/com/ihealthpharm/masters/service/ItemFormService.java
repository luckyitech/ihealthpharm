package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemFormModel;

public interface ItemFormService {



	ItemFormModel saveItemFormData(ItemFormModel itemFormModel);

	ItemFormModel updateItemFormData(ItemFormModel itemFormModel);

	List<ItemFormModel> updateItemFormsData(List<ItemFormModel> itemFormModels);

	List<ItemFormModel> findItemFormByActive();

	ItemFormModel findItemFormById(Integer itemFormModel);

	void deleteItemFormById( Integer itemFormModel);

	void deleteMultipleItemFormsById(Integer[] itemFormModels);

	List<ItemFormModel> findAllItemFormsData(String medicalOrNonMedical,String searchTerm);

	List<ItemFormModel> findAllItemForms();

}
