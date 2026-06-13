package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemAlternativeRepository;
import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.helper.ItemAlternativeHelper;
import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemAlternativeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemAlternativeServiceImpl implements ItemAlternativeService{

	@Autowired
	ItemAlternativeRepository itemAlternativeRepository;
	
	@Autowired
	ItemAlternativeHelper itemAlternativeHelper; 
	
	@Override
	public ItemAlternativeModel saveItemAlternative(List<ItemsModel> items, ItemsModel item) {
		ItemAlternativeModel itemAlternativeRes = null;
		ItemAlternativeModel itemAlternativeModel = null;
		for(ItemsModel itemModel:items)
		{
			itemAlternativeModel = new ItemAlternativeModel();
			itemAlternativeModel.setItemId(item);
			itemAlternativeModel.setAlternativeItemId(itemModel);
			log.info(itemAlternativeModel.toString());
			itemAlternativeRes = itemAlternativeRepository.save(itemAlternativeModel);
			log.info("Alternative Item Saved with Id:" + itemAlternativeRes.getAlternativeItemId());
		}
		return itemAlternativeRes;
	}

	@Override
	public ItemAlternativeModel updateItemAlternative(List<ItemAlternativeModel> itemAlternativeModels) {
		ItemAlternativeModel itemAlternativeRes = null;
		for(ItemAlternativeModel itemAlternativeModel:itemAlternativeModels)
		{
			itemAlternativeRes = getValidItemAlternative(itemAlternativeModel.getItemAlternativeId());
			if (!Objects.nonNull(itemAlternativeRes)) {
				throw new IHealthPharmException(itemAlternativeHelper.getNotFoundItemAlternativeMessage(),HttpStatus.NOT_FOUND);
			}
			itemAlternativeRes = itemAlternativeRepository.save(itemAlternativeModel);
			log.info("Alternative Item updated with Id:" + itemAlternativeRes.getAlternativeItemId());
		}
		return itemAlternativeRes;
	}

	@Override
	public ItemAlternativeModel findByItemAlternativeId(Integer itemAlternativeId) {
		ItemAlternativeModel itemAlternativeRes = itemAlternativeRepository.getOne(itemAlternativeId);
		return itemAlternativeRes;
	}

	@Override
	public List<ItemAlternativeModel> findAll() {
		List<ItemAlternativeModel> itemAlternativeList = itemAlternativeRepository.findAll();
		return itemAlternativeList;
	}

	@Override
	public void delete(Integer itemAlternativeId) {
		ItemAlternativeModel itemAlternativeRes = getValidItemAlternative(itemAlternativeId);
		if (!Objects.nonNull(itemAlternativeRes)) {
			throw new IHealthPharmException(itemAlternativeHelper.getNotFoundItemAlternativeMessage(),HttpStatus.NOT_FOUND);
		}
		itemAlternativeRepository.delete(itemAlternativeRes);
		log.info("Alternative Item deleted with Id:" + itemAlternativeRes.getAlternativeItemId());
		
	}

	@Override
	public List<AlternativeItemDTO> findByItemId(ItemsModel item) {
		List<AlternativeItemDTO> itemAlternativeList = itemAlternativeRepository.findByItemsId(item.getItemId());
		return itemAlternativeList;
	}
	
	private ItemAlternativeModel getValidItemAlternative(Integer itemAlternativeId)
	{
		ItemAlternativeModel itemAlternativeRes = null;
		try {
			itemAlternativeRes =  itemAlternativeRepository.findById(itemAlternativeId).get();
			return itemAlternativeRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemAlternativeHelper.getNotFoundItemAlternativeMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void updateItemAlternativeBasedOnItemModel(List<ItemsModel> itemAlternativeModel, ItemsModel item) {
		List<ItemsModel> existingItemCheck = itemAlternativeRepository.findByItemId(item);
		ItemAlternativeModel itemRes = null;
		if(Objects.nonNull(existingItemCheck))
		{
			itemAlternativeRepository.deleteByItemId(item.getItemId());
		/*for(ItemsModel itemModel:itemAlternativeModel)
		{
			
			System.out.println(itemModel);
			
			  itemAlternativeRepository.updateBasedOnItem(itemModel,item);
		}*/
			saveItemAlternative(itemAlternativeModel,item);
		
		}
		else
		{
			saveItemAlternative(itemAlternativeModel,item);
		}
		
	}
}
