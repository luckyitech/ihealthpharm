package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.helper.BankTransactionsHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.service.BankTransactionsService;
import com.ihealthpharm.stock.service.InvoiceService;

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
	
	@GetMapping("/getall/transactionsIds")
	public ResponseEntity<BaseDto<List<BankTransactionDTO>>> getAllBankTransactionsId(@RequestParam String transactionId){
		List<BankTransactionDTO> response=bankTransService.findAllTransactionId(transactionId);
		return new BaseDto<>(response, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}
	
//	@GetMapping("/checktransctionids")
//	
//	public ResponseEntity<String> checkTransactionId(@RequestParam(name="transactionId") String transactionId){
//		String res=bankTransService.checkByTransactionId(transactionId);
//
//		return ResponseEntity.ok().body(res);
//		
//	}
	
	@GetMapping("/getpartyaccountdetailsbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPartyAccountsBySearch(@RequestParam String searchTerm) {
		List<String> results = bankTransService.getBySearchPartyDetails(searchTerm);
		return new BaseDto<>(results, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}

	
	@GetMapping("/getallpartyaccountdetails")
	public ResponseEntity<BaseDto<List<String>>> getAllPartyAccounts() {
		List<String> results = bankTransService.getAllPartyDetails();
		return new BaseDto<>(results, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}
	@GetMapping("/getcounterpartyaccountdetailsbysearch")
	public ResponseEntity<BaseDto<List<String>>> getCounterPartyAccountsBySearch(@RequestParam String searchTerm) {
		List<String> results = bankTransService.getBySearchCounterPartyDetails(searchTerm);
		return new BaseDto<>(results, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}

	@GetMapping("/getallcounterpartyaccountdetails")
	public ResponseEntity<BaseDto<List<String>>> getAllCounterPartyAccounts() {
		List<String> results = bankTransService.getAllCounterPartyDetails();
		return new BaseDto<>(results, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}

	@GetMapping("/getall/bankTransactions/byRefNo")
	public ResponseEntity<BaseDto<List<BankTransactionsModel>>> getAllBankTransactionsByRefNo(@RequestParam String referenceNo){
		List<BankTransactionsModel> response=bankTransService.findAllBankTransactionsByRefNo(referenceNo);
		return new BaseDto<>(response, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}	
	
	
	@PostMapping("/get/bankTransactionsBySearch")
	public ResponseEntity<BaseDto<List<BankTransactionsModel>>> getAllBankTransactionsBySearch(
			@RequestParam("refNo") String refNo,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("party") String party,@RequestParam("counterParty") String counterParty){
		List<BankTransactionsModel> response=bankTransService.findAllBankTransactionsBySearch(refNo,fromDate,toDate,party,counterParty);
		return new BaseDto<>(response, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}	
	
	
	@GetMapping("/get/bankTransactionDetails")
	public ResponseEntity<BaseDto<BankTransactionsModel>> getBankTransactionDetails(@RequestParam Integer bankTransactionId){
		BankTransactionsModel response=bankTransService.findBankTxnDetailsById(bankTransactionId);
		return new BaseDto<>(response, bankTransactionsHelper.getRetrieveBankTransactionsMessage(), OK).respond();
	}	
	
	@PostMapping("update/chartOfAccountWithPreviousAmt")
	public void updateChartOfAccountWithPrevAmt(
			@RequestParam("party") String party,@RequestParam("counterParty") String counterParty,
			@RequestParam("amount") String amount){
		
		bankTransService.updateChartOfAccountBal(party,counterParty,amount);
	
	}
	
	
	
	
	
}
