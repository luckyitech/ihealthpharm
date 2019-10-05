package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemGenericNameRepository;
import com.ihealthpharm.masters.helper.ItemGenericNamesHelper;
import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.service.ItemGenericNamesService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class ItemGenericNamesServiceImpl implements ItemGenericNamesService {


	@Autowired
	ItemGenericNameRepository itemGenericNameRepository;

	@Autowired
	ItemGenericNamesHelper itemGenericHelper;

	private ItemGenericNamesModel getValidItemGeneric(int itemGenericNameId)
	{
		ItemGenericNamesModel itemGenericRes = null;
		try {
			itemGenericRes =itemGenericNameRepository.findById(itemGenericNameId).get();
			return itemGenericRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(),HttpStatus.NOT_FOUND);
		}


	}


	@Override
	public ItemGenericNamesModel saveItemGenericNamesData(ItemGenericNamesModel itemGenericNamesModel) {

		itemGenericNamesModel = itemGenericNameRepository.save(itemGenericNamesModel);
		log.info("ItemGenericName data with ID: "+ itemGenericNamesModel.getItemGenericNameId()+" saved succesfully");
		return itemGenericNamesModel;

	}

	@Override
	public ItemGenericNamesModel updateItemGenericNameData(ItemGenericNamesModel itemGenericNamesModel) {


		ItemGenericNamesModel itemGenericModelRes = getValidItemGeneric(itemGenericNamesModel.getItemGenericNameId());

		if(!Objects.nonNull(itemGenericModelRes))
		{
			throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(),HttpStatus.NOT_FOUND);
		}

		itemGenericModelRes = itemGenericNameRepository.save(itemGenericNamesModel);
		log.info("ItemGenericName data with ID : "+ itemGenericModelRes.getItemGenericNameId()+" updated succesfully");
		return itemGenericModelRes;
	}

	@Override
	public List<ItemGenericNamesModel> updateItemGenericNamesData(List<ItemGenericNamesModel> itemGroupModels) {

		for (ItemGenericNamesModel itemGenerics : itemGroupModels) {
			ItemGenericNamesModel itemGenericRes = getValidItemGeneric(itemGenerics.getItemGenericNameId());
			if (!Objects.nonNull(itemGenericRes)) {
				throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(), HttpStatus.NOT_FOUND);
			}

			itemGenericRes =  itemGenericNameRepository.save(itemGenerics);
			log.info("ItemGenericName data with Multiple IDs : " + itemGenericRes.getItemGenericNameId() + " updated succesfully");
		}

		return itemGroupModels;
	}

	@Override
	public List<ItemGenericNamesModel> findItemGenericNameByActive() {

		return itemGenericNameRepository.findByActiveS("Y");
	}

	@Override
	public ItemGenericNamesModel findItemGenericNameById(int itemGenericNameId) {

		ItemGenericNamesModel itemGenericModelRes = getValidItemGeneric(itemGenericNameId);

		if(!Objects.nonNull(itemGenericModelRes))
		{
			throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("ItemGenericName data with ID : "+ itemGenericModelRes.getItemGenericNameId()+" retrieved succesfully");
		return itemGenericModelRes;
	}

	@Override
	public void deleteItemGenericNameById(int itemGenericNameId) {

		ItemGenericNamesModel itemGenericModelRes = getValidItemGeneric(itemGenericNameId);
		if(!Objects.nonNull(itemGenericModelRes))
		{
			throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(),HttpStatus.NOT_FOUND);
		}		
		itemGenericNameRepository.delete(itemGenericModelRes);
		log.info("ItemGenericName data with ID: "+ itemGenericModelRes.getItemGenericNameId()+" deleted succesfully");
	}

	@Override
	public void deleteMultipleItemGenericNamesById(int[] itemGenericNameIds) {

		ItemGenericNamesModel itemGenericModelRes;
		for (int itemGeneric : itemGenericNameIds) {

			itemGenericModelRes =  getValidItemGeneric(itemGeneric); 
			if (!Objects.nonNull(itemGenericModelRes)) {
				throw new IHealthPharmException(itemGenericHelper.getNotFoundItemGenericNameMessage(), HttpStatus.NOT_FOUND);
			}
			itemGenericNameRepository.delete(itemGenericModelRes);
			log.info("ItemForm data with ID: " + itemGenericModelRes.getItemGenericNameId() + " deleted succesfully");
		}

	}


	@Override
	public List<ItemGenericNamesModel> findAllItemGenericNamesData(String medicalOrNonMedical, String searchTerm,
			Integer itemGroupId) {
		ItemGroupModel itemGroupModel = new ItemGroupModel();
		itemGroupModel.setItemGroupId(itemGroupId);

		return itemGenericNameRepository.findAllBySearchCriteria(medicalOrNonMedical, searchTerm, itemGroupModel);
	}


	@Override
	public List<ItemGenericNamesModel> getAllGenerics() {
		return itemGenericNameRepository.findAllByOrderByLastUpdateTimestampDesc();
	}




}
