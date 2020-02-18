package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
