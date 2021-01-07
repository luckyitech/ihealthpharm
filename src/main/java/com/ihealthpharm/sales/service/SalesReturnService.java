package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesReturnModel;

public interface SalesReturnService {
	
	SalesReturnModel saveSalesReturnData(SalesReturnModel  salesReturnModel);
	
	List<SalesReturnModel> findAllSalesReturns();

	SalesReturnModel updateSalesReturns(SalesReturnModel salesReturnModel);

	void deleteSalesReturnById(Integer salesReturnId);
	
	List<String> getLastSRIByEmp(String searchTerm);

	List<String> getLastSRIByCust(String searchTerm);
	
	List<SalesReturnModel> getAllSalesRetuns();
}
