package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.ChartOfAccountsHelper;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.service.ChartOfAccountsService;

@RestController
@CrossOrigin
public class ChartOfAccountsController {

	@Autowired
	private ChartOfAccountsService chartAccountService;
	
	@Autowired
	private ChartOfAccountsHelper chartAccountHelper;
	
	@GetMapping("/getall/chartofaccounts")
	public ResponseEntity<BaseDto<List<ChartOfAccountsModel>>> getAllChartOfAccounts(){
		List<ChartOfAccountsModel> response=chartAccountService.findAllAccounts();
		return new BaseDto<>(response, chartAccountHelper.getSaveChartOfAccountsMessage(), OK).respond();
	}
	
	@GetMapping("/getBalance")
	public ResponseEntity<BaseDto<Double>> getBalance(@RequestParam Integer accountId){
		Double results = chartAccountService.findBalance(accountId);
		return new BaseDto<Double>(results, chartAccountHelper.getRetrieveChartOfAccountsMessage(),OK).respond();

	}
	
	@GetMapping("/getbypharmacyid/chartofaccounts")
	public ResponseEntity<BaseDto<List<ChartOfAccountsModel>>> getChartOfAccountsById(@Valid @RequestParam Integer pharmacyId){
		List<ChartOfAccountsModel> response=chartAccountService.findchartOfAccountsByPharmaId(pharmacyId);
		return new BaseDto<>(response, chartAccountHelper.getRetrieveChartOfAccountsMessage(), OK).respond();
	}
	

	@GetMapping("/getaccountdetails/byid")
	public ResponseEntity<BaseDto<ChartOfAccountsModel>> getchartOfAccountsById(@Valid @RequestParam Integer accountId){
		ChartOfAccountsModel chartOfAccountsRes= chartAccountService.findchartOfAccountsById(accountId);
		return new BaseDto<>(chartOfAccountsRes,chartAccountHelper.getRetrieveChartOfAccountsMessage(),OK).respond();
	}
	
	@PostMapping("/save/COAdetails")
	public ResponseEntity<BaseDto<ChartOfAccountsModel>> insertChartOfAccountsData(@Valid @RequestBody ChartOfAccountsModel chartOfAccountsModel) {
		ChartOfAccountsModel chartOfAccRes = chartAccountService.saveCOAData(chartOfAccountsModel);
		return new BaseDto<>(chartOfAccRes, chartAccountHelper.getSaveChartOfAccountsMessage(), OK).respond();
	}
	
	@PostMapping("/update/COAdetails")
	public ResponseEntity<BaseDto<ChartOfAccountsModel>> updateChartOfAccountsData(@Valid @RequestBody ChartOfAccountsModel chartOfAccountsModel){
		ChartOfAccountsModel COARes=chartAccountService.updateChartOfAccData(chartOfAccountsModel);
		return new BaseDto<>(COARes,chartAccountHelper.getUpdateChartOfAccountsMessage(),OK).respond();
	}
	
	@GetMapping("/getCOA/basedon/accountno")
	public ResponseEntity<BaseDto<List<ChartOfAccountsModel>>> getAllChartOfAccountsBySearch(@RequestParam String accountNo){
		List<ChartOfAccountsModel> result=chartAccountService.getAllCOABasedOnAccNo(accountNo);
		return new BaseDto<>(result,chartAccountHelper.getRetrieveChartOfAccountsMessage(),OK).respond();
	}
	
	@GetMapping("/getByType/byAccountType")
	public ResponseEntity<BaseDto<List<ChartOfAccountsModel>>> getchartOfAccountsByAccType(@RequestParam String accountType){
		List<ChartOfAccountsModel> chartOfAccountsRes= chartAccountService.findchartOfAccountsByType(accountType);
		return new BaseDto<>(chartOfAccountsRes,chartAccountHelper.getRetrieveChartOfAccountsMessage(),OK).respond();
	}

}
