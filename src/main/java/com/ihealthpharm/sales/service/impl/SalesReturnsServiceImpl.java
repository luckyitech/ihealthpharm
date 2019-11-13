package com.ihealthpharm.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public SalesReturnModel saveSalesReturnDate(SalesReturnModel  salesReturnModel) {
		salesReturnModel = salesReturnRepo.save(salesReturnModel);
		log.info("SalesReturn data with ID: "+ salesReturnModel.getSalesReturnId()+" saved succesfully");
		return salesReturnModel;
	}


	@Override
	public List<SalesReturnModel> findAllSalesReturns() {
		return salesReturnRepo.findAllByOrderByLastUpdateTimestampDesc();
	}


}
