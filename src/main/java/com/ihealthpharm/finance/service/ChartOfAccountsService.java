package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;

public interface ChartOfAccountsService {

	 List<ChartOfAccountsModel> findAllAccounts();
}
