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
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AccountReceivablesController {

	@Autowired
	AccountReceivablesService accountReceivablesService;

	@Autowired
	AccountReceivablesHelper accountReceivablesHelper;
	
	@Autowired
	SalesHelper salesHelper;

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
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesData(@RequestParam Integer accountReceivablesId) {
		log.info("Request Object for delete is: ", accountReceivablesId);
		accountReceivablesService.deleteAccountReceivablesById(accountReceivablesId);
		return new BaseDto<>(accountReceivablesHelper.getDeleteAccountReceivablesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/accountsReceivables")
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesData(@RequestParam Integer[] accountReceivablesIds) {

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
	public ResponseEntity<BaseDto<AccountReceivablesModel>> getAccountReceivablesDataById(@RequestParam Integer accountReceivablesId) {
		AccountReceivablesModel result = accountReceivablesService.findAccountReceivablesById(accountReceivablesId);
		return new BaseDto<>(result, accountReceivablesHelper.getRetrieveAccountReceivablesMessage(), OK).respond();
	}

	@GetMapping("/getbillsbycustomerid")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllBillsBasedOnCustomerId(@RequestParam Integer customerId){
		
		List<SalesModel> result=accountReceivablesService.getAllBillsByCustomerId(customerId);
		
		return new BaseDto<>(result,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getcustomersbycustomerid")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllCustomersBasedOnCustomerId(@RequestParam Integer customerId){
		
		List<SalesModel> result=accountReceivablesService.getAllCustomersByCustomerId(customerId);
		
		return new BaseDto<>(result,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	//to update salesreturn totalAmount
	@GetMapping("/getaccreceipts/basedonbillid")
		public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAccountRecivableData(@RequestParam Integer salesModel){
		System.out.println(salesModel);
		List<AccountReceivablesModel> result = accountReceivablesService.findAccountReceivablesByBillId(salesModel);
		return new BaseDto<>(result, accountReceivablesHelper.getRetrieveAccountReceivablesMessage(), OK).respond();
	}
	
	@GetMapping("/getsalesbasedon/SalesNumber")
	public ResponseEntity<BaseDto<List<SalesModel>>> getSalesBasedOnSalesNumber(@RequestParam String billCode){
		List<SalesModel> response=accountReceivablesService.getAllSalesBySearch(billCode);
		return new BaseDto<>(response,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
}
