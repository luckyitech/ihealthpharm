package com.ihealthpharm.sales.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.dao.SalesReturnRepository;
import com.ihealthpharm.sales.helper.SalesReturnHelper;
import com.ihealthpharm.sales.model.SalesReturnModel;
import com.ihealthpharm.sales.service.SalesReturnService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SalesReturnsServiceImpl implements SalesReturnService {

	@Autowired
	private SalesReturnRepository salesReturnRepo;
	
	@Autowired
	private SalesReturnHelper salesReturnHelper;
	
	
	@Override
	public SalesReturnModel saveSalesReturnData(SalesReturnModel  salesReturnModel) {
		salesReturnModel = salesReturnRepo.save(salesReturnModel);
		log.info("SalesReturn data with ID: "+ salesReturnModel.getSalesReturnId()+" saved succesfully");
		return salesReturnModel;
	}


	@Override
	public List<SalesReturnModel> findAllSalesReturns() {
		return salesReturnRepo.findAllByOrderByLastUpdateTimestampDesc();
	}


	@Override
	public SalesReturnModel updateSalesReturns(SalesReturnModel salesReturnModel) {
		SalesReturnModel salesReturnResp = validateSalesReturn(salesReturnModel.getSalesReturnId());
		if (!Objects.nonNull(salesReturnResp)) {
			throw new IHealthPharmException(salesReturnHelper.getNotFoundMessage(),
					HttpStatus.NOT_FOUND);
		}
		SalesReturnModel salesRtnObj = salesReturnRepo.save(salesReturnModel);	
		log.info("SalesReturn data with ID : " + salesRtnObj.getSalesReturnId() + " updated succesfully");
		return salesRtnObj;
	}


	@Override
	public void deleteSalesReturnById(Integer salesReturnId) {
		SalesReturnModel salesOrderRes = salesReturnRepo.getOne(salesReturnId);
		if (!Objects.nonNull(salesOrderRes)) {
			throw new IHealthPharmException(salesReturnHelper.getNotFoundMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("SalesReturn data with ID : " + salesOrderRes.getSalesReturnId() + " Deleted succesfully");
		salesReturnRepo.delete(salesOrderRes);
		
	}

	
	public SalesReturnModel validateSalesReturn(Integer salesReurnId) {
		try {
			return salesReturnRepo.findById(salesReurnId).get();
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesReturnHelper.getNotFoundMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	

}
