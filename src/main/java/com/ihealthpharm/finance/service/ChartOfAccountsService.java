package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;

public interface ChartOfAccountsService {

	 List<ChartOfAccountsModel> findAllAccounts();
	 ChartOfAccountsModel saveCOAData(ChartOfAccountsModel chartOfAccountsModel);
	 ChartOfAccountsModel findchartOfAccountsById(Integer accountId);
}
