package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ihealthpharm.finance.dao.BillTypesRepository;
import com.ihealthpharm.finance.model.BillTypeModel;
import com.ihealthpharm.finance.service.BillTypesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BillTypesServiceImpl implements BillTypesService {

	@Autowired
	BillTypesRepository billTypesRepository;

	@Override
	public List<BillTypeModel> getAllBillTypes() {
		return billTypesRepository.getAllBillTypes();
	}

	@Override
	public List<String> getCNByBillType() {
		return billTypesRepository.getCNByBillType();
	}
	
	
}
