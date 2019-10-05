package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemGroupRepository;
import com.ihealthpharm.masters.helper.ItemGroupHelper;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.service.ItemGroupService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ItemGroupServiceImpl implements ItemGroupService {


	@Autowired
	ItemGroupRepository itemGroupRepository;

	@Autowired
	ItemGroupHelper itemGroupHelper;


	private ItemGroupModel getValidItemGroup(int itemGroupId)
	{
		ItemGroupModel itemGroupRes = null;
		try {
			itemGroupRes = itemGroupRepository.findById(itemGroupId).get();  
			return itemGroupRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(),HttpStatus.NOT_FOUND);
		}

	}


	@Override
	public ItemGroupModel saveItemGroupData(ItemGroupModel itemGroupModel) {
		itemGroupModel = itemGroupRepository.save(itemGroupModel);
		log.info("ItemGroup data with ID: "+ itemGroupModel.getItemGroupId()+" saved succesfully");
		return itemGroupModel;
	}

	@Override
	public ItemGroupModel updateItemGroupData(ItemGroupModel itemGroupModel) {

		ItemGroupModel itemGroupModelRes = getValidItemGroup(itemGroupModel.getItemGroupId());

		if(!Objects.nonNull(itemGroupModelRes))
		{
			throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(),HttpStatus.NOT_FOUND);
		}

		itemGroupModelRes = itemGroupRepository.save(itemGroupModel);
		log.info("ItemGroup data with ID : "+ itemGroupModelRes.getItemGroupId()+" updated succesfully");
		return itemGroupModelRes;

	}

	@Override
	public List<ItemGroupModel> updateItemGroupsData(List<ItemGroupModel> itemGroupModels) {

		for (ItemGroupModel itemGroup : itemGroupModels) {
			ItemGroupModel itemGroupRes = getValidItemGroup(itemGroup.getItemGroupId());
			if (!Objects.nonNull(itemGroupRes)) {
				throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(), HttpStatus.NOT_FOUND);
			}

			itemGroupRes = itemGroupRepository.save(itemGroup);
			log.info("ItemGroup data with Multiple IDs : " + itemGroupRes.getItemGroupId() + " updated succesfully");
		}

		return itemGroupModels;
	}

	@Override
	public List<ItemGroupModel> findItemGroupByActive() {
		return itemGroupRepository.findByActiveS("Y");
	}

	@Override
	public ItemGroupModel findItemGroupById(int itemGroupId) {

		ItemGroupModel itemGroupModelRes = getValidItemGroup(itemGroupId);

		if(!Objects.nonNull(itemGroupModelRes))
		{
			throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("ItemForms data with ID : "+ itemGroupModelRes.getItemGroupId()+" retrieved succesfully");
		return itemGroupModelRes;
	}

	@Override
	public void deleteItemGroupById(int itemGroupId) {

		ItemGroupModel itemGroupModelRes = getValidItemGroup(itemGroupId);
		if(!Objects.nonNull(itemGroupModelRes))
		{
			throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(),HttpStatus.NOT_FOUND);
		}		
		itemGroupRepository.delete(itemGroupModelRes);
		log.info("ItemGroup data with ID: "+ itemGroupModelRes.getItemGroupId()+" deleted succesfully");

	}

	@Override
	public void deleteMultipleItemGroupsById(int[] itemGroupIds) {

		ItemGroupModel itemGroupRes;
		for (int items : itemGroupIds) {
			itemGroupRes = getValidItemGroup(items);
			if (!Objects.nonNull(itemGroupRes)) {
				throw new IHealthPharmException(itemGroupHelper.getNotFoundItemGroupMessage(), HttpStatus.NOT_FOUND);
			}
			itemGroupRepository.delete(itemGroupRes);
			log.info("Manufacturer data with ID: " + itemGroupRes.getItemGroupId() + " deleted succesfully");
		}

	}


	@Override
	public List<ItemGroupModel> findAllItemGroupData(String medicalOrNonMedical,String searchTerm) {

		return itemGroupRepository.findAllBySearchCriteria(medicalOrNonMedical,searchTerm);
	}


	@Override
	public List<ItemGroupModel> findAllItemGroups() {

		return itemGroupRepository.findAllByOrderByCreationTimeStampDesc();
	}

}
