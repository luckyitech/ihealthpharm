package com.ihealthpharm.finance.service;
import java.util.List;

import com.ihealthpharm.finance.model.PettyCashModel;


public interface PettyCashService {
	List<PettyCashModel> findAllPettyCash();

	PettyCashModel savepettyCashData(PettyCashModel pettyCashDetails);
	
	PettyCashModel findPettyCashById(Integer pettyCashId);

	List<String> getAllCounterPartyDetails();

	List<String> getBySearchCounterPartyDetails(String searchTerm);

	List<String> getAllPartyDetails();

	List<String> getBySearchPartyDetails(String searchTerm);


}
