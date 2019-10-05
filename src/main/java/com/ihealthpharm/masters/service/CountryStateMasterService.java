package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.StateModel;

public interface CountryStateMasterService {

	List<CountryModel> findAllCountries();

	List<StateModel> findAllStates();

	CountryModel saveCountryData(CountryModel countryModel);

	StateModel saveStateData(StateModel stateModel);

	List<StateModel> findAllStatesByCountryId(CountryModel countryId);
}
