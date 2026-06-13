package com.ihealthpharm.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dao.PurchaseReturnTypesRepo;
import com.ihealthpharm.finance.model.PurchaseReturnTypesModel;
import com.ihealthpharm.finance.service.PurchaseReturnTypesService;

@Service
@Transactional
public class PurchaseReturnTypesServiceImpl implements PurchaseReturnTypesService {
	
	@Autowired
	private PurchaseReturnTypesRepo prTypeRepo;

	@Override
	public List<PurchaseReturnTypesModel> getAllPrTypes() {
		return prTypeRepo.getAllPrTypes();
	}

}
