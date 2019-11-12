package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ProviderModel;

public interface ProviderService {

	 ProviderModel saveProviderData(ProviderModel providerModel);
	
	 ProviderModel updateProviderData(ProviderModel providerModel);
	
	 List<ProviderModel> updateProvidersData(List<ProviderModel> providerModels);
	
	 List<ProviderModel> findProviderByActive();
	
	 ProviderModel findProviderById(int providerId);
	
	 void deleteProviderById( int providerId);
	
	 void deleteProvidersById( int[] providerIds);
	
	 List<ProviderModel> findAllProviders();
	 
	 List<ProviderModel> findLimitProviders();

	List<ProviderModel> findProvidersDataByName(String providerName);
	
}
