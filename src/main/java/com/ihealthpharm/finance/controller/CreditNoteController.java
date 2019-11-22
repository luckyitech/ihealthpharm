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
		System.out.println(creditNoteModel.toString());
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
	public ResponseEntity<BaseDto<Object>> deleteCreditNote(@RequestParam int creditNoteId){
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
	public ResponseEntity<BaseDto<CreditNoteModel>> getCreditNoteById(@Valid @RequestParam int creditNoteId){
		System.out.println(creditNoteId);
		CreditNoteModel creditNoteRes= creditNoteService.findCreditById(creditNoteId);
		System.out.println(creditNoteRes);
		return new BaseDto<>(creditNoteRes,creditNoteHelper.getRetriveCreditNoteMessage(),OK).respond();
	}
	}
