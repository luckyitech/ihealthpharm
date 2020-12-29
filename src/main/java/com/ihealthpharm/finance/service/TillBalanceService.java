package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.TillBalanceModel;

public interface TillBalanceService {
	
	TillBalanceModel saveTillBalanceDetails(TillBalanceModel tillBalModel);

	List<String> findAllCustomerNamesBySearch(String customer);

	List<String> findTillCustomerNames();

	List<String> findAllTillAccountsBySearch(String tillAccount);

	List<String> findTillAccounts();

}
