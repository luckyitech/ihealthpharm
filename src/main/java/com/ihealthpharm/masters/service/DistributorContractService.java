package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorContractModel;

public interface DistributorContractService {

public DistributorContractModel saveDistrubutorContractData(DistributorContractModel distributorContractModel );
	
	public DistributorContractModel updateDistrubutorContractData(DistributorContractModel distributorContractModel );
	
	public List<DistributorContractModel> updateDistrubutorContractsData(List<DistributorContractModel> distributorContractModels );
	
	public List<DistributorContractModel> findDistrubutorContractByActive();
	
	public DistributorContractModel findDistrubutorContractById(int distrubutorContractId);
	
	public void deleteDistrubutorContractById(int distrubutorContractId);
	
	public void deleteDistrubutorContractsById(int[] distrubutorContractIds);
	
	public List<DistributorContractModel> findAllDistributorContracts();
}
