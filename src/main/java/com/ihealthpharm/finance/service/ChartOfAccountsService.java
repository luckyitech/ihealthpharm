package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.sales.model.SalesModel;

public interface ChartOfAccountsService {


	List<ChartOfAccountsModel> findAllAccounts();

	public Double findBalance(Integer accountId);


	ChartOfAccountsModel saveCOAData(ChartOfAccountsModel chartOfAccountsModel);

	ChartOfAccountsModel findchartOfAccountsById(Integer accountId); 

	List<ChartOfAccountsModel> findchartOfAccountsByPharmaId(Integer pharmacyId);

	ChartOfAccountsModel updateChartOfAccData(ChartOfAccountsModel chartOfAccountsModel);

	List<ChartOfAccountsModel> getAllCOABasedOnAccNo(String accountNo);

}
