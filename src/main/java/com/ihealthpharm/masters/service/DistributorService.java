package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorModel;

public interface DistributorService {

	 DistributorModel saveDistrubutorData(DistributorModel distributorModel );
	
	 DistributorModel updateDistrubutorData(DistributorModel distributorModel );
	
	 List<DistributorModel> updateDistrubutorsData(List<DistributorModel> distributorModels );
	
	 List<DistributorModel> findDistrubutorByActive();
	
	 DistributorModel findDistrubutorById(int distrubutorId);
	
	 void deleteDistrubutorById(int distrubutorId);
	
	 void deleteDistrubutorsById(int[] distrubutorIds);
	
	 List<DistributorModel> findAllDistributors();

	 List<DistributorModel> findAllDistributorsByName(String searchTerm);
}
