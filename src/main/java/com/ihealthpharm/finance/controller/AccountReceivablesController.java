package com.ihealthpharm.finance.controller;

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
import com.ihealthpharm.finance.helper.AccountReceivablesHelper;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.service.AccountReceivablesService;
import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AccountReceivablesController {

	@Autowired
	AccountReceivablesService accountReceivablesService;

	@Autowired
	AccountReceivablesHelper accountReceivablesHelper;

	@PostMapping("/save/accountReceivables")
	public ResponseEntity<BaseDto<AccountReceivablesModel>> insertAccountReceivablesData(@Valid @RequestBody AccountReceivablesModel accountReceivablesModel) {

		AccountReceivablesModel accountReceivablesModelRes = accountReceivablesService.saveAccountReceivablesData(accountReceivablesModel);
		return new BaseDto<>(accountReceivablesModelRes, accountReceivablesHelper.getSaveAccountReceivablesMessage(), OK).respond();
	}

	@PutMapping("/update/accountReceivables")
	public ResponseEntity<BaseDto<AccountReceivablesModel>> updateAccountReceivablesData(@Valid @RequestBody AccountReceivablesModel accountReceivablesModel) {
		log.info("Request Object for update is: ", accountReceivablesModel);
		AccountReceivablesModel accountReceivablesModelRes = accountReceivablesService.updateAccountReceivablesData(accountReceivablesModel);
		return new BaseDto<>(accountReceivablesModelRes, accountReceivablesHelper.getUpdateAccountReceivablesMessage(), OK).respond();
	}

	@PutMapping("/update/accountsReceivables")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> updateAccountsReceivablesData(@Valid @RequestBody List<AccountReceivablesModel> accountReceivablesModel) {
		log.info("Request Object for update is: " , accountReceivablesModel);
		List<AccountReceivablesModel> AccountReceivablesModelRes = accountReceivablesService.updateAccountsReceivablesData(accountReceivablesModel);
		return new BaseDto<>(AccountReceivablesModelRes, accountReceivablesHelper.getUpdateAccountReceivablesMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/accountReceivables")
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesData(@RequestParam int accountReceivablesId) {
		log.info("Request Object for delete is: ", accountReceivablesId);
		accountReceivablesService.deleteAccountReceivablesById(accountReceivablesId);
		return new BaseDto<>(accountReceivablesHelper.getDeleteAccountReceivablesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/accountsReceivables")
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesData(@RequestParam int[] accountReceivablesIds) {

		log.info("Request Object for delete is: " + accountReceivablesIds[0]);
		accountReceivablesService.deleteAccountsReceivablesById(accountReceivablesIds);
		return new BaseDto<>(accountReceivablesHelper.getDeleteAccountReceivablesMessage(), OK).respond();
	}

	
	@GetMapping("/getaccountReceivablesdata")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAccountReceivablesData() {
		List<AccountReceivablesModel> result = accountReceivablesService.findAllAccountsReceivables();
		return new BaseDto<>(result, accountReceivablesHelper.getRetrieveAccountReceivablesMessage(), OK).respond();
	}

	@GetMapping("/getaccountReceivablesdatabyid")
	public ResponseEntity<BaseDto<AccountReceivablesModel>> getAccountReceivablesDataById(@RequestParam int accountReceivablesId) {
		AccountReceivablesModel result = accountReceivablesService.findAccountReceivablesById(accountReceivablesId);
		return new BaseDto<>(result, accountReceivablesHelper.getRetrieveAccountReceivablesMessage(), OK).respond();
	}

/*	@GetMapping("/getbillsbycustomerid")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllBillsBasedOnCustomerId(@RequestParam CustomerInsuranceModel customerInsuranceModel){
		System.out.println("in grid"+customerInsuranceModel);
		List<SalesModel> result=accountReceivablesService.getAllBillsByCustomerId(customerInsuranceModel);
		log.info("---------------------------------");
		log.info(result.toString());
		log.info("---------------------------------");
		return new BaseDto<>(result,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}*/
	
	@GetMapping("/getbillsbycustomerid")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllBillsBasedOnCustomerId(@RequestParam int customerId){
		System.out.println("in grid"+customerId);
		List<SalesModel> result=accountReceivablesService.getAllBillsByCustomerId(customerId);
		log.info("---------------------------------");
		log.info(result.toString());
		log.info("---------------------------------");
		return new BaseDto<>(result,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
}
