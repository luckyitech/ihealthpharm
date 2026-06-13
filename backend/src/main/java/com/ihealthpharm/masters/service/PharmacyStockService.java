package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.PharmacyStockModel;

public interface PharmacyStockService {

	PharmacyStockModel addStock(@Valid PharmacyStockModel pharmacyStockModel);

	PharmacyStockModel updateStock(@Valid PharmacyStockModel pharmacyStockModel);

	List<PharmacyStockModel> updateStocks(@Valid List<PharmacyStockModel> pharmacyStockModels);

	void deleteStock(Integer stockId);

	PharmacyStockModel findStockById(Integer stockId);

	List<PharmacyStockModel> findAllPharmaStocks();

}
