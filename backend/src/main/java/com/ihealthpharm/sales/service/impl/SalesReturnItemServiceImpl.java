package com.ihealthpharm.sales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.dao.SalesReturnItemRepository;
import com.ihealthpharm.sales.helper.SalesReturnItemHelper;
import com.ihealthpharm.sales.model.SalesReturnItemsModel;
import com.ihealthpharm.sales.service.SalesReturnItemService;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class SalesReturnItemServiceImpl implements SalesReturnItemService{

	@Autowired
	SalesReturnItemRepository salesReturnItemRepo;
	
	@Autowired
	SalesReturnItemHelper salesReturnItemHelper;
	
	@Override
	public List<SalesReturnItemsModel> saveSalesReturnItemData( List<SalesReturnItemsModel> salesReturnItemsModels) {
		
		List<SalesReturnItemsModel> salesItemsRes = new ArrayList<>();
		for(SalesReturnItemsModel saleItemModel:salesReturnItemsModels)
		{	
			salesItemsRes.add(salesReturnItemRepo.save(saleItemModel));
		}
		return salesItemsRes;
		
	}

	@Override
	public SalesReturnItemsModel updateSalesReturnsItemData(@Valid SalesReturnItemsModel salesReturnItemsModel) {
		
		SalesReturnItemsModel salesReturnItemsRes = getValidSalesReturnItems(salesReturnItemsModel.getSalesReturnItemId());
		if (!Objects.nonNull(salesReturnItemsRes)) {
			throw new IHealthPharmException(salesReturnItemHelper.getNotFoundSalesReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		salesReturnItemsRes = salesReturnItemRepo.save(salesReturnItemsRes);
		log.info("SalesRetrunItems data with ID : " + salesReturnItemsRes.getSalesReturnItemId() + " updated succesfully");
		return salesReturnItemsRes;
	}

	@Override
	public void deleteSalesReturnItemsById(Integer salesReturnItemId) {
		SalesReturnItemsModel salesReturnItemsRes = salesReturnItemRepo.getOne(salesReturnItemId);
		if (!Objects.nonNull(salesReturnItemsRes)) {
			throw new IHealthPharmException(salesReturnItemHelper.getNotFoundSalesReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("SalesRetrunItems data with ID : " + salesReturnItemsRes.getSalesReturnItemId() + " Deleted succesfully");
		salesReturnItemRepo.delete(salesReturnItemsRes);		
	}

	@Override
	public List<SalesReturnItemsModel> findAllSalesReturnItems() {
		return salesReturnItemRepo.findAll();
	}

	@Override
	public SalesReturnItemsModel findSalesReturnItemsById(Integer salesReturnItemId) {
    
		SalesReturnItemsModel salesReturnItemsRes = getValidSalesReturnItems(salesReturnItemId);
		if (!Objects.nonNull(salesReturnItemsRes)) {
			throw new IHealthPharmException(salesReturnItemHelper.getNotFoundSalesReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("SalesRetrunItems data with ID : " + salesReturnItemsRes.getSalesReturnItemId() + " retrieved succesfully");
		return salesReturnItemsRes;
	}

	@Override
	public void deletePurchaseReturnItemsById(Integer[] salesReturnItemIds) {
		SalesReturnItemsModel salesReturnItemsRes;
		for (int salesReturnItems : salesReturnItemIds) {
			salesReturnItemsRes = getValidSalesReturnItems(salesReturnItems);
			if (!Objects.nonNull(salesReturnItemsRes)) {
				throw new IHealthPharmException(salesReturnItemHelper.getNotFoundSalesReturnItemMessage(),
						HttpStatus.NOT_FOUND);
			}
			salesReturnItemRepo.delete(salesReturnItemsRes);
			log.info("SalesRetrunItems data with ID: " + salesReturnItemsRes.getSalesReturnItemId() + " deleted succesfully");
		}		
	}
	
	
	public SalesReturnItemsModel getValidSalesReturnItems(Integer salesReturnItemId) {
		SalesReturnItemsModel salesReturnItemsRes = null;

		try {
			salesReturnItemsRes = salesReturnItemRepo.findById(salesReturnItemId).get();
			salesReturnItemsRes.getItems();
			return salesReturnItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesReturnItemHelper.getNotFoundSalesReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<String> findSalesReturnNoInSalesReturn(String searchTerm) {
		return salesReturnItemRepo.findsalesReturnNoInSalesReturn(searchTerm);
	}

	@Override
	public List<String> findAllSalesReturnNumbersInSalesReturn() {
		return salesReturnItemRepo.findallsalesReturnNoInSalesReturn();
	}

	@Override
	public Integer getReturnQtyByItemId(Integer itemId,Integer billId) {
		return salesReturnItemRepo.getReturnQtyByItem(itemId,billId);
	}

	@Override
	public List<SalesReturnItemsModel> getReturnItemsById(Integer salesReturnId) {
		return salesReturnItemRepo.getAllItemsById(salesReturnId);
	}

}
