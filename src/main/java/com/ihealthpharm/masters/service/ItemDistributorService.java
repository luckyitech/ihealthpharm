package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemDistributorModel;

public interface ItemDistributorService {


	ItemDistributorModel saveItemDistributorData( int[] itemsId,  int[] distributorsId);

	ItemDistributorModel updateItemDistributorData(ItemDistributorModel itemDistributor);

	List<ItemDistributorModel> updateItemDistributorsData(List<ItemDistributorModel> itemDistributorModels);

	List<ItemDistributorModel> findItemDistributorByActive();

	ItemDistributorModel findItemDistributorById(int itemDistributorId);

	void deleteItemDistributorById( int itemDistributorId);

	void deleteMultipleItemDistributorsById(int[] itemDistributorIds);
	
	List<ItemDistributorModel> findAllItemDistributors();


}
