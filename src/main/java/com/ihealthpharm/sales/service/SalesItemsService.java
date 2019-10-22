package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.sales.model.SalesItemsModel;

public interface SalesItemsService
{
    
    void deleteSalesItemsData(Integer salesItemId);

    SalesItemsModel findSalesItemsData(Integer billId);
    
    List<SalesItemsModel> findAllSalesItemsData();

    SalesItemsModel saveSalesItemsData(SalesItemsModel salesItemsModel);

    SalesItemsModel updateSalesItemsData(SalesItemsModel salesItemsModel);
}