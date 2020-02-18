package com.ihealthpharm.finance.controller;

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
import com.ihealthpharm.finance.helper.BankTransactionsHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.service.BankTransactionsService;

@RestController
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
	
	@PostMapping("/savebanktransactions")
	public ResponseEntity<BaseDto<BankTransactionsModel>> saveTransaction(@RequestBody BankTransactionsModel bankTransactionsModel){
		
		BankTransactionsModel response=bankTransService.saveTransaction(bankTransactionsModel);
		System.out.println(response);
		return new BaseDto<>(response,bankTransactionsHelper.getSaveBankTransactionsMessage(),OK).respond();
	}
	
	
}
