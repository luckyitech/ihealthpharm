package com.ihealthpharm.sales.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.dao.SalesRepository;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {
  
	@Autowired
    SalesRepository salesRepository;

    @Autowired
    SalesHelper salesHelper;
    
	@Override
	public void deleteSalesData(Integer salesId) {
		SalesModel salesRes = getValidSalesItem(salesId);
		if (!Objects.nonNull(salesRes)) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesRepository.delete(salesRes);
		
	}

	@Override
	public SalesModel findSalesData(Integer billId) {
		SalesModel salesRes = salesRepository.findById(billId).get();
		return salesRes;
	}

	@Override
	public SalesModel saveSalesData(SalesModel salesModel) {
		salesModel = salesRepository.save(salesModel);
		return salesModel;
	}

	@Override
	public SalesModel updateSalesData(SalesModel salesModel) {
		SalesModel salesRes = getValidSalesItem(salesModel.getBillId());
		if (!Objects.nonNull(salesRes)) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesRes = salesRepository.save(salesModel);
		return salesRes;
	}
    
	public SalesModel getValidSalesItem(Integer salesId) {
		SalesModel salesRes = null;

		try {
			salesRes = salesRepository.findById(salesId).get();
			return salesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SalesModel> findAllSalesData() {
		
		return salesRepository.findAll();
	}
    
    
}