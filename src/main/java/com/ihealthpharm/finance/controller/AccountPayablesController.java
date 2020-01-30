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
import com.ihealthpharm.finance.helper.AccountPayablesHelper;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.service.AccountPayablesService;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.stock.helper.InvoiceHelper;
import com.ihealthpharm.stock.model.InvoiceModel;

@RestController
@CrossOrigin
public class AccountPayablesController {

	@Autowired
	AccountPayablesService accountPayablesService;

	@Autowired
	AccountPayablesHelper accountPayablesHelper;
	
	@Autowired
	InvoiceHelper invoiceHelper;
	
	@Autowired
	SalesHelper salesHelper;

	@PostMapping("/save/accountPayables")
	public ResponseEntity<BaseDto<AccountPayablesModel>> insertAccountPayablesData(@Valid @RequestBody AccountPayablesModel accountPayablesModel) {
		AccountPayablesModel accountPayablesModelRes = accountPayablesService.saveAccountPayablesData(accountPayablesModel);
		return new BaseDto<>(accountPayablesModelRes, accountPayablesHelper.getSaveAccountPayablesMessage(), OK).respond();
	}

	@PutMapping("/update/accountPayables")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> updateAccountPayablesData(@RequestBody List<AccountPayablesModel> accountPayablesModels) {
		List<AccountPayablesModel> accountPayablesModelRes = accountPayablesService.updateAccountPayablesData(accountPayablesModels);
		return new BaseDto<>(accountPayablesModelRes, accountPayablesHelper.getUpdateAccountPayablesMessage(), OK).respond();
	}
	
	@PutMapping("/update/accountsPayables")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> updateAccountsPayablesData(@Valid @RequestBody List<AccountPayablesModel> accountPayablesModel) {
		List<AccountPayablesModel> AccountPayablesModelRes = accountPayablesService.updateAccountsPayablesData(accountPayablesModel);
		return new BaseDto<>(AccountPayablesModelRes, accountPayablesHelper.getUpdateAccountPayablesMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/accountPayables")
	public ResponseEntity<BaseDto<Object>> deleteAccountPayablesData(@RequestParam Integer accountPayablesId) {
		accountPayablesService.deleteAccountPayablesById(accountPayablesId);
		return new BaseDto<>(accountPayablesHelper.getDeleteAccountPayablesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/accountsPayables")
	public ResponseEntity<BaseDto<Object>> deleteAccountPayablesData(@RequestParam Integer[] accountPayablesIds) {

		accountPayablesService.deleteAccountsPayablesById(accountPayablesIds);
		return new BaseDto<>(accountPayablesHelper.getDeleteAccountPayablesMessage(), OK).respond();
	}

	
	@GetMapping("/getaccountPayablesdata")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAccountPayablesData() {
		List<AccountPayablesModel> result = accountPayablesService.findAllAccountsPayables();
		return new BaseDto<>(result, accountPayablesHelper.getRetrieveAccountPayablesMessage(), OK).respond();
	}

	@GetMapping("/getaccountPayablesdatabyid")
	public ResponseEntity<BaseDto<AccountPayablesModel>> getAccountPayablesDataById(@RequestParam Integer accountPayablesId) {
		AccountPayablesModel result = accountPayablesService.findAccountPayablesById(accountPayablesId);
		return new BaseDto<>(result, accountPayablesHelper.getRetrieveAccountPayablesMessage(), OK).respond();
	}

	@GetMapping("/getinvoicesbysupplierid")
	public ResponseEntity<BaseDto<List<InvoiceModel>>> getAllInvoicesBasedOnSupplierId(@RequestParam Integer supplierId){
		List<InvoiceModel> result=accountPayablesService.getAllInvoicesBySupplierId(supplierId);

		return new BaseDto<>(result,invoiceHelper.getRetrieveInvoiceMessage(),OK).respond();
	}
	
	@GetMapping("/getinvoicebasedon/InvoiceNumber")
	public ResponseEntity<BaseDto<List<InvoiceModel>>> getInvoiceBasedOnInvoiceNumber(@RequestParam String invoiceNo){
		List<InvoiceModel> response=accountPayablesService.getAllInvoicesBySearch(invoiceNo);
		return new BaseDto<>(response,invoiceHelper.getRetrieveInvoiceMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/accountpayables")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAllPayables(){
		List<AccountPayablesModel> response=accountPayablesService.getAllAccountPayables();
		return new BaseDto<>(response,accountPayablesHelper.getRetrieveAccountPayablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/Accountpayables/basedonCustomer")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAllAccountPayables(@RequestParam String customerName){
		List<AccountPayablesModel> response=accountPayablesService.getAllCustomersBasedOnName(customerName);
		return new BaseDto<>(response,accountPayablesHelper.getRetrieveAccountPayablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/AccountPayables/basedonSupplier")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAllAccountPayablesBasedOnSupplier(@RequestParam String supplierName){
		List<AccountPayablesModel> response=accountPayablesService.getAllSuppliersBasedonSupplierName(supplierName);
		return new BaseDto<>(response,accountPayablesHelper.getRetrieveAccountPayablesMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/AccountPayables/basedonsuppliers")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAllAccountPayablesForSuppliers(){
		List<AccountPayablesModel> response=accountPayablesService.getAllSuppliersForAccountPayables();
		return new BaseDto<>(response,accountPayablesHelper.getRetrieveAccountPayablesMessage(),OK).respond();
		
	}
	
	
	@GetMapping("/getAll/suppliersby/nameSearch")
	public ResponseEntity<BaseDto<List<AccountPayablesModel>>> getAllPayablesBasedOnSupplierSearch(@RequestParam String supplierName){
		List<AccountPayablesModel> res=accountPayablesService.getAllPayablesBasedOnSuppliers(supplierName);
		return new BaseDto<>(res,accountPayablesHelper.getRetrieveAccountPayablesMessage(),OK).respond();
	}
	
	
	@GetMapping("/getPendingCount")
	public ResponseEntity<BaseDto<Integer>> getPendingDetals() {
		Integer response = accountPayablesService.getCountOfPending();
		return new BaseDto<>(response, accountPayablesHelper.getRetrieveAccountPayablesMessage(), OK).respond();
	}
	
	@GetMapping("/getPartallyPaidCount")
	public ResponseEntity<BaseDto<Integer>> getPartiallyPaid(){
		Integer response = accountPayablesService.getCountPartiallyPaid();
		return new BaseDto<>(response, accountPayablesHelper.getRetrieveAccountPayablesMessage(), OK).respond();
	}
	@GetMapping("/getPaidCount")
	public ResponseEntity<BaseDto<Integer>> getPaidCount(){
		Integer response = accountPayablesService.getCountPaid();
		return new BaseDto<>(response, accountPayablesHelper.getRetrieveAccountPayablesMessage(), OK).respond();
	}	
	
	@GetMapping("/getallpaymentnosinap")
	public ResponseEntity<BaseDto<List<String>>> getAllPaymentNosInAP(){
		List<String> results=accountPayablesService.findAllPaymentNosINAP();
		return new BaseDto<>(results,invoiceHelper.getRetrieveInvoiceMessage(),OK).respond();
	}
	@GetMapping("/getpaymentnosinapbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPaymentNosInAP(@RequestParam String PNO){
		List<String> results=accountPayablesService.findPaymentNoINAP(PNO);
		return new BaseDto<>(results,invoiceHelper.getRetrieveInvoiceMessage(),OK).respond();
	}
}
