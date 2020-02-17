package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.ChartOfAccountRepository;
import com.ihealthpharm.finance.helper.ChartOfAccountsHelper;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.service.ChartOfAccountsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ChartOfAccountsServiceImpl implements ChartOfAccountsService {

	@Autowired
	 ChartOfAccountRepository chartAccRepo;
	
	@Autowired
	ChartOfAccountsHelper chartAccHelper;
	
	
	@Override
	public List<ChartOfAccountsModel> findAllAccounts() {
		return chartAccRepo.findAll();
	}

}
