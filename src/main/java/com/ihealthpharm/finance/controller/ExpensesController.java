package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.dto.expensesDTO;
import com.ihealthpharm.finance.helper.ExpensesHelper;
import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.service.ExpensesService;


@RestController
@CrossOrigin
public class ExpensesController {
	
	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private ExpensesHelper expensesHelper;
	
	@PostMapping("save/expenses")
	public ResponseEntity<BaseDto<ExpensesModel>> saveExpenses(@RequestBody ExpensesModel expensesModel){
		ExpensesModel expensesRes = expensesService.saveExpenses(expensesModel);
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
	@GetMapping("update/pettycash/balance")
	public ResponseEntity<BaseDto<Integer>> updatePettyCashBalance(@RequestParam Integer pettyCashId,@RequestParam Double balance){
		Integer expensesRes = expensesService.updatePettyBalance(pettyCashId,balance);
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
	
	@GetMapping("expenses/getpartiesbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPartiesBySearch(@RequestParam String searchTerm){
		List<String> expensesRes = expensesService.getAllPartiesBySearch(searchTerm);
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}

	@GetMapping("expenses/getallparties")
	public ResponseEntity<BaseDto<List<String>>> getAllParties(){
		List<String> expensesRes = expensesService.getAllParties();
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
	@GetMapping("/getall/expenses/transactionsIds")
	public ResponseEntity<BaseDto<List<expensesDTO>>> getAllBankTransactionsId(@RequestParam String transactionId){
		List<expensesDTO> response=expensesService.findAllTransactionId(transactionId);
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}
}
