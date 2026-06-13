package com.ihealthpharm.masters.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.CountryRepository;
import com.ihealthpharm.masters.dao.StateRepository;
import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.StateModel;
import com.ihealthpharm.masters.service.CountryStateMasterService;

@Service
@Transactional
public class CountryStateMasterServiceImpl implements CountryStateMasterService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<CountryModel> findAllCountries()
	{
		return countryRepository.findAll();
	}
	
	public List<StateModel> findAllStates()
	{
		return stateRepository.findAll();
	}

	@Override
	public CountryModel saveCountryData(CountryModel countryModel) {
		return countryRepository.save(countryModel);
	}

	@Override
	public StateModel saveStateData(StateModel stateModel) {
		return stateRepository.save(stateModel);
	}

	@Override
	public List<StateModel> findAllStatesByCountryId(CountryModel countryId) {
		
		return stateRepository.findByCountryId(countryId);
	}
}