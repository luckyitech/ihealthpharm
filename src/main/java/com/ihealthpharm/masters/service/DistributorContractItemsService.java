package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorContractItemsModel;

public interface DistributorContractItemsService {

public DistributorContractItemsModel saveDistrubutorContractItemData(DistributorContractItemsModel distributorContractItemsModel );
	
	public DistributorContractItemsModel updateDistrubutorContractItemData(DistributorContractItemsModel distributorContractItemsModel );
	
	public List<DistributorContractItemsModel> updateDistrubutorContractItemsData(List<DistributorContractItemsModel> distributorContractItemsModels );
	
	public List<DistributorContractItemsModel> findDistrubutorContractItemByActive();
	
	public DistributorContractItemsModel findDistrubutorContractItemById(int distrubutorContractItemId);
	
	public void deleteDistrubutorContractItemById(int distrubutorContractItemId);
	
	public void deleteDistrubutorContractItemsById(int[] distrubutorContractItemIds);
	
	public List<DistributorContractItemsModel> findAllDistributorContractItems();
}
