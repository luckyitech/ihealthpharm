package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesReturnModel;

public interface SalesReturnService {
	
	SalesReturnModel saveSalesReturnDate(SalesReturnModel  salesReturnModel);
	
	List<SalesReturnModel> findAllSalesReturns();

}
