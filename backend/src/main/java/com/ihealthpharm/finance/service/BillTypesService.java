package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.BillTypeModel;

public interface BillTypesService {
	
	List<BillTypeModel> getAllBillTypes();

	List<String> getCNByBillType();
}
