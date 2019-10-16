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
import com.ihealthpharm.masters.dao.DistrubutorRepository;
import com.ihealthpharm.masters.dao.ItemDistributorsRepository;
import com.ihealthpharm.masters.dao.ItemsRepository;
import com.ihealthpharm.masters.dto.ItemDistributorDTO;
import com.ihealthpharm.masters.helper.ItemDistributorHelper;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemDistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemDistributorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ItemDistributorServiceImpl implements ItemDistributorService {

	@Autowired
	ItemDistributorsRepository itemDistributorsRepository;

	@Autowired
	ItemDistributorHelper itemDistributorHelper;

	@Autowired
	ItemsRepository itemRepo;

	@Autowired
	DistrubutorRepository distributorRepo;

	private ItemDistributorModel getValidItemDistributor(int itemDistributorId) {
		ItemDistributorModel itemDistributorRes = null;
		try {
			itemDistributorRes = itemDistributorsRepository.findById(itemDistributorId).get();
			return itemDistributorRes;

		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
					HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ItemDistributorModel saveItemDistributorData( int[] itemsId,int[]  distributorsId) {

		ItemDistributorModel itemDistributor = null;

		if (itemsId.length <= 1) {

			for (int i : distributorsId) {
				itemDistributor = new ItemDistributorModel();
				itemDistributor.setItemsId(itemsId[0]);
				itemDistributor.setDistributorsId(i);
				itemDistributor = itemDistributorsRepository.save(itemDistributor);
			}

		} else {

			for (int i : itemsId) {
				itemDistributor = new ItemDistributorModel();
				itemDistributor.setItemsId(i);
				itemDistributor.setDistributorsId(distributorsId[0]);
				itemDistributor = itemDistributorsRepository.save(itemDistributor);
			}

		}

		log.info("ItemDistributor data with ID: " + itemDistributor.getItemDistributorId() + " saved succesfully");
		return itemDistributor;
	}

	@Override
	public ItemDistributorModel updateItemDistributorData(ItemDistributorModel itemDistributor) {
		ItemDistributorModel itemDistributorModelRes = getValidItemDistributor(itemDistributor.getItemDistributorId());

		if (!Objects.nonNull(itemDistributorModelRes)) {
			throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
					HttpStatus.NOT_FOUND);
		}

		itemDistributorModelRes = itemDistributorsRepository.save(itemDistributor);
		log.info("ItemDistributor data with ID : " + itemDistributorModelRes.getItemDistributorId()+ " updated succesfully");
		return itemDistributorModelRes;
	}

	@Override
	public List<ItemDistributorModel> updateItemDistributorsData(List<ItemDistributorModel> itemDistributorModels) {
		for (ItemDistributorModel itemDistributors : itemDistributorModels) {
			ItemDistributorModel itemDistributorRes = getValidItemDistributor(itemDistributors.getItemDistributorId());
			if (!Objects.nonNull(itemDistributorRes)) {
				throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
						HttpStatus.NOT_FOUND);
			}

			itemDistributorRes = itemDistributorsRepository.save(itemDistributors);
			log.info("ItemDistributor data with Multiple IDs : " + itemDistributorRes.getItemDistributorId()
			+ " updated succesfully");
		}

		return itemDistributorModels;
	}

	@Override
	public List<ItemDistributorModel> findItemDistributorByActive() {

		return itemDistributorsRepository.findByActiveS("Y");
	}

	@Override
	public ItemDistributorModel findItemDistributorById(int itemDistributorId) {

		ItemDistributorModel itemDistributorModelRes = getValidItemDistributor(itemDistributorId);

		if (!Objects.nonNull(itemDistributorModelRes)) {
			throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
					HttpStatus.NOT_FOUND);
		}
		log.info("ItemDistributor data with ID : " + itemDistributorModelRes.getItemDistributorId()
		+ " retrieved succesfully");
		return itemDistributorModelRes;
	}

	@Override
	public void deleteItemDistributorById(int itemDistributorId) {

		ItemDistributorModel itemDistributorModelModelRes = getValidItemDistributor(itemDistributorId);
		if (!Objects.nonNull(itemDistributorModelModelRes)) {
			throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
					HttpStatus.NOT_FOUND);
		}
		itemDistributorsRepository.delete(itemDistributorModelModelRes);
		log.info("ItemDistributor data with ID: " + itemDistributorModelModelRes.getItemDistributorId()
		+ " deleted succesfully");
	}

	@Override
	public void deleteMultipleItemDistributorsById(int[] itemDistributorIds) {

		ItemDistributorModel itemDistributorModelRes;
		for (int itemDistributor : itemDistributorIds) {

			itemDistributorModelRes = getValidItemDistributor(itemDistributor);
			if (!Objects.nonNull(itemDistributorModelRes)) {
				throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
						HttpStatus.NOT_FOUND);
			}
			itemDistributorsRepository.delete(itemDistributorModelRes);
			log.info("ItemDistributor data with ID: " + itemDistributorModelRes.getItemDistributorId()
			+ " deleted succesfully");
		}
	}

	@Override
	public List<ItemDistributorModel> findAllItemDistributors() {
		return itemDistributorsRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public List<DistributorModel> findAllUnMappedItemDistributorsData(int itemId) {

		List<DistributorModel> response = itemDistributorsRepository.getAllUnMappedDistributors(itemId);
		return response;
	}

	@Override
	public List<ItemsModel> findAllUnMappedDistributorItems(int distributorId) {
		List<ItemsModel> result = itemDistributorsRepository.getAllUnMappedItems(distributorId);
		return result;
	}

	@Override
	public List<ItemDistributorDTO> findAllMappedItemDistributors() {

		List<ItemDistributorDTO>  r =itemDistributorsRepository.getAllItemDistributors();

		return r;
	}

	@Override
	public List<DistributorModel> findAllUnmappedDistributorsNamesSearch(int itemId, String searchTerm) {

		List<DistributorModel> result=itemDistributorsRepository.getAllItemDistributorsSearchData(itemId,searchTerm);

		return result;
	}

	@Override
	public List<ItemsModel> finAllUnmppedItemsNameSearch(int distributorId, String searchTerm) {
		List<ItemsModel> res=itemDistributorsRepository.getAllItemDistributorsItemSearchData(distributorId,searchTerm);
		return res;
	}

	@Override
	public void saveItemDistributorsById(@Valid int itemDistributorId, @Valid String activeS) {
		ItemDistributorModel itemDistributorModelModelRes = getValidItemDistributor(itemDistributorId);
		if (!Objects.nonNull(itemDistributorModelModelRes)) {
			throw new IHealthPharmException(itemDistributorHelper.getNotFoundItemDistributorMessage(),
					HttpStatus.NOT_FOUND);
		}

		itemDistributorsRepository.updateStatus(itemDistributorId,activeS);
		log.info("ItemDistributor data with ID: " + itemDistributorModelModelRes.getItemDistributorId()
		+ " Updated succesfully");

	}

	@Override
	public List<ItemDistributorDTO> findAllMappedItemDistributorsOnItemName(int itemId) {
		List<ItemDistributorDTO> response=itemDistributorsRepository.getAllItemDistributorsBasedOnItemId(itemId);

		return response;
	}

	@Override
	public List<ItemDistributorDTO> findAllDistributorItemOnDistributorId(int distributorId) {
		List<ItemDistributorDTO> response=itemDistributorsRepository.getAllDistributorItemBasedOnDistId(distributorId);
		return response;
	}

	
	
	@Override
	public ItemDistributorModel saveItemDistributorDataModel(@Valid ItemDistributorModel itemDistributorModel) {

		itemDistributorModel = itemDistributorsRepository.save(itemDistributorModel);
		log.info("ItemDistributor data with ID: "+ itemDistributorModel.getItemDistributorId()+" saved succesfully");
		return itemDistributorModel;
	}

}
