package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorContractItemsModel;

public interface DistributorContractItemsService {

public DistributorContractItemsModel saveDistrubutorContractItemData(DistributorContractItemsModel distributorContractItemsModel );
	
	 DistributorContractItemsModel updateDistrubutorContractItemData(DistributorContractItemsModel distributorContractItemsModel );
	
	 List<DistributorContractItemsModel> updateDistrubutorContractItemsData(List<DistributorContractItemsModel> distributorContractItemsModels );
	
	 List<DistributorContractItemsModel> findDistrubutorContractItemByActive();
	
	 DistributorContractItemsModel findDistrubutorContractItemById(int distrubutorContractItemId);
	
	 void deleteDistrubutorContractItemById(int distrubutorContractItemId);
	
	 void deleteDistrubutorContractItemsById(int[] distrubutorContractItemIds);
	
	 List<DistributorContractItemsModel> findAllDistributorContractItems();
}
