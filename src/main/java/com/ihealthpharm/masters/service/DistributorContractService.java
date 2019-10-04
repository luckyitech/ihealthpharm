package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorContractModel;

public interface DistributorContractService {

public DistributorContractModel saveDistrubutorContractData(DistributorContractModel distributorContractModel );
	
	 DistributorContractModel updateDistrubutorContractData(DistributorContractModel distributorContractModel );
	
	 List<DistributorContractModel> updateDistrubutorContractsData(List<DistributorContractModel> distributorContractModels );
	
	 List<DistributorContractModel> findDistrubutorContractByActive();
	
	 DistributorContractModel findDistrubutorContractById(int distrubutorContractId);
	
	 void deleteDistrubutorContractById(int distrubutorContractId);
	
	 void deleteDistrubutorContractsById(int[] distrubutorContractIds);
	
	 List<DistributorContractModel> findAllDistributorContracts();
}
