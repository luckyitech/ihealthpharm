package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemFormRepository;
import com.ihealthpharm.masters.helper.ItemFormsHelper;
import com.ihealthpharm.masters.model.ItemFormModel;
import com.ihealthpharm.masters.service.ItemFormService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ItemFormsServiceImpl implements ItemFormService {


	@Autowired
	ItemFormRepository itemFormRepository;

	@Autowired
	ItemFormsHelper itemFormsHelper;


	private ItemFormModel getValidItemForm(int itemFormId)
	{
		ItemFormModel itemFormRes = null;
		try {
			itemFormRes =itemFormRepository.findById(itemFormId).get();
			return itemFormRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(),HttpStatus.NOT_FOUND);
		}


	}


	@Override
	public ItemFormModel saveItemFormData(ItemFormModel itemFormModel) {
		itemFormModel = itemFormRepository.save(itemFormModel);
		log.info("ItemForm data with ID: "+ itemFormModel.getItemformId()+" saved succesfully");
		return itemFormModel;
	}

	@Override
	public ItemFormModel updateItemFormData(ItemFormModel itemFormModel) {

		ItemFormModel itemFormModelRes = getValidItemForm(itemFormModel.getItemformId());

		if(!Objects.nonNull(itemFormModelRes))
		{
			throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(),HttpStatus.NOT_FOUND);
		}

		itemFormModelRes = itemFormRepository.save(itemFormModel);
		log.info("ItemForm data with ID : "+ itemFormModelRes.getItemformId()+" updated succesfully");
		return itemFormModelRes;
	}

	@Override
	public List<ItemFormModel> updateItemFormsData(List<ItemFormModel> itemFormModels) {

		for (ItemFormModel itemForms : itemFormModels) {
			ItemFormModel itemFormRes = getValidItemForm(itemForms.getItemformId());
			if (!Objects.nonNull(itemFormRes)) {
				throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(), HttpStatus.NOT_FOUND);
			}

			itemFormRes =  itemFormRepository.save(itemForms);
			log.info("ItemForm data with Multiple IDs : " + itemFormRes.getItemformId() + " updated succesfully");
		}

		return itemFormModels;
	}

	@Override
	public List<ItemFormModel> findItemFormByActive() {

		return itemFormRepository.findByActiveS("Y");
	}

	@Override
	public ItemFormModel findItemFormById(Integer itemFormModel) {

		ItemFormModel itemFormModelRes = getValidItemForm(itemFormModel);

		if(!Objects.nonNull(itemFormModelRes))
		{
			throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("ItemForm data with ID : "+ itemFormModelRes.getItemformId()+" retrieved succesfully");
		return itemFormModelRes;
	}

	@Override
	public void deleteItemFormById(Integer itemFormModelId) {

		ItemFormModel itemFormModelModelRes = getValidItemForm(itemFormModelId);
		if(!Objects.nonNull(itemFormModelModelRes))
		{
			throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(),HttpStatus.NOT_FOUND);
		}		
		itemFormRepository.delete(itemFormModelModelRes);
		log.info("ItemForm data with ID: "+ itemFormModelModelRes.getItemformId()+" deleted succesfully");

	}

	@Override
	public void deleteMultipleItemFormsById(Integer[] itemFormModelIds) {

		ItemFormModel itemFormsModelRes;
		for (int itemForm : itemFormModelIds) {

			itemFormsModelRes =  getValidItemForm(itemForm); 
			if (!Objects.nonNull(itemFormsModelRes)) {
				throw new IHealthPharmException(itemFormsHelper.getNotFoundItemFormsMessage(), HttpStatus.NOT_FOUND);
			}
			itemFormRepository.delete(itemFormsModelRes);
			log.info("ItemForm data with ID: " + itemFormsModelRes.getItemformId() + " deleted succesfully");
		}

	}


	@Override
	public List<ItemFormModel> findAllItemFormsData(String medicalOrNonMedical, String searchTerm) {
		if("ALL".equalsIgnoreCase(searchTerm)) {
	    	  searchTerm="";
	      }
		return itemFormRepository.findAllBySearchCriteria(medicalOrNonMedical, searchTerm);
	}
	
	
	@Override
	public List<ItemFormModel> findAllItemForms() {
		return itemFormRepository.findAllLastRecordsDesc();
	}

}
