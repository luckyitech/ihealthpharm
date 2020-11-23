package com.ihealthpharm.finance.service;
import java.util.HashMap;
import java.util.List;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;


public interface PettyCashService {
	List<PettyCashModel> findAllPettyCash();

	PettyCashModel savepettyCashData(PettyCashModel pettyCashDetails);
	
	PettyCashModel findPettyCashById(Integer pettyCashId);

	List<String> getAllCounterPartyDetails();

	List<String> getBySearchCounterPartyDetails(String searchTerm);

	List<String> getAllPartyDetails();

	List<String> getBySearchPartyDetails(String searchTerm);

	List<PettyCashModel> findAllPettyCashTransactionsBySearch(String refNo, String fromDate, String toDate,
			String party, String counterParty,Integer startPosition, Integer limit);

	HashMap<String, ChartOfAccountsModel> updateChartOfAccountBalFromPettyScreen(String party, String counterParty,
			String amount, String selectedParty, String selectedCounterParty);

	List<PettyCashModel> getAllPettyCashTxnByPagination(Integer pageNumber,Integer limit);

	Integer getAllPettyCashCount();

	Integer findAllPettyCashTransactionsBySearchCount(String refNo, String fromDate, String toDate, String party,
			String counterParty);


}
