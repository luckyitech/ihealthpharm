package com.ihealthpharm.finance.controller;
import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.ChartOfAccountsHelper;
import com.ihealthpharm.finance.helper.PettyCashHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.PettyCashService;

@RestController
@CrossOrigin
public class PettyCashController {

	@Autowired
	private PettyCashService pettyCashService;
	
	@Autowired
	private PettyCashHelper pettyCashHelper;
	
	@Autowired
	private ChartOfAccountsHelper coaHelper;
	
	
	
	@GetMapping("/getall/pettycashdetails")
	public ResponseEntity<BaseDto<List<PettyCashModel>>> getAllPettyCash(){
		List<PettyCashModel> response=pettyCashService.findAllPettyCash();
		return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	@PostMapping("/save/pettycashdetails")
	public ResponseEntity<BaseDto<PettyCashModel>> insertPettyCashData(@Valid @RequestBody PettyCashModel pettyCashModel) {
		PettyCashModel pettyCashModelRes = pettyCashService.savepettyCashData(pettyCashModel);
		return new BaseDto<>(pettyCashModelRes, pettyCashHelper.getSavepettyCashMessage(), OK).respond();
	}
	
	@GetMapping("/getpettycashdetails/byid")
	public ResponseEntity<BaseDto<PettyCashModel>> getPettyCashById(@Valid @RequestParam Integer pettyCashId){
		PettyCashModel pettyCashRes= pettyCashService.findPettyCashById(pettyCashId);
		return new BaseDto<>(pettyCashRes,pettyCashHelper.getRetrivepettyCashMessage(),OK).respond();
	}
	
	//Petty Cash Expenditure Report
	@GetMapping("pettycash/getpartyaccountdetailsbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPettyPartyAccountsBySearch(@RequestParam String searchTerm) {
		List<String> results = pettyCashService.getBySearchPartyDetails(searchTerm);
		return new BaseDto<>(results, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}

	
	@GetMapping("pettycash/getallpartyaccountdetails")
	public ResponseEntity<BaseDto<List<String>>> getAllPettyPartyAccounts() {
		List<String> results = pettyCashService.getAllPartyDetails();
		return new BaseDto<>(results, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	@GetMapping("pettycash/getcounterpartyaccountdetailsbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPettyCounterPartyAccountsBySearch(@RequestParam String searchTerm) {
		List<String> results = pettyCashService.getBySearchCounterPartyDetails(searchTerm);
		return new BaseDto<>(results, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}

	@GetMapping("pettycash/getallcounterpartyaccountdetails")
	public ResponseEntity<BaseDto<List<String>>> getAllPettyCounterPartyAccounts() {
		List<String> results = pettyCashService.getAllCounterPartyDetails();
		return new BaseDto<>(results, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	
	//Asha
	@PostMapping("/get/pettyCashTransactionsBySearch")
	public ResponseEntity<BaseDto<List<PettyCashModel>>> getAllPettyCashTransactionsBySearch(
			@RequestParam("refNo") String refNo,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("party") String party,
			@RequestParam("counterParty") String counterParty,
			@RequestParam("startPosition") String startPosition,
			@RequestParam("limit") String limit){
		
		
		int startOfRecords=Integer.parseInt(startPosition);
		int pageLimit=Integer.parseInt(limit);
		
		List<PettyCashModel> response=pettyCashService.findAllPettyCashTransactionsBySearch(refNo,fromDate,
				toDate,party,counterParty,startOfRecords,pageLimit);
		return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}	
	
	//Asha
		@PostMapping("/get/pettyCashTransactionsBySearchCount")
		public ResponseEntity<BaseDto<Integer>> getAllPettyCashTransactionsBySearchCount(
				@RequestParam("refNo") String refNo,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
				@RequestParam("party") String party,@RequestParam("counterParty") String counterParty){
			
			Integer response=pettyCashService.findAllPettyCashTransactionsBySearchCount(refNo,fromDate,toDate,party,counterParty);
			return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
		}	
	
	
	//Asha
	@PostMapping("update/pettyCashTransactionsWithPrevAmt")
	public ResponseEntity<BaseDto<HashMap<String, ChartOfAccountsModel>>> updateChartOfAccountWithPrevAmt(
			@RequestParam("party") String party,@RequestParam("counterParty") String counterParty,
			@RequestParam("amount") String amount,@RequestParam("selectedParty") String selectedParty,
			@RequestParam("selectedCounterParty") String selectedCounterParty){
		
		HashMap<String,ChartOfAccountsModel> coaList=pettyCashService.updateChartOfAccountBalFromPettyScreen(party,counterParty,amount,selectedParty,selectedCounterParty);
		
		return new BaseDto<>(coaList, coaHelper.getRetrieveChartOfAccountsMessage(), OK).respond();
	
	}
	
	@GetMapping("/getall/pettyCashTxns/bypagination")
	public ResponseEntity<BaseDto<List<PettyCashModel>>> getAllPettyCashByPagination(@RequestParam Integer pageNumber,@RequestParam Integer limit){
		List<PettyCashModel> response=pettyCashService.getAllPettyCashTxnByPagination(pageNumber,limit);
		return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	
	@GetMapping("/getall/pettycashdetailscount")
	public ResponseEntity<BaseDto<Integer>> getAllPettyCashCount(){
		Integer response=pettyCashService.getAllPettyCashCount();
		return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	
	
}
