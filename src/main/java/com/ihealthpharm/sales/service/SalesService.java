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
}