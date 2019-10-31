package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesBillsModel;

public interface AccountReceivablesBillsService
{
    
	AccountReceivablesBillsModel saveAccountReceivablesBills(AccountReceivablesBillsModel accountReceivablesBills);

	AccountReceivablesBillsModel updateAccountReceivablesBillsData(AccountReceivablesBillsModel accountReceivablesBills);
	
	 List<AccountReceivablesBillsModel> updateAccountsReceivablesBillsData(List<AccountReceivablesBillsModel> accountsReceivablesBills);
	
	 List<AccountReceivablesBillsModel> findAllAccountsReceivablesBills();
	
	 AccountReceivablesBillsModel findAccountReceivablesBillsById(int accountReceivablesBillsId);
	
	 void deleteAccountReceivablesBillsById(int accountReceivablesBillsIds);
	
	 void deleteAccountsReceivablesBillsById(int[] accountReceivablesBillsIds);
	 

	List<AccountReceivablesBillsModel> findAccountReceivablesBillsByActive();
}