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
import com.ihealthpharm.finance.model.PettyCashModel;
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
	
	@GetMapping("expenses/getcounterpartiesbysearch")
	public ResponseEntity<BaseDto<List<String>>> getCounterPartiesBySearch(@RequestParam String searchTerm){
		List<String> expensesRes = expensesService.getAllCounterPartiesBySearch(searchTerm);
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
	@GetMapping("expenses/getallcounterparties")
	public ResponseEntity<BaseDto<List<String>>> getAllCounterParties(){
		List<String> expensesRes = expensesService.getAllCounterParties();
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
	@GetMapping("/getall/expenses/transactionsIds")
	public ResponseEntity<BaseDto<List<expensesDTO>>> getAllBankTransactionsId(@RequestParam String transactionId){
		List<expensesDTO> response=expensesService.findAllTransactionId(transactionId);
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}
	
	@GetMapping("/getall/expensescount")
	public ResponseEntity<BaseDto<Integer>> getAllExpences(){
		Integer response=expensesService.getExpecncesCount();
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}
	
	@GetMapping("/getall/expenses/bypagination")
	public ResponseEntity<BaseDto<List<ExpensesModel>>> getAllExpencesByPagination(@RequestParam Integer pageNumber,@RequestParam Integer limit){
		List<ExpensesModel> response=expensesService.getAllExpecncesByPagination(pageNumber,limit);
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}
	
	@PostMapping("/get/expensesTransactionsCountBySearch")
	public ResponseEntity<BaseDto<Integer>> getAllPettyCashTransactionsBySearch(
			@RequestParam("refNo") String refNo,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("party") String party,@RequestParam("counterParty") String counterParty){
		Integer response=expensesService.findAllExpensesTransactionsCountBySearch(refNo,fromDate,toDate,party,counterParty);
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}	
	
	
	@PostMapping("/getall/expensesBySearch")
	public ResponseEntity<BaseDto<List<ExpensesModel>>> getAllExpensesTransactionsBySearch(
			@RequestParam("refNo") String refNo,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("party") String party,
			@RequestParam("counterParty") String counterParty,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit){
			
		int pageNo=Integer.parseInt(pageNumber);
		int pageLimit=Integer.parseInt(limit);
		
		
		List<ExpensesModel> response=expensesService.findAllExpensesTransactionsBySearch(refNo,fromDate,toDate,
				party,counterParty,pageNo,pageLimit);
		return new BaseDto<>(response, expensesHelper.getRetrieveExpenseMessage(), OK).respond();
	}	
	
}
