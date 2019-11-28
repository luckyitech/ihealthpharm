package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesModel;

public interface SalesService
{
    
    void deleteSalesData(Integer billId);

    SalesModel findSalesData(Integer billId);
    
    List<SalesModel> findAllSalesData();
    
    List<SalesModel> findLimitedSalesData();
    
    SalesModel saveSalesData(SalesModel salesModel);

    SalesModel updateSalesData(SalesModel salesModel);
    
     List<SalesModel> findByCriteria(String status,String code, String codeValue, String startDate, String endDate);

	SalesModel getSaleByBillCode(String searchTerm);
	
	List totalSalesByMonthWiseData();
	
	List<String> findManufacturerBySales(String searchTerm);
	
	List<String> findAllManufacturerBySales();
	
	List<String> findProvidersBySales(String searchTerm);
	
	List<String> findAllProvidersBySales();
	
	List<String> findBillDateBySales(String searchTerm);
	
	List<String> findAllBillDtaessBySales();
}