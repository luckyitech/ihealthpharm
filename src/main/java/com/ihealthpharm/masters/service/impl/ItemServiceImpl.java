package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemsRepository;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 *
 */
@Service
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemsRepository itemRepository;
	
	@Autowired
	private ItemPropertyHelper itemPropertyHelper;
	

	@Override
	public ItemsModel updateItemData(ItemsModel itemsModel) {
		ItemsModel itemsModelRes = getValidItems(itemsModel.getItemId());

		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}

		itemsModelRes = itemRepository.save(itemsModel);
		log.info("Items data with ID : "+ itemsModelRes.getItemId()+" updated succesfully");
		return itemsModelRes;
	}

	@Override
	public List<ItemsModel> updateItemsData(List<ItemsModel> itemsModels) {
		for (ItemsModel items : itemsModels) {
			ItemsModel itemsRes = getValidItems(items.getItemId());
			if (!Objects.nonNull(itemsRes)) {
				throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
			}

			itemsRes = itemRepository.save(items);
			log.info("Items data with Multiple IDs : " + itemsRes.getItemId() + " updated succesfully");
		}
		
		return itemsModels;
	}

	
	private ItemsModel getValidItems(int itemId)
	{
		ItemsModel itemsRes = null;
		try {
			itemsRes =  itemRepository.findById(itemId).get();
			return itemsRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}


	}
	
	
	@Override
	public List<ItemsModel> findItemsByActive() {
		return itemRepository.findByActiveS("Y");
	}

	@Override
	public ItemsModel findItemsById(int itemId) {
		
		ItemsModel itemsModelRes = getValidItems(itemId);

		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Items data with ID : "+ itemsModelRes.getItemId()+" retrieved succesfully");
		return itemsModelRes;
	}

	@Override
	public void deleteItemsById(int itemId) {
		ItemsModel itemsModelRes = getValidItems(itemId);
		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}		
		itemRepository.delete(itemsModelRes);
		log.info("Items data with ID: "+ itemsModelRes.getItemId()+" deleted succesfully");
	}

	@Override
	public void deleteMultipleItemsById(int[] itemIds) {
		ItemsModel itemsModelRes;
		for (int items : itemIds) {
			itemsModelRes = getValidItems(items);
			if (!Objects.nonNull(itemsModelRes)) {
				throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
			}
			itemRepository.delete(itemsModelRes);
			log.info("Items data with ID: " + itemsModelRes.getItemId() + " deleted succesfully");
		}		
	}

	@Override
	public ItemsModel saveItemsData(@Valid ItemsModel itemsModel) {
		itemsModel = itemRepository.save(itemsModel);
		log.info("Items data with ID: "+ itemsModel.getItemId()+" saved succesfully");
		return itemsModel;
		
	}

	@Override
	public List<ItemsModel> findAllItems() {
		return itemRepository.findAllByOrderByCreationTimeStampDesc();
	}

	
	
}
