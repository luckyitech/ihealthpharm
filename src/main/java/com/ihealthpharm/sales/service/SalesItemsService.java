package com.ihealthpharm.sales.service;

import java.util.Date;
import java.util.List;

import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.model.SalesModel;

public interface SalesItemsService
{
    
    void deleteSalesItemsData(Integer salesItemId);

    SalesItemsModel findSalesItemsData(Integer billId);
    
    List<SalesItemsModel> findAllSalesItemsData();

    List<SalesItemsModel> saveSalesItemsData(List<SalesItemsModel> salesItemsModel);

    SalesItemsModel updateSalesItemsData(SalesItemsModel salesItemsModel);
    
    List<SalesItemsModel> getByBillId(SalesModel sales);
    
    //Sales By Product Details
    
    List<String> findCustomersBySalesItemsSBPD(String searchTerm);
	
	List<String> findAllCustomersBySalesItemsSBPD();

//SBPS
	List<String> finditemNameInSalesSBPS(String searchTerm);

    List<String> findnameInSalesSBPS(String searchTerm);
    
    List<String> findAllitemNameInSalesSBPS();
    
    List<String> findAllnameInSalesSBPS();
	
}