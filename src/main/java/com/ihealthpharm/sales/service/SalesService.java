package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesModel;

public interface SalesService
{
    
    void deleteSalesData(Integer billId);

    SalesModel findSalesData(Integer billId);
    
    List<SalesModel> findAllSalesData();
    
    List<SalesModel> findLimitedSalesData();
    
    List<String> getBillNumbersTop100();
    
    List<String> getBillNumbersBySearch(String key);
    
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

	//DBL
	
	List<String> findBillDatesBySalesDBL(String searchTerm);
	
	List<String> findfirst_nmBySalesDBL(String searchTerm);
	
	List<String> findnameBySalesDBL(String searchTerm);
	
	List<String> findAllBillDatesBySalesDBL();
	
	List<String> findAllfirst_nmBySalesDBL();
	
	List<String> findAllnameBySalesDBL();
	
//SRD	
	
	List<String> findbillDateINSalesSRD(String searchTerm);
	
	List<String> findtypeINSalesSRD(String searchTerm);
	
	List<String> findAllbillDateINSalesSRD();
	
	List<String> findAlltypeINSalesSRD();
	
	
//SRADL
	
	List<String> findcityNameINSalesSRADL(String searchTerm);
}