package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.AccountTypeRepository;
import com.ihealthpharm.finance.model.AccountTypeModel;
import com.ihealthpharm.finance.service.AccountTypeService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountTypeServiceImpl implements AccountTypeService {
	
	
	@Autowired
	AccountTypeRepository accountTypeRepo;

	@Override
	public List<AccountTypeModel> findAllAccountTypes() {		
		return accountTypeRepo.findAll();
	}

	
}
