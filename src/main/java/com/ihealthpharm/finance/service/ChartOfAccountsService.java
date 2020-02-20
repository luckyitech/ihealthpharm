package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;

public interface ChartOfAccountsService {

	List<ChartOfAccountsModel> findAllAccounts();

	ChartOfAccountsModel saveCOAData(ChartOfAccountsModel chartOfAccountsModel);

	ChartOfAccountsModel findchartOfAccountsById(Integer accountId); 

	List<ChartOfAccountsModel> findchartOfAccountsByPharmaId(Integer pharmacyId);

	ChartOfAccountsModel updateChartOfAccData(ChartOfAccountsModel chartOfAccountsModel);

	List<ChartOfAccountsModel> getAllCOABasedOnAccNo(String accountNo);
}
