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
import com.ihealthpharm.finance.helper.AccountPayablesInvoicesHelper;
import com.ihealthpharm.finance.model.AccountPayablesInvoicesModel;
import com.ihealthpharm.finance.service.AccountPayablesInvoicesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AccountPayablesInvoicesController {

	@Autowired
	AccountPayablesInvoicesService accountPayablesInvoicesService;

	@Autowired
	AccountPayablesInvoicesHelper accountPayablesInvoicesHelper;

	@PostMapping("/save/accountPayablesInvoices")
	public ResponseEntity<BaseDto<AccountPayablesInvoicesModel>> insertAccountPayablesInvoicesData(@Valid @RequestBody AccountPayablesInvoicesModel accountPayablesInvoicesModel) {

		AccountPayablesInvoicesModel accountPayablesInvoicesModelRes = accountPayablesInvoicesService.saveAccountPayablesInvoicesData(accountPayablesInvoicesModel);
		return new BaseDto<>(accountPayablesInvoicesModelRes, accountPayablesInvoicesHelper.getSaveAccountPayablesInvoicesMessage(), OK).respond();
	}

	@PutMapping("/update/accountPayablesInvoices")
	public ResponseEntity<BaseDto<AccountPayablesInvoicesModel>> updateAccountPayablesInvoicesData(@Valid @RequestBody AccountPayablesInvoicesModel accountPayablesInvoicesModel) {
		log.info("Request Object for update is: ", accountPayablesInvoicesModel);
		AccountPayablesInvoicesModel accountPayablesInvoicesModelRes = accountPayablesInvoicesService.updateAccountPayablesInvoicesData(accountPayablesInvoicesModel);
		return new BaseDto<>(accountPayablesInvoicesModelRes, accountPayablesInvoicesHelper.getUpdateAccountPayablesInvoicesMessage(), OK).respond();
	}

	@PutMapping("/update/accountsPayablesInvoices")
	public ResponseEntity<BaseDto<List<AccountPayablesInvoicesModel>>> updateAccountsPayablesInvoicesData(@Valid @RequestBody List<AccountPayablesInvoicesModel> accountPayablesInvoicesModel) {
		log.info("Request Object for update is: " , accountPayablesInvoicesModel);
		List<AccountPayablesInvoicesModel> AccountPayablesInvoicesModelRes = accountPayablesInvoicesService.updateAccountsPayablesInvoicesData(accountPayablesInvoicesModel);
		return new BaseDto<>(AccountPayablesInvoicesModelRes, accountPayablesInvoicesHelper.getUpdateAccountPayablesInvoicesMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/accountPayablesInvoices")
	public ResponseEntity<BaseDto<Object>> deleteAccountPayablesInvoicesData(@RequestParam int accountPayablesInvoicesId) {
		log.info("Request Object for delete is: ", accountPayablesInvoicesId);
		accountPayablesInvoicesService.deleteAccountPayablesInvoicesById(accountPayablesInvoicesId);
		return new BaseDto<>(accountPayablesInvoicesHelper.getDeleteAccountPayablesInvoicesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/accountsPayablesInvoices")
	public ResponseEntity<BaseDto<Object>> deleteAccountPayablesInvoicesData(@RequestParam int[] accountPayablesInvoicesIds) {

		log.info("Request Object for delete is: " + accountPayablesInvoicesIds[0]);
		accountPayablesInvoicesService.deleteAccountsPayablesInvoicesById(accountPayablesInvoicesIds);
		return new BaseDto<>(accountPayablesInvoicesHelper.getDeleteAccountPayablesInvoicesMessage(), OK).respond();
	}

	
	@GetMapping("/getaccountPayablesInvoicesdata")
	public ResponseEntity<BaseDto<List<AccountPayablesInvoicesModel>>> getAccountPayablesInvoicesData() {
		List<AccountPayablesInvoicesModel> result = accountPayablesInvoicesService.findAllAccountsPayablesInvoices();
		return new BaseDto<>(result, accountPayablesInvoicesHelper.getRetrieveAccountPayablesInvoicesMessage(), OK).respond();
	}

	@GetMapping("/getaccountPayablesInvoicesdatabyid")
	public ResponseEntity<BaseDto<AccountPayablesInvoicesModel>> getAccountPayablesInvoicesDataById(@RequestParam int accountPayablesInvoicesId) {
		AccountPayablesInvoicesModel result = accountPayablesInvoicesService.findAccountPayablesInvoicesById(accountPayablesInvoicesId);
		return new BaseDto<>(result, accountPayablesInvoicesHelper.getRetrieveAccountPayablesInvoicesMessage(), OK).respond();
	}

	
	
}
