package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.ProviderHelper;
import com.ihealthpharm.masters.model.ProviderLookupTypeModel;
import com.ihealthpharm.masters.model.ProviderModel;
import com.ihealthpharm.masters.service.ProviderLookupTypeService;
import com.ihealthpharm.masters.service.ProviderService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin
@RestController
@Slf4j
public class ProviderController {
	String resMessage;

	@Autowired
	private ProviderService providerService;

	@Autowired
	private ProviderLookupTypeService providerLookupTypeService; 

	@Autowired
	private ProviderHelper providerHelper;

	@PostMapping("/save/provider")
	public ResponseEntity<BaseDto<ProviderModel>> insertProviderData(@Valid @RequestBody ProviderModel providerModel) {
		log.info("Request Object insert is: "+ providerModel.toString());
		ProviderModel providerModelRes = providerService.saveProviderData(providerModel);
		return new BaseDto<>(providerModelRes,providerHelper.getSaveProviderMessage(),OK).respond();
	}

	@PutMapping("/update/provider")
	public ResponseEntity<BaseDto<ProviderModel>> updateProviderData(@Valid @RequestBody ProviderModel providerModel) {
		log.info("Request Object for update is: ", providerModel);
		ProviderModel providerModelRes = providerService.updateProviderData(providerModel);
		return new BaseDto<>(providerModelRes,providerHelper.getUpdateProviderMessage(),OK).respond();
	}

	@DeleteMapping("/delete/provider")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam Integer providerId) {
		log.info("Request Object for delete is: ", providerId);
		providerService.deleteProviderById(providerId);
		return new BaseDto<>(providerHelper.getDeleteProviderMessage(), OK).respond();
	}

	@GetMapping("/getactiveprovidersdata")
	public ResponseEntity<BaseDto<List<ProviderModel>>> getProviderData() {
		List<ProviderModel> result = providerService.findProviderByActive();
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}

	@GetMapping("/getallprovidersdata")
	public ResponseEntity<BaseDto<List<ProviderModel>>> getAllProviderData() {
		List<ProviderModel> result = providerService.findAllProviders();
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}

	@GetMapping("/getlimitedprovidersdata")
	public ResponseEntity<BaseDto<List<ProviderModel>>> getLimitProviderData() {
		List<ProviderModel> result = providerService.findLimitProviders();
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}
	
	@GetMapping("/getlimitedprovidersdata/forsalesbilling")
	public ResponseEntity<BaseDto<List<ProviderModel>>> getLimitProviderDataFor() {
		List<ProviderModel> result = providerService.findLimitProvidersForSalesBilling();
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}
	
	
	@GetMapping("/getprovidersdatabyname")
	public ResponseEntity<BaseDto<List<ProviderModel>>> getProviderDataByName(@RequestParam("key") String name) {
		System.out.println(name);
		List<ProviderModel> result = providerService.findProvidersDataByName(name);
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}
	
	@GetMapping("/getallprovidertypelookuptypedata")
	public ResponseEntity<BaseDto<List<ProviderLookupTypeModel>>> getProviderLookupTypeDataData() {
		List<ProviderLookupTypeModel> result = providerLookupTypeService.findAllProviderLookupTypes();
		return new BaseDto<>(result, providerHelper.getRetrieveProviderTypeLookupMessage(), OK).respond();
	}
	
	

	@GetMapping("/getproviderdatabyid")
	public ResponseEntity<BaseDto<ProviderModel>> getProviderDataById(@RequestParam Integer providerId) {
		ProviderModel result = providerService.findProviderById(providerId);
		return new BaseDto<>(result, providerHelper.getRetrieveProvideMessage(), OK).respond();
	}

	@DeleteMapping("/delete/providers")
	public ResponseEntity<BaseDto<Object>> deleteProvidersData(@RequestParam Integer[] providersId) {
		log.info("Request Object for delete is: "+providersId[0]);
		providerService.deleteProvidersById(providersId);
		return new BaseDto<>(providerHelper.getDeleteProviderMessage(), OK).respond();
	}

	@PutMapping("/update/providers")
	public ResponseEntity<BaseDto<List<ProviderModel>>> updateProvidersData(@Valid @RequestBody List<ProviderModel> providersModel) {
		log.info("Request Object for update is: "+ providersModel.toString());
		List<ProviderModel> providerModelRes = providerService.updateProvidersData(providersModel);
		return new BaseDto<>(providerModelRes,providerHelper.getUpdateProviderMessage(),OK).respond();
	}

}
