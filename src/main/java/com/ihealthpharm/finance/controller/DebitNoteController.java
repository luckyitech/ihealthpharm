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
import com.ihealthpharm.finance.helper.DebitNoteHelper;
import com.ihealthpharm.finance.model.DebitNoteModel;
import com.ihealthpharm.finance.service.DebitNoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class DebitNoteController {

	@Autowired
	private DebitNoteService debitNoteService;
	
	@Autowired
	private DebitNoteHelper debitNoteHelper;
	
	@PostMapping("/save/debitNote")
	public ResponseEntity<BaseDto<DebitNoteModel>> saveDebitNote(@Valid @RequestBody  DebitNoteModel debitNoteModel ){
		log.info("Request Object to insert is :"+debitNoteModel);
		System.out.println(debitNoteModel.toString());
		DebitNoteModel debitNote= debitNoteService.saveDebitData(debitNoteModel);
		System.out.println(debitNote);
		return new BaseDto<>(debitNote,debitNoteHelper.getSaveDebitNoteMessage(),OK).respond();
	}
	@PutMapping("/update/DebitNote")
	public ResponseEntity<BaseDto<DebitNoteModel>> updateDebitNoteData(@Valid @RequestBody DebitNoteModel debitNoteModel){
		log.info("Request Object for update is :"+debitNoteModel);
		DebitNoteModel debitNoteRes=debitNoteService.updateDebitData(debitNoteModel);
		return new BaseDto<>(debitNoteRes,debitNoteHelper.getUpdateDebitNoteMessage(),OK).respond();
	}
	@PutMapping("/update/allDebitNotes")
	public ResponseEntity<BaseDto<List<DebitNoteModel>>> updateDebitNotes(@Valid @RequestBody List<DebitNoteModel> debitNoteModels ){
		log.info("Request Update For Multiples :"+debitNoteModels);
		List<DebitNoteModel> debitNotes=debitNoteService.updateMultipleDebit(debitNoteModels);
		return new BaseDto<>(debitNotes,debitNoteHelper.getUpdateDebitNoteMessage(),OK).respond();
	}
	@DeleteMapping("/delete/DebitNote")
	public ResponseEntity<BaseDto<Object>> deleteDebitNote(@RequestParam int debitNoteId){
		log.info("Request Object for delete :"+debitNoteId);
		debitNoteService.deleteDebitById(debitNoteId);
		return new BaseDto<>(debitNoteHelper.getDeleteDebitNoteMessage(),OK).respond();
	}
	
	
	@GetMapping("/getDebitNote")
	public ResponseEntity<BaseDto<List<DebitNoteModel>>> getAllDebitNotes(){
		List<DebitNoteModel> response=debitNoteService.findAllDebit();
		
		return new BaseDto<>(response,debitNoteHelper.getRetriveDebitNoteMessage(),OK).respond();
	}
	
	@GetMapping("/getDebitNote/byid")
	public ResponseEntity<BaseDto<DebitNoteModel>> getDebitNoteById(@Valid @RequestParam int DebitNoteId){
		System.out.println(DebitNoteId);
		DebitNoteModel debitNoteRes= debitNoteService.findDebitById(DebitNoteId);
		System.out.println(debitNoteRes);
		return new BaseDto<>(debitNoteRes,debitNoteHelper.getRetriveDebitNoteMessage(),OK).respond();
	}
}
