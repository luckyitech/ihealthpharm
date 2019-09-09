package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemsBinRepository;
import com.ihealthpharm.masters.helper.ItemsBinHelper;
import com.ihealthpharm.masters.model.ItemsBinModel;
import com.ihealthpharm.masters.service.ItemsBinService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ItemsBinServiceImpl implements ItemsBinService {
	
	
	@Autowired
	ItemsBinRepository itemsBinRepository;
	
	@Autowired
	ItemsBinHelper itemsBinHelper;
	
	
	@Override
	public ItemsBinModel saveItemsBinData(ItemsBinModel itemsBinModel) {
		itemsBinModel =itemsBinRepository.save(itemsBinModel);
		log.info("ItemsBin data with ID: "+ itemsBinModel.getItemBinId()+" saved succesfully");
		return itemsBinModel;
	}

	@Override
	public ItemsBinModel updateItemsBinData(ItemsBinModel itemsBinModel) {

		ItemsBinModel itemsBinModelRes =getValidItemBins(itemsBinModel.getItemBinId());

		if(!Objects.nonNull(itemsBinModelRes))
		{
			throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(),HttpStatus.NOT_FOUND);
		}

		itemsBinModelRes = itemsBinRepository.save(itemsBinModel);
		log.info("ItemsBin data with ID : "+ itemsBinModelRes.getItemBinId()+" updated succesfully");
		return itemsBinModelRes;
	
	}
	
	
	

	private ItemsBinModel getValidItemBins(int itemBinsId)
	{
		ItemsBinModel itemBinsRes = null;
		try {
			itemBinsRes =itemsBinRepository.findById(itemBinsId).get();
			return itemBinsRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(),HttpStatus.NOT_FOUND);
		}


	}

	@Override
	public List<ItemsBinModel> updateItemsBinsData(List<ItemsBinModel> itemsBinModel) {
		for (ItemsBinModel itemsBin : itemsBinModel) {
			ItemsBinModel	 itemsBinRes = getValidItemBins(itemsBin.getItemBinId());
			if (!Objects.nonNull(itemsBinRes)) {
				throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(), HttpStatus.NOT_FOUND);
			}

			itemsBinRes = itemsBinRepository.save(itemsBin);
			log.info("ItemsBin data with Multiple IDs : " + itemsBinRes.getItemBinId() + " updated succesfully");
		}
		
		return itemsBinModel;
	}

	
	@Override
	public ItemsBinModel findItemsBinById(int itemBinId) {
		
		ItemsBinModel itemsBinModelRes = getValidItemBins(itemBinId);

		if(!Objects.nonNull(itemsBinModelRes))
		{
			throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("ItemsBin data with ID : "+ itemsBinModelRes.getItemBinId()+" retrieved succesfully");
		return itemsBinModelRes;

	}

	@Override
	public void deleteItemsBinById(int itemBinId) {
		ItemsBinModel itemsBinModelRes = getValidItemBins(itemBinId);
		if(!Objects.nonNull(itemsBinModelRes))
		{
			throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(),HttpStatus.NOT_FOUND);
		}		
		itemsBinRepository.delete(itemsBinModelRes);
		log.info("ItemsBin data with ID: "+ itemsBinModelRes.getItemBinId()+" deleted succesfully");
	
	}

	@Override
	public void deleteMultipleItemBinsById(int[] itemBinIds) {
	
		ItemsBinModel itemsModelRes;
		for (int items : itemBinIds) {
			itemsModelRes = getValidItemBins(items);
			
			if (!Objects.nonNull(itemsModelRes)) {
				throw new IHealthPharmException(itemsBinHelper.getNotFoundItemsBinMessage(), HttpStatus.NOT_FOUND);
			}
			itemsBinRepository.delete(itemsModelRes);
			log.info("ItemsBins data with ID: " + itemsModelRes.getItemBinId() + " deleted succesfully");
		}
		
	}

}
