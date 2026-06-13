package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.TillBalanceRepository;
import com.ihealthpharm.finance.model.TillBalanceModel;
import com.ihealthpharm.finance.service.TillBalanceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TillBalanceServiceImpl implements TillBalanceService{
	
	@Autowired
	TillBalanceService tillBalanceService;
	
	@Autowired
	TillBalanceRepository tillBalanceRepo;

	@Override
	public TillBalanceModel saveTillBalanceDetails(TillBalanceModel tillBalModel) {
		TillBalanceModel tillBalRes=tillBalanceRepo.save(tillBalModel);
		return tillBalRes;
	}

	@Override
	public List<String> findAllCustomerNamesBySearch(String customer) {
		return tillBalanceRepo.findAllTillCustomersBySearch(customer);
	}

	@Override
	public List<String> findTillCustomerNames() {
		
		return tillBalanceRepo.findAllTillcustomers();
	}

	@Override
	public List<String> findAllTillAccountsBySearch(String tillAccount) {
		
		return tillBalanceRepo.findTillAccountsBySearch(tillAccount);
	}

	@Override
	public List<String> findTillAccounts() {
		
		return tillBalanceRepo.findAllTillAccounts();
	}
	
	
}
