package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ProviderModel;

public interface ProviderService {

	public ProviderModel saveProviderData(ProviderModel providerModel);
	
	public ProviderModel updateProviderData(ProviderModel providerModel);
	
	public List<ProviderModel> updateProvidersData(List<ProviderModel> providerModels);
	
	public List<ProviderModel> findProviderByActive();
	
	public ProviderModel findProviderById(int providerId);
	
	public void deleteProviderById( int providerId);
	
	public void deleteProvidersById( int[] providerIds);
	
	public List<ProviderModel> findAllProviders();
	
}
