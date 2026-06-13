package com.ihealthpharm.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dao.SalesReturnTypesRepo;
import com.ihealthpharm.finance.model.SalesReturnTypesModel;
import com.ihealthpharm.finance.service.SalesReturnTypesService;

@Service
@Transactional
public class SalesReturnTypesServiceImpl implements SalesReturnTypesService {

	@Autowired
	private SalesReturnTypesRepo srTypesRepo;
	
	@Override
	public List<SalesReturnTypesModel> getAllPrTypes() {
		return srTypesRepo.getAllsrTypes();
	}

}
