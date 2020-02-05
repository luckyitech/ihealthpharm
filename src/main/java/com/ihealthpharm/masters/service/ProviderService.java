package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ProviderModel;

public interface ProviderService {

	 ProviderModel saveProviderData(ProviderModel providerModel);
	
	 ProviderModel updateProviderData(ProviderModel providerModel);
	
	 List<ProviderModel> updateProvidersData(List<ProviderModel> providerModels);
	
	 List<ProviderModel> findProviderByActive();
	
	 ProviderModel findProviderById(Integer providerId);
	
	 void deleteProviderById( Integer providerId);
	
	 void deleteProvidersById( Integer[] providerIds);
	
	 List<ProviderModel> findAllProviders();
	 
	 List<ProviderModel> findLimitProviders();
	 
	 List<ProviderModel> findLimitProvidersForSalesBilling();

	List<ProviderModel> findProvidersDataByName(String providerName);
	
}
