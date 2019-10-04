package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemDistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemDistributorService {

    
	ItemDistributorModel saveItemDistributorData( int[] itemsId,  int[] distributorsId);
	
	ItemDistributorModel updateItemDistributorData(ItemDistributorModel itemDistributor);

	List<ItemDistributorModel> updateItemDistributorsData(List<ItemDistributorModel> itemDistributorModels);

	List<ItemDistributorModel> findItemDistributorByActive();

	ItemDistributorModel findItemDistributorById(int itemDistributorId);

	void deleteItemDistributorById( int itemDistributorId);

	void deleteMultipleItemDistributorsById(int[] itemDistributorIds);
	
	List<ItemDistributorModel> findAllItemDistributors();

	List<DistributorModel> findAllUnMappedItemDistributorsData(int itemId);

	List<ItemsModel> findAllUnMappedDistributorItems(int distributorId);

	List<ItemDistributorModel> findAllMappedItemDistributors();

	List<DistributorModel> findAllUnmappedDistributorsNamesSearch(int itemId,String searchTerm);

	List<ItemsModel> finAllUnmppedItemsNameSearch(int distributorId, String searchTerm);

	void saveItemDistributorsById(int itemDistributorId,String activeS);


}
