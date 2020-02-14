package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.BankTransactionsHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.service.BankTransactionsService;

@Repository
@CrossOrigin
public class BankTransactionsController {

	@Autowired
	private BankTransactionsService bankTransService;
	
	@Autowired
	private BankTransactionsHelper bankTransactionsHelper;
	
	
	@GetMapping("/getall/banktransactions")
	public ResponseEntity<BaseDto<List<BankTransactionsModel>>> getAllBankTransactions(){
		List<BankTransactionsModel> response=bankTransService.findAllBankTransactions();
		return new BaseDto<>(response, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}
	
	
	
}
