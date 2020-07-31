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
import com.ihealthpharm.finance.dto.AccRecievablesAccountsDTO;
import com.ihealthpharm.finance.dto.AccRecievablesCustomerDTO;
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
		System.out.println("In accout recievables updating");
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

	@GetMapping("/getsalesbasedon/salesNumber")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAllSalesBasedonSalesNumber(@RequestParam String billCode,@RequestParam String customerName){
		List<AccountReceivablesModel> result=accountReceivablesService.getAllByBillCodeSearch(billCode,customerName);
		return new BaseDto<>(result,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();

	}

	@GetMapping("/getAll/accountrecievables")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAll(){
		List<AccountReceivablesModel> response=accountReceivablesService.getAllAccountPayables();
		return new BaseDto<>(response,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/accountrecievables/byDTO")
	public ResponseEntity<BaseDto<List<AccRecievablesCustomerDTO>>> getAllAccRecievables(){
		List<AccRecievablesCustomerDTO> response=accountReceivablesService.getAllAccountPayablesData();
		return new BaseDto<>(response,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}


	@GetMapping("/getAll/accountrecievables/basedon/customername")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAllBasedOnCustomerName(@RequestParam String customerName){
		List<AccountReceivablesModel> result=accountReceivablesService.getAllCustomersBasedonCustomerName(customerName);
		return new BaseDto<>(result,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	//Account Receivables 
	@GetMapping("/getcustnamesbysearchAR")
	public ResponseEntity<BaseDto<List<String>>> findCustonersNamesBySearchAR(@RequestParam String searchTerm){
		List<String> results=accountReceivablesService.findCustNamesbysearchAR(searchTerm);
		return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}

	@GetMapping("/getallcustnamesAR")
	public ResponseEntity<BaseDto<List<String>>> findAllCustomersNamesAR(){
		List<String> results=accountReceivablesService.findallCustNamesAR();
		return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}

	//Credit Note
	@GetMapping("/getreceiptnobysearchAR")
	public ResponseEntity<BaseDto<List<String>>> findReceiptNoBySearchAR(@RequestParam String searchTerm){
		List<String> results=accountReceivablesService.findReceiptNobysearchAR(searchTerm);
		return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}

	@GetMapping("/getallreceiptnoAR")
	public ResponseEntity<BaseDto<List<String>>> findAllReceiptNoAR(){
		List<String> results=accountReceivablesService.findallReceiptNoAR();
		return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}

	@GetMapping("/getAccountRecievables/customername/search")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAllRecievablesCustNamesSearch(@RequestParam String customerName){
		List<AccountReceivablesModel> response=accountReceivablesService.getAllRecievablesCustomerNameSearch(customerName);
		return new BaseDto<>(response,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}

	// account recievables popup search
	
	@GetMapping("/getAccRecievables/forPopupSearch")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAllAccRecievablessBasedOnSearches(
		@RequestParam String paymentStatus, @RequestParam String paymentStartDate, @RequestParam String paymentEndDate,@RequestParam String SourceRef,
				@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String customerName){
		System.out.println(SourceRef);
		 System.out.println(paymentStartDate +"-------------------------------------  "+paymentEndDate);
		 
		 System.out.println("===================================================================");
		 System.out.println(paymentStartDate +"-------------------------------------  "+paymentEndDate);
		 System.out.println(paymentStatus+" 000 0 "+SourceRef);
			List<AccountReceivablesModel> results=accountReceivablesService.searchInAccRecievables(paymentStatus,paymentStartDate,paymentEndDate,SourceRef,pageNumber,pageSize,customerName);
			return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAccRecievables/forPopupSearchCount")
	public ResponseEntity<BaseDto<Integer>> getAllAccRecievablesBasedOnSearchesForCount(
		@RequestParam String paymentStatus, @RequestParam String paymentStartDate, @RequestParam String paymentEndDate,@RequestParam String SourceRef,
				@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String customerName){
		System.out.println("===================================================================");
		 System.out.println(paymentStartDate +"-------------------------------------  "+paymentEndDate);
		 System.out.println(paymentStatus+" 000 0 "+SourceRef);
		 Integer results=accountReceivablesService.searchInAccRecievablesForCount(paymentStatus,paymentStartDate,paymentEndDate,SourceRef,pageNumber,pageSize,customerName);
			return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
	//@GetMapping
	// account recievables popup search for accounts
	
	@GetMapping("/getAccRecievables/forPopupSearch/forAccounts")
	public ResponseEntity<BaseDto<List<AccountReceivablesModel>>> getAllAccRecievablessBasedOnSearchesForAccounts(
		@RequestParam String paymentStatus, @RequestParam String paymentStartDate, @RequestParam String paymentEndDate,@RequestParam String SourceRef,
				@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String creditNumber){
		System.out.println(SourceRef);
		 System.out.println(paymentStartDate +"-------------------------------------  "+paymentEndDate);
		 System.out.println(creditNumber);
			List<AccountReceivablesModel> results=accountReceivablesService.searchInAccRecievablesForAccounts(paymentStatus,paymentStartDate,paymentEndDate,SourceRef,pageNumber,pageSize,creditNumber);
			return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAccRecievables/forPopupSearchCount/forAccounts")
	public ResponseEntity<BaseDto<Integer>> getAllAccRecievablesBasedOnSearchesForCountForAccounts(
		@RequestParam String paymentStatus, @RequestParam String paymentStartDate, @RequestParam String paymentEndDate,@RequestParam String SourceRef,
				@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String creditNumber){
		 Integer results=accountReceivablesService.searchInAccRecievablesForCountForAccounts(paymentStatus,paymentStartDate,paymentEndDate,SourceRef,pageNumber,pageSize,creditNumber);
			return new BaseDto<>(results,accountReceivablesHelper.getRetrieveAccountReceivablesMessage(),OK).respond();
	}
	
}
