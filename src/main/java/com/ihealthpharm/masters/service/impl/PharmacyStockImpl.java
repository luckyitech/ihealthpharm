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
import com.ihealthpharm.masters.dao.PharmacyStockRepository;
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyStockModel;
import com.ihealthpharm.masters.service.PharmacyStockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PharmacyStockImpl implements PharmacyStockService {
	
	@Autowired
	private PharmacyStockRepository pharmaStockRepo;
	
	@Autowired
	private PharmacyHelper pharmacyHelper;
	
	
	
	@Override
	public PharmacyStockModel addStock(@Valid PharmacyStockModel pharmacyStockModel) {
	
		pharmacyStockModel=pharmaStockRepo.save(pharmacyStockModel);
		log.info("PharmacyStock data with Id:"+pharmacyStockModel.getStockPointId()+"saved SuccessFully");
		return pharmacyStockModel;
	}
	
	
	private PharmacyStockModel getValidStock(int stockPointId) {
		
		PharmacyStockModel pharmacyStock=null;
		
		try {
			pharmacyStock=pharmaStockRepo.findById(stockPointId).get();
			return pharmacyStock;
		}catch (NoSuchElementException noSuchElementException) {
            throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(),HttpStatus.NOT_FOUND);
		}
	}
	

	@Override
	public PharmacyStockModel updateStock(@Valid PharmacyStockModel pharmacyStockModel) {
		
		PharmacyStockModel stockModelRes =getValidStock(pharmacyStockModel.getStockPointId());

		if(!Objects.nonNull(stockModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(),HttpStatus.NOT_FOUND);
		}

		stockModelRes = pharmaStockRepo.save(stockModelRes); 
		log.info("PharmacyStock data with ID : "+ stockModelRes.getStockPointId()+" updated succesfully");
		return stockModelRes;
	}

	@Override
	public List<PharmacyStockModel> updateStocks(@Valid List<PharmacyStockModel> pharmacyStockModels) {
	
		for (PharmacyStockModel stocks : pharmacyStockModels) {
			PharmacyStockModel stockRes =getValidStock(stocks.getStockPointId()); 
			if (!Objects.nonNull(stockRes)) {
				throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
			}

			stockRes = pharmaStockRepo.save(stocks); 
			log.info("PharmacyStock data with Multiple IDs : " + stockRes.getStockPointId() + " updated succesfully");
		}
		
		return pharmacyStockModels;
		
	}

	@Override
	public void deleteStock(int stockId) {
		PharmacyStockModel stockModelRes = getValidStock(stockId);
		if(!Objects.nonNull(stockModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(),HttpStatus.NOT_FOUND);
		}	
		pharmaStockRepo.delete(stockModelRes);
		log.info("PharmacyStock data with ID: "+ stockModelRes.getStockPointId()+" deleted succesfully");		
	}

	@Override
	public PharmacyStockModel findStockById(int stockId) {
		PharmacyStockModel stockModelRes = getValidStock(stockId);

		if(!Objects.nonNull(stockModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("PharmacyStock data with ID : "+ stockModelRes.getStockPointId()+" retrieved succesfully");
		return stockModelRes;
	}


	@Override
	public List<PharmacyStockModel> findAllPharmaStocks() {
		
		return pharmaStockRepo.findAllByOrderByCreationTimeStampDesc();
	}

}
