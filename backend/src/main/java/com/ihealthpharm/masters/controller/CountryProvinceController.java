package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.CountryStateHelper;
import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.StateModel;
import com.ihealthpharm.masters.service.CountryStateMasterService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class CountryProvinceController {

	@Autowired
	CountryStateMasterService countryStateMasterService;

	@Autowired
	private CountryStateHelper countryStateHelper;

	@GetMapping("/getCountries")
	public ResponseEntity<BaseDto<List<CountryModel>>> getCountries() {
		List<CountryModel> countryRes = countryStateMasterService.findAllCountries();
		return new BaseDto<>(countryRes, countryStateHelper.getSaveCountryMessage(), OK).respond();
	}

	@GetMapping("/getProvinces")
	public ResponseEntity<BaseDto<List<StateModel>>> getProvinces() {
		List<StateModel> stateRes = countryStateMasterService.findAllStates();
		return new BaseDto<>(stateRes, countryStateHelper.getSaveProvinceMessage(), OK).respond();

	}
	
	@PostMapping("/getProvincebyid")
	public ResponseEntity<BaseDto<List<StateModel>>> getProvinceById(@RequestBody CountryModel countryModel) {
		log.info("country object is : " + countryModel);
		List<StateModel> stateRes = countryStateMasterService.findAllStatesByCountryId(countryModel);
		return new BaseDto<>(stateRes, countryStateHelper.getRetrieveCountryMessage(), OK).respond();

	}

	@PostMapping("/savecountry")
	public ResponseEntity<BaseDto<CountryModel>> saveCountry(@RequestBody CountryModel countryModel) {
		log.info("Country Object: " + countryModel);
		countryModel = countryStateMasterService.saveCountryData(countryModel);
		return new BaseDto<>(countryModel, countryStateHelper.getSaveCountryMessage(), OK).respond();
	}

	@PostMapping("/saveprovince")
	public ResponseEntity<BaseDto<StateModel>> saveState(@RequestBody StateModel stateModel) {
		stateModel = countryStateMasterService.saveStateData(stateModel);
		return new BaseDto<>(stateModel, countryStateHelper.getSaveProvinceMessage(), OK).respond();
	}
}
