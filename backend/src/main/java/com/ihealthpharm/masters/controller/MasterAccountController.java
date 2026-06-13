package com.ihealthpharm.masters.controller;

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
import com.ihealthpharm.masters.dto.MasterAccDTO;
import com.ihealthpharm.masters.helper.MasterAccountHelper;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.MasterAccountModel;
import com.ihealthpharm.masters.service.MasterAccountService;


@RestController
@CrossOrigin
public class MasterAccountController {

	@Autowired
	MasterAccountService masterAccountService;
	
	@Autowired
	MasterAccountHelper masterAccountHelper;
	
	@PostMapping("/save/masteraccount")
	public ResponseEntity<BaseDto<MasterAccountModel>> saveMasterAccount(@Valid @RequestBody  MasterAccountModel masterAccount ){
		
		MasterAccountModel masterAccountRes=masterAccountService.save(masterAccount);
		return new BaseDto<>(masterAccountRes,masterAccountHelper.getSaveMasterAccountMessage(),OK).respond();
	}
	
	@PutMapping("/update/masteraccount")
	public ResponseEntity<BaseDto<MasterAccountModel>> updateMasterAccountData(@Valid @RequestBody  MasterAccountModel masterAccount){
		
		MasterAccountModel masterAccountRes=masterAccountService.update(masterAccount);
		return new BaseDto<>(masterAccountRes,masterAccountHelper.getUpdateMasterAccountMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/masteraccount")
	public ResponseEntity<BaseDto<Object>> deleteMasterAccount(@RequestParam Integer masterAccountId){
		
		masterAccountService.delete(masterAccountId);
		return new BaseDto<>(masterAccountHelper.getDeleteMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/masteraccount")
	public ResponseEntity<BaseDto<List<MasterAccountModel>>> getAllMasterAccounts(){
		List<MasterAccountModel> response=masterAccountService.findByAll();
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/masteraccountbyid")
	public ResponseEntity<BaseDto<MasterAccountModel>> getMasterAccountById(@Valid @RequestParam Integer masterAccountId){
		MasterAccountModel masterAccountRes=masterAccountService.findById(masterAccountId);
		return new BaseDto<>(masterAccountRes,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/customersnotinmasterandfamily")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getAllCustomers(){
		List<CustomerModel> response=masterAccountService.getCustomersList();
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/customersbynamenotinmasterandfamily")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getAllCustomersByName(@RequestParam String name){
		
		List<CustomerModel> response=masterAccountService.getCustomersListbyName(name);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/masterbycustomer")
	public ResponseEntity<BaseDto<MasterAccountModel>> getMasterByCustomer(@RequestParam Integer customerId){
		
		MasterAccountModel response=masterAccountService.getMasterByCustomer(customerId);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/update/masteraccountbyaccountid")
	public ResponseEntity<BaseDto<Integer>> updateMasterByAccountId(@RequestParam Integer masterAccountId, @RequestParam Integer creditLimitLeft,
			@RequestParam Integer lastUpdatedUser,@RequestParam String entryType, @RequestParam String salesBillNo){
		
		Integer response=masterAccountService.updateMasterAccountByAccountId(masterAccountId,creditLimitLeft,lastUpdatedUser,entryType,salesBillNo);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/masterAccountData/forAccRecievables")
	public ResponseEntity<BaseDto<List<MasterAccDTO>>> getMasterForAccRec(@RequestParam Integer start,@RequestParam Integer end){
		List<MasterAccDTO> response=masterAccountService.getMastersForRecievables(start, end);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	
	@GetMapping("/get/masterAccountData/forAccRecievables/bySearch")
	public ResponseEntity<BaseDto<List<MasterAccDTO>>> getMasterForAccRecBySearch(@RequestParam String creditNumber){
		List<MasterAccDTO> response=masterAccountService.getMastersForRecievablesBySearch(creditNumber);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	//Member ship report
	@GetMapping("/getAll/masterAccountNoBySearch")
	public ResponseEntity<BaseDto<List<String>>> getMasterAccountNoBySearch(@RequestParam String creditNo){
		List<String> response=masterAccountService.getMastersAccountNoBySearch(creditNo);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/masterAccountNo")
	public ResponseEntity<BaseDto<List<String>>> getAllMasterAccountNo(){
		List<String> response=masterAccountService.getAllMastersAccountNo();
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/masterAccountCustomerBySearch")
	public ResponseEntity<BaseDto<List<String>>> getMasterAccountCustomerBySearch(@RequestParam String name){
		List<String> response=masterAccountService.getMastersAccountCustomerBySearch(name);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/familyAccountCustomerBySearch")
	public ResponseEntity<BaseDto<List<String>>> getMasterAccountFamilyBySearch(@RequestParam String name){
		List<String> response=masterAccountService.getFamilyAccountCustomerBySearch(name);
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
	
	@GetMapping("/get/getAccByCreditNumb")
	public ResponseEntity<BaseDto<List<String>>> getAccByCreditNumb(){
		List<String> response=masterAccountService.getAccByCreditNumber();
		return new BaseDto<>(response,masterAccountHelper.getRetrieveMasterAccountMessage(),OK).respond();
	}
}
