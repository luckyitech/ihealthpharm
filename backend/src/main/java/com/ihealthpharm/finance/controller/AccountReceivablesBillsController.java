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
import com.ihealthpharm.finance.helper.AccountReceivablesBillsHelper;
import com.ihealthpharm.finance.model.AccountReceivablesBillsModel;
import com.ihealthpharm.finance.service.AccountReceivablesBillsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AccountReceivablesBillsController {

	@Autowired
	AccountReceivablesBillsService accountReceivablesBillsService;

	@Autowired
	AccountReceivablesBillsHelper accountReceivablesBillsHelper;

	@PostMapping("/save/accountReceivablesBills")
	public ResponseEntity<BaseDto<AccountReceivablesBillsModel>> insertAccountReceivablesBillsData(@Valid @RequestBody AccountReceivablesBillsModel accountReceivablesBillsModel) {

		AccountReceivablesBillsModel accountReceivablesBillsModelRes = accountReceivablesBillsService.saveAccountReceivablesBillsData(accountReceivablesBillsModel);
		return new BaseDto<>(accountReceivablesBillsModelRes, accountReceivablesBillsHelper.getSaveAccountReceivablesBillsMessage(), OK).respond();
	}

	@PutMapping("/update/accountReceivablesBills")
	public ResponseEntity<BaseDto<AccountReceivablesBillsModel>> updateAccountReceivablesBillsData(@Valid @RequestBody AccountReceivablesBillsModel accountReceivablesBillsModel) {
		log.info("Request Object for update is: ", accountReceivablesBillsModel);
		AccountReceivablesBillsModel accountReceivablesBillsModelRes = accountReceivablesBillsService.updateAccountReceivablesBillsData(accountReceivablesBillsModel);
		return new BaseDto<>(accountReceivablesBillsModelRes, accountReceivablesBillsHelper.getUpdateAccountReceivablesBillsMessage(), OK).respond();
	}

	@PutMapping("/update/accountsReceivablesBills")
	public ResponseEntity<BaseDto<List<AccountReceivablesBillsModel>>> updateAccountsReceivablesBillsData(@Valid @RequestBody List<AccountReceivablesBillsModel> accountReceivablesBillsModel) {
		log.info("Request Object for update is: " , accountReceivablesBillsModel);
		List<AccountReceivablesBillsModel> AccountReceivablesBillsModelRes = accountReceivablesBillsService.updateAccountsReceivablesBillsData(accountReceivablesBillsModel);
		return new BaseDto<>(AccountReceivablesBillsModelRes, accountReceivablesBillsHelper.getUpdateAccountReceivablesBillsMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/accountReceivablesBills")
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesBillsData(@RequestParam Integer accountReceivablesBillsId) {
		log.info("Request Object for delete is: ", accountReceivablesBillsId);
		accountReceivablesBillsService.deleteAccountReceivablesBillsById(accountReceivablesBillsId);
		return new BaseDto<>(accountReceivablesBillsHelper.getDeleteAccountReceivablesBillsMessage(), OK).respond();
	}

	@DeleteMapping("/delete/accountsReceivablesBills")
	public ResponseEntity<BaseDto<Object>> deleteAccountReceivablesBillsData(@RequestParam Integer[] accountReceivablesBillsIds) {

		log.info("Request Object for delete is: " + accountReceivablesBillsIds[0]);
		accountReceivablesBillsService.deleteAccountsReceivablesBillsById(accountReceivablesBillsIds);
		return new BaseDto<>(accountReceivablesBillsHelper.getDeleteAccountReceivablesBillsMessage(), OK).respond();
	}

	
	@GetMapping("/getaccountReceivablesBillsdata")
	public ResponseEntity<BaseDto<List<AccountReceivablesBillsModel>>> getAccountReceivablesBillsData() {
		List<AccountReceivablesBillsModel> result = accountReceivablesBillsService.findAllAccountsReceivablesBills();
		return new BaseDto<>(result, accountReceivablesBillsHelper.getRetrieveAccountReceivablesBillsMessage(), OK).respond();
	}

	@GetMapping("/getaccountReceivablesBillsdatabyid")
	public ResponseEntity<BaseDto<AccountReceivablesBillsModel>> getAccountReceivablesBillsDataById(@RequestParam Integer accountReceivablesBillsId) {
		AccountReceivablesBillsModel result = accountReceivablesBillsService.findAccountReceivablesBillsById(accountReceivablesBillsId);
		return new BaseDto<>(result, accountReceivablesBillsHelper.getRetrieveAccountReceivablesBillsMessage(), OK).respond();
	}

	
	
}
