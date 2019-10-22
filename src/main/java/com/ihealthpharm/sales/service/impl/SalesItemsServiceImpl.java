package com.ihealthpharm.sales.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.dao.SalesItemsRepository;
import com.ihealthpharm.sales.helper.SalesItemsHelper;
import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.service.SalesItemsService;

@Service
public class SalesItemsServiceImpl implements SalesItemsService {

    @Autowired
    SalesItemsRepository salesItemsRepository;

    @Autowired
    SalesItemsHelper salesItemsHelper;
    
	@Override
	public void deleteSalesItemsData(Integer salesItemId) {
		SalesItemsModel salesItemsRes = getValidSalesItem(salesItemId);
		if (!Objects.nonNull(salesItemsRes)) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesItemsRepository.delete(salesItemsRes);
		
	}

	@Override
	public SalesItemsModel findSalesItemsData(Integer billId) {
		SalesItemsModel salesItemsRes = salesItemsRepository.findById(billId).get();
		return salesItemsRes;
	}

	@Override
	public SalesItemsModel saveSalesItemsData(SalesItemsModel salesItemsModel) {
		salesItemsModel = salesItemsRepository.save(salesItemsModel);
		return salesItemsModel;
	}

	@Override
	public SalesItemsModel updateSalesItemsData(SalesItemsModel salesItemsModel) {
		SalesItemsModel salesItemsRes = getValidSalesItem(salesItemsModel.getBillId());
		if (!Objects.nonNull(salesItemsRes)) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesItemsRes = salesItemsRepository.save(salesItemsModel);
		return salesItemsRes;
	}
    
	public SalesItemsModel getValidSalesItem(Integer salesItemId) {
		SalesItemsModel salesItemsRes = null;

		try {
			salesItemsRes = salesItemsRepository.findById(salesItemId).get();
			return salesItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SalesItemsModel> findAllSalesItemsData() {
		
		return salesItemsRepository.findAll();
	}
}