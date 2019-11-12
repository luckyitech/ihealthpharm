package com.ihealthpharm.sales.service;

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
}