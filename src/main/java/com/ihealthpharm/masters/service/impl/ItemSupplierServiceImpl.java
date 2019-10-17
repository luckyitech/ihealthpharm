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
import com.ihealthpharm.masters.dao.ItemSuppliersRepository;
import com.ihealthpharm.masters.dao.ItemsRepository;
import com.ihealthpharm.masters.dao.SupplierRepository;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.helper.ItemSupplierHelper;
import com.ihealthpharm.masters.model.ItemSupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.ItemSupplierService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ItemSupplierServiceImpl implements ItemSupplierService {

	@Autowired
	ItemSuppliersRepository itemSuppliersRepository;

	@Autowired
	ItemSupplierHelper itemSupplierHelper;

	@Autowired
	ItemsRepository itemRepo;

	@Autowired
	SupplierRepository supplierRepo;

	private ItemSupplierModel getValidItemSupplier(int itemSupplierId) {
		ItemSupplierModel itemSupplierRes = null;
		try {
			itemSupplierRes = itemSuppliersRepository.findById(itemSupplierId).get();
			return itemSupplierRes;

		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
					HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ItemSupplierModel saveItemSupplierData( int[] itemsId,int[]  suppliersId) {

		ItemSupplierModel itemSupplier = null;

		if (itemsId.length <= 1) {

			for (int i : suppliersId) {
				itemSupplier = new ItemSupplierModel();
				itemSupplier.setItemsId(itemsId[0]);
				itemSupplier.setSuppliersId(i);
				itemSupplier = itemSuppliersRepository.save(itemSupplier);
			}

		} else {

			for (int i : itemsId) {
				itemSupplier = new ItemSupplierModel();
				itemSupplier.setItemsId(i);
				itemSupplier.setSuppliersId(suppliersId[0]);
				itemSupplier = itemSuppliersRepository.save(itemSupplier);
			}

		}

		log.info("ItemSupplier data with ID: " + itemSupplier.getItemSupplierId() + " saved succesfully");
		return itemSupplier;
	}

	@Override
	public ItemSupplierModel updateItemSupplierData(ItemSupplierModel itemSupplier) {
		ItemSupplierModel itemSupplierModelRes = getValidItemSupplier(itemSupplier.getItemSupplierId());

		if (!Objects.nonNull(itemSupplierModelRes)) {
			throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
					HttpStatus.NOT_FOUND);
		}

		itemSupplierModelRes = itemSuppliersRepository.save(itemSupplier);
		log.info("ItemSupplier data with ID : " + itemSupplierModelRes.getItemSupplierId()+ " updated succesfully");
		return itemSupplierModelRes;
	}

	@Override
	public List<ItemSupplierModel> updateItemSuppliersData(List<ItemSupplierModel> itemSupplierModels) {
		for (ItemSupplierModel itemSuppliers : itemSupplierModels) {
			ItemSupplierModel itemSupplierRes = getValidItemSupplier(itemSuppliers.getItemSupplierId());
			if (!Objects.nonNull(itemSupplierRes)) {
				throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
						HttpStatus.NOT_FOUND);
			}

			itemSupplierRes = itemSuppliersRepository.save(itemSuppliers);
			log.info("ItemSupplier data with Multiple IDs : " + itemSupplierRes.getItemSupplierId()
			+ " updated succesfully");
		}

		return itemSupplierModels;
	}

	@Override
	public List<ItemSupplierModel> findItemSupplierByActive() {

		return itemSuppliersRepository.findByActiveS("Y");
	}

	@Override
	public ItemSupplierModel findItemSupplierById(int itemSupplierId) {

		ItemSupplierModel itemSupplierModelRes = getValidItemSupplier(itemSupplierId);

		if (!Objects.nonNull(itemSupplierModelRes)) {
			throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
					HttpStatus.NOT_FOUND);
		}
		log.info("ItemSupplier data with ID : " + itemSupplierModelRes.getItemSupplierId()
		+ " retrieved succesfully");
		return itemSupplierModelRes;
	}

	@Override
	public void deleteItemSupplierById(int itemsupplierId) {

		ItemSupplierModel itemSupplierModelRes = getValidItemSupplier(itemsupplierId);
		if (!Objects.nonNull(itemSupplierModelRes)) {
			throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
					HttpStatus.NOT_FOUND);
		}
		itemSuppliersRepository.delete(itemSupplierModelRes);
		log.info("ItemSupplier data with ID: " + itemSupplierModelRes.getItemSupplierId()
		+ " deleted succesfully");
	}

	@Override
	public void deleteMultipleItemSuppliersById(int[] itemsupplierIds) {

		ItemSupplierModel itemSupplierModelRes;
		for (int itemSupplier : itemsupplierIds) {

			itemSupplierModelRes = getValidItemSupplier(itemSupplier);
			if (!Objects.nonNull(itemSupplierModelRes)) {
				throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
						HttpStatus.NOT_FOUND);
			}
			itemSuppliersRepository.delete(itemSupplierModelRes);
			log.info("ItemSupplier data with ID: " + itemSupplierModelRes.getItemSupplierId()
			+ " deleted succesfully");
		}
	}

	@Override
	public List<ItemSupplierModel> findAllItemSuppliers() {
		return itemSuppliersRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public List<SupplierModel> findAllUnMappedItemSuppliersData(int itemId) {

		List<SupplierModel> response = itemSuppliersRepository.getAllUnMappedSuppliers(itemId);
		return response;
	}

	@Override
	public List<ItemsModel> findAllUnMappedSupplierItems(int supplierId) {
		List<ItemsModel> result = itemSuppliersRepository.getAllUnMappedItems(supplierId);
		return result;
	}

	@Override
	public List<ItemSupplierDTO> findAllMappedItemSuppliers() {

		List<ItemSupplierDTO>  r =itemSuppliersRepository.getAllItemSuppliers();

		return r;
	}

	@Override
	public List<SupplierModel> findAllUnmappedSuppliersNamesSearch(int itemId, String searchTerm) {

		List<SupplierModel> result=itemSuppliersRepository.getAllItemSuppliersSearchData(itemId,searchTerm);

		return result;
	}

	@Override
	public List<ItemsModel> finAllUnmppedItemsNameSearch(int supplierId, String searchTerm) {
		List<ItemsModel> res=itemSuppliersRepository.getAllItemSuppliersItemSearchData(supplierId,searchTerm);
		return res;
	}

	@Override
	public void saveItemSuppliersById(@Valid int itemsupplierId, @Valid String activeS) {
		ItemSupplierModel itemSupplierModelRes = getValidItemSupplier(itemsupplierId);
		if (!Objects.nonNull(itemSupplierModelRes)) {
			throw new IHealthPharmException(itemSupplierHelper.getNotFoundItemSupplierMessage(),
					HttpStatus.NOT_FOUND);
		}

		itemSuppliersRepository.updateStatus(itemsupplierId,activeS);
		log.info("ItemSupplier data with ID: " + itemSupplierModelRes.getItemSupplierId()
		+ " Updated succesfully");

	}

	@Override
	public List<ItemSupplierDTO> findAllMappedItemSuppliersOnItemName(int itemId) {
		List<ItemSupplierDTO> response=itemSuppliersRepository.getAllItemSuppliersBasedOnItemId(itemId);

		return response;
	}

	@Override
	public List<ItemSupplierDTO> findAllSupplierItemOnSupplierId(int supplierId) {
		List<ItemSupplierDTO> response=itemSuppliersRepository.getAllSupplierItemBasedOnDistId(supplierId);
		return response;
	}

	
	
	@Override
	public ItemSupplierModel saveItemSupplierDataModel(@Valid ItemSupplierModel itemSupplierModel) {

		itemSupplierModel = itemSuppliersRepository.save(itemSupplierModel);
		log.info("ItemSupplier data with ID: "+ itemSupplierModel.getItemSupplierId()+" saved succesfully");
		return itemSupplierModel;
	}

}
