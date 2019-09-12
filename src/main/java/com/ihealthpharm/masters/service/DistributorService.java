package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorModel;

public interface DistributorService {

	public DistributorModel saveDistrubutorData(DistributorModel distributorModel );
	
	public DistributorModel updateDistrubutorData(DistributorModel distributorModel );
	
	public List<DistributorModel> updateDistrubutorsData(List<DistributorModel> distributorModels );
	
	public List<DistributorModel> findDistrubutorByActive();
	
	public DistributorModel findDistrubutorById(int distrubutorId);
	
	public void deleteDistrubutorById(int distrubutorId);
	
	public void deleteDistrubutorsById(int[] distrubutorIds);
	
	public List<DistributorModel> findAllDistributors();
}
