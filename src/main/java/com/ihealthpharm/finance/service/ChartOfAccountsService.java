package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.sales.model.SalesModel;

public interface ChartOfAccountsService {

	 List<ChartOfAccountsModel> findAllAccounts();
	 
	 public Double findBalance(Integer accountId);
}
