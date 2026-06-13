package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesBillsModel;

public interface AccountReceivablesBillsService
{
    
	AccountReceivablesBillsModel saveAccountReceivablesBillsData(AccountReceivablesBillsModel accountReceivablesBills);

	AccountReceivablesBillsModel updateAccountReceivablesBillsData(AccountReceivablesBillsModel accountReceivablesBills);
	
	 List<AccountReceivablesBillsModel> updateAccountsReceivablesBillsData(List<AccountReceivablesBillsModel> accountsReceivablesBills);
	
	 List<AccountReceivablesBillsModel> findAllAccountsReceivablesBills();
	
	 AccountReceivablesBillsModel findAccountReceivablesBillsById(Integer accountReceivablesBillsId);
	
	 void deleteAccountReceivablesBillsById(Integer accountReceivablesBillsIds);
	
	 void deleteAccountsReceivablesBillsById(Integer[] accountReceivablesBillsIds);
	 

	
}