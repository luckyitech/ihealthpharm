package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemCategoriesRepository;
import com.ihealthpharm.masters.helper.ItemCategoryHelper;
import com.ihealthpharm.masters.model.ItemCategoryModel;
import com.ihealthpharm.masters.service.ItemCategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ItemCategoryServiceImpl implements ItemCategoryService {


	@Autowired
	ItemCategoriesRepository itemCategoriesRepository;

	@Autowired
	ItemCategoryHelper itemCategoryHelper;
	

	
	private ItemCategoryModel getValidItemCategory(int itemCategoryId)
	{
		ItemCategoryModel itemCategoryRes = null;
		try {
			itemCategoryRes =  itemCategoriesRepository.findById(itemCategoryId).get();
			return itemCategoryRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(),HttpStatus.NOT_FOUND);
		}


	}
	
	
	public ItemCategoryModel saveItemCategoryData(ItemCategoryModel itemCategoryModel) {
		
		itemCategoryModel = itemCategoriesRepository.save(itemCategoryModel);
		log.info("ItemCategory data with ID: "+ itemCategoryModel.getItemCategoryId()+" saved succesfully");
		return itemCategoryModel;
		
	}

	@Override
	public ItemCategoryModel updateItemCategoryData(ItemCategoryModel itemCategoryModel) {
		
		ItemCategoryModel itemCategoryModelRes = getValidItemCategory(itemCategoryModel.getItemCategoryId());

		if(!Objects.nonNull(itemCategoryModelRes))
		{
			throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(),HttpStatus.NOT_FOUND);
		}

		itemCategoryModelRes = itemCategoriesRepository.save(itemCategoryModel);
		
		if(itemCategoryModelRes.getMarginPercentage() != null)
		{
			Integer markup = itemCategoriesRepository.getMarkup();
			itemCategoriesRepository.updateStockWithMargin(itemCategoryModel.getItemCategoryId(),itemCategoryModel.getMarginPercentage(),markup);	
		}
		else
		{
			Integer markup = itemCategoriesRepository.getMarkup();
			Integer margin = itemCategoriesRepository.getMargin();
			itemCategoriesRepository.updateStockWithMarginIfItemMarginNull(itemCategoryModel.getItemCategoryId(),margin,markup);	
		}
		
		
		log.info("ItemCategory data with ID : "+ itemCategoryModelRes.getItemCategoryId()+" updated succesfully");
		return itemCategoryModelRes;
	}

	@Override
	public List<ItemCategoryModel> updateItemCategoriesData(List<ItemCategoryModel> itemCategoryModels) {
		
		for (ItemCategoryModel itemCategories : itemCategoryModels) {
			ItemCategoryModel itemCategoryRes = getValidItemCategory(itemCategories.getItemCategoryId());
			if (!Objects.nonNull(itemCategoryRes)) {
				throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(), HttpStatus.NOT_FOUND);
			}

			itemCategoryRes = itemCategoriesRepository.save(itemCategories);
			log.info("ItemCategory data with Multiple IDs : " + itemCategoryRes.getItemCategoryId() + " updated succesfully");
		}
		
		return itemCategoryModels;
	}

	@Override
	public List<ItemCategoryModel> findItemCategoryByActive() {
		return itemCategoriesRepository.findByActiveS("Y");
	}

	@Override
	public ItemCategoryModel findItemCategoryById(Integer itemCategoryId) {

		ItemCategoryModel itemCategoryModelRes = getValidItemCategory(itemCategoryId);

		if(!Objects.nonNull(itemCategoryModelRes))
		{
			throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("ItemCategory data with ID : "+ itemCategoryModelRes.getItemCategoryId()+" retrieved succesfully");
		return itemCategoryModelRes;
	}

	@Override
	public void deleteItemCategoryById(Integer itemCategoryId) {
		
		ItemCategoryModel itemCategoryModelModelRes = getValidItemCategory(itemCategoryId);
		if(!Objects.nonNull(itemCategoryModelModelRes))
		{
			throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(),HttpStatus.NOT_FOUND);
		}		
		itemCategoriesRepository.delete(itemCategoryModelModelRes);
		log.info("ItemCategory data with ID: "+ itemCategoryModelModelRes.getItemCategoryId()+" deleted succesfully");

	}

	@Override
	public void deleteMultipleItemCategoriesById(Integer[] itemCategoryIds) {
	
		ItemCategoryModel itemCategoryModelModelRes;
		for (int itemCategory : itemCategoryIds) {
			itemCategoryModelModelRes = getValidItemCategory(itemCategory);
			if (!Objects.nonNull(itemCategoryModelModelRes)) {
				throw new IHealthPharmException(itemCategoryHelper.getNotFoundItemCategoryMessage(), HttpStatus.NOT_FOUND);
			}
			itemCategoriesRepository.delete(itemCategoryModelModelRes);
			log.info("ItemCategory data with ID: " + itemCategoryModelModelRes.getItemCategoryId() + " deleted succesfully");
		}
		

	}

	@Override
	public List<ItemCategoryModel> findAllItemCategoryData(String medicalOrNonMedical, String searchTerm) {
		if("ALL".equalsIgnoreCase(searchTerm)) {
	    	  searchTerm="";
	      }
		return itemCategoriesRepository.findAllBySearchCriteria(medicalOrNonMedical,searchTerm);
	}


	@Override
	public List<ItemCategoryModel> findAllCategories() {
		return itemCategoriesRepository.findAllByLastUpdated();
	}


	@Override
	public List<ItemCategoryModel> findAllCategoriesMapWithItems() {
		return itemCategoriesRepository.findAllByLastUpdatedRecords();
	}
	
	

}
