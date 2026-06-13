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
import com.ihealthpharm.masters.helper.CompanyTermsAndConditionsHelper;
import com.ihealthpharm.masters.model.CompanyTermsModel;
import com.ihealthpharm.masters.service.CompanyTermsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CompanyTermsController {
	
	@Autowired
	private CompanyTermsAndConditionsHelper companyTermsHelper;
	
	@Autowired
	private CompanyTermsService companyTermsService;

	

	@PostMapping("/save/companyterms")
	public ResponseEntity<BaseDto<CompanyTermsModel>> insertCompanyTermsData(@Valid @RequestBody CompanyTermsModel CompanyTermsModel) {
		log.info("Request Object insert is: "+ CompanyTermsModel.toString());
		CompanyTermsModel companyTermsRes = companyTermsService.saveCompanyTermsData(CompanyTermsModel);
		return new BaseDto<>(companyTermsRes,companyTermsHelper.getSaveComapanyTermsMessage(),OK).respond();
	}
	
	@PutMapping("/update/companyterms")
	public ResponseEntity<BaseDto<CompanyTermsModel>> updateCompanyTermData(@Valid @RequestBody CompanyTermsModel companyTermsAndConditions) {
		log.info("Request Object for update is: ",companyTermsAndConditions.toString());
		CompanyTermsModel companyTermsRes = companyTermsService.updateCompanyTermsData(companyTermsAndConditions);
		return new BaseDto<>(companyTermsRes,companyTermsHelper.getUpdateComapanyTermsMessage(),OK).respond();
	}
	

	@PutMapping("/update/multiplecompanyterms")
	public ResponseEntity<BaseDto<List<CompanyTermsModel>>> updateCompanyTermsData(@Valid @RequestBody List<CompanyTermsModel> companyTermsModels) {
		log.info("Request Object for update is: "+ companyTermsModels.toString());
		List<CompanyTermsModel> companyRes = companyTermsService.updateCompanyTermsData(companyTermsModels);
		return new BaseDto<>(companyRes,companyTermsHelper.getUpdateComapanyTermsMessage(),OK).respond();
	}
	
	
	@DeleteMapping("/delete/companyterms")
	public ResponseEntity<BaseDto<Object>> deleteCompanyTermsData(@RequestParam Integer companyTermsId) {
		log.info("Request Object for delete is: ", companyTermsId);
		companyTermsService.deleteCompanyTermsById(companyTermsId);
		return new BaseDto<>(companyTermsHelper.getDeleteComapanyTermsMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/multiplecompanyterms")
	public ResponseEntity<BaseDto<Object>> deleteCompanyTermsMultipleData(@RequestParam Integer[] companyTermsIds) {
		log.info("Request Object for delete is: "+ companyTermsIds.toString());
		companyTermsService.deleteCompanyTermsDataByTds(companyTermsIds);
		return new BaseDto<>(companyTermsHelper.getDeleteComapanyTermsMessage(), OK).respond();
	}

	@GetMapping("/getallcompanytermsdata")
	public ResponseEntity<BaseDto<List<CompanyTermsModel>>> getAllComapnyTermsdata() {
		List<CompanyTermsModel> result = companyTermsService.findAllCompanyTerms();
		return new BaseDto<>(result, companyTermsHelper.getRetrieveComapanyTermsMessage(), OK).respond();
	}
	
	@GetMapping("/getcompanytermsdatabyid")
	public ResponseEntity<BaseDto<CompanyTermsModel>> getCompanyTermsDataById(@RequestParam Integer companyTCId) {
		CompanyTermsModel result = companyTermsService.findCompanyTermsById(companyTCId);
		return new BaseDto<>(result, companyTermsHelper.getRetrieveComapanyTermsMessage(), OK).respond();
	}
	
	
	
}
