package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesModel;

public interface SalesService
{
    
    void deleteSalesData(Integer billId);

    SalesModel findSalesData(Integer billId);
    
    List<SalesModel> findAllSalesData();

    SalesModel saveSalesData(SalesModel salesModel);

    SalesModel updateSalesData(SalesModel salesModel);
    
    public List<SalesModel> findByCriteria(String status,String code, String codeValue, String startDate, String endDate);

	SalesModel getSaleByBillCode(String searchTerm);
}