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
import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.helper.CreditNoteHelper;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.service.CreditNoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CreditNoteController {

	@Autowired
	private CreditNoteService creditNoteService;

	@Autowired
	private CreditNoteHelper creditNoteHelper;

	@PostMapping("/save/creditNote")
	public ResponseEntity<BaseDto<CreditNoteModel>> saveCreditNote(@Valid @RequestBody  CreditNoteModel creditNoteModel ){
		log.info("Request Object to insert is :"+creditNoteModel);
		CreditNoteModel creditNote= creditNoteService.saveCreditData(creditNoteModel);
		System.out.println(creditNote);
		return new BaseDto<>(creditNote,creditNoteHelper.getSaveCreditNoteMessage(),OK).respond();
	}
	@PutMapping("/update/creditNote")
	public ResponseEntity<BaseDto<CreditNoteModel>> updatecreditNoteData(@Valid @RequestBody CreditNoteModel creditNoteModel){
		log.info("Request Object for update is :"+creditNoteModel);
		CreditNoteModel creditNoteRes=creditNoteService.updateCreditData(creditNoteModel);
		return new BaseDto<>(creditNoteRes,creditNoteHelper.getUpdateCreditNoteMessage(),OK).respond();
	}
	@PutMapping("/update/allCreditNotes")
	public ResponseEntity<BaseDto<List<CreditNoteModel>>> updatecreditNotes(@Valid @RequestBody List<CreditNoteModel> creditNoteModels ){
		log.info("Request Update For Multiples :"+creditNoteModels);
		List<CreditNoteModel> creditNotes=creditNoteService.updateListOfCreditData(creditNoteModels);
		return new BaseDto<>(creditNotes,creditNoteHelper.getUpdateCreditNoteMessage(),OK).respond();
	}
	@DeleteMapping("/delete/creditNote")
	public ResponseEntity<BaseDto<Object>> deleteCreditNote(@RequestParam Integer creditNoteId){
		log.info("Request Object for delete :"+creditNoteId);
		creditNoteService.deleteCreditById(creditNoteId);
		return new BaseDto<>(creditNoteHelper.getDeleteCreditNoteMessage(),OK).respond();
	}


	@GetMapping("/getCreditNote")
	public ResponseEntity<BaseDto<List<CreditNoteModel>>> getAllCreditNotes(){
		List<CreditNoteModel> response=creditNoteService.findAllCredit();

		return new BaseDto<>(response,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}

	@GetMapping("/getCreditNote/byid")
	public ResponseEntity<BaseDto<CreditNoteModel>> getCreditNoteById(@Valid @RequestParam Integer creditNoteId){
		CreditNoteModel creditNoteRes= creditNoteService.findCreditById(creditNoteId);
		return new BaseDto<>(creditNoteRes,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}

	@GetMapping("/getcustomersfrom/creditnotes")
	public ResponseEntity<BaseDto<List<CreditCustomerDTO>>> getCustomers(){
		List<CreditCustomerDTO> response=creditNoteService.getAllCustomersFromCreditNotes();
		return new BaseDto<>(response,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
	//Credit Note
	@GetMapping("/getcreditnotenobysearchCN")
	public ResponseEntity<BaseDto<List<String>>> findCreditNoteNoBySearchCN(@RequestParam String searchTerm){
		List<String> results=creditNoteService.findCreditNoteNobysearchCN(searchTerm);
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}

	@GetMapping("/getallcreditnotenoCN")
	public ResponseEntity<BaseDto<List<String>>> findAllCreditNoteNoCN(){
		List<String> results=creditNoteService.findallCreditNoteNoCN();
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}

	//Getting customers for credit note
	@GetMapping("/creditnote/getcustomers")
	public ResponseEntity<BaseDto<List<String>>> getAllCustomers(){
		List<String> results=creditNoteService.findAllCustomers();
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
	@GetMapping("/creditnote/getcustomersbysearch")
	public ResponseEntity<BaseDto<List<String>>> getCustomersBySearch(@RequestParam String customer){
		List<String> results=creditNoteService.findAllCustomersBySearch(customer);
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
//	@GetMapping("/getCreditNoteByBillTypes")
//	public List<String> getAllCNByBillTypes(@RequestParam String searchTerm){
//	return creditNoteService.getCreditNoteByBillType(searchTerm);
//	}
	
	@GetMapping("/getCreditNoteByBillTypes")
	public ResponseEntity<BaseDto<List<String>>> getAllCNByBillTypes(@RequestParam String searchTerm){
		List<String> results=creditNoteService.getCreditNoteByBillType(searchTerm);
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
	
	@GetMapping("/getAllCreditNotes")
	public ResponseEntity<BaseDto<List<CreditNoteModel>>> getAllCreditNotesForHistory(){
		List<CreditNoteModel> results=creditNoteService.getAllCreditNotes();
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
	@GetMapping("/getAllCreditNotes/bySearch")
	public ResponseEntity<BaseDto<List<CreditNoteModel>>> getAllBySearch(@RequestParam String searchTerm,@RequestParam String searchValue){
		List<CreditNoteModel> response=creditNoteService.getAllCreditNotesBySearch(searchTerm,searchValue);
		return new BaseDto<>(response,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
	@GetMapping("/getAllCreditNotePaymentStatus")
	public ResponseEntity<BaseDto<List<String>>> getAllCreditNotesPaymentStatus(){
		List<String> results=creditNoteService.getAllCreditNotesPaymentStatus();
		return new BaseDto<>(results,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	
 
}
