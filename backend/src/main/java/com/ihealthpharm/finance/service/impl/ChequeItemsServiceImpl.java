package com.ihealthpharm.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dao.ChequeItemsRepository;
import com.ihealthpharm.finance.helper.ChequeItemsHelper;
import com.ihealthpharm.finance.model.ChequeItemsModel;
import com.ihealthpharm.finance.service.ChequeItemsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ChequeItemsServiceImpl implements ChequeItemsService {

	
	@Autowired
	private ChequeItemsRepository chequeItemsRepo;
	
	@Autowired
	private ChequeItemsHelper chequeItemsHelper;
	
	
	@Override
	public ChequeItemsModel saveChequeItem(ChequeItemsModel chequeItemsModel) {
		chequeItemsModel = chequeItemsRepo.save(chequeItemsModel);
		log.info("ChequeItemItem data with ID: " + chequeItemsModel.getChequeItemsId() + " saved succesfully");
		return chequeItemsModel;
	}

}
