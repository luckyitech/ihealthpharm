package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.model.GeneralLedgerModel;

public interface GeneralLedgerService {
	
	GeneralLedgerModel saveGeneraledger(GeneralLedgerModel generalLedgerModel);
	
	List<GeneralLedgerModel> saveMutipleLedgersData(List<AccountPayablesModel> generalLedgerModels);
	
    List<GeneralLedgerModel> saveMultipleLedgersDataForAccRecievables(List<AccountReceivablesModel> generalLedgerModels);
    
    GeneralLedgerModel saveGeneralLedgerData(AccountReceivablesModel generalLedgerModel);
    
    Double getTotalLimit();
}
