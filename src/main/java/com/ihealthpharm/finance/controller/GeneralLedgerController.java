package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.GeneralLedgerHelper;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.model.GeneralLedgerModel;
import com.ihealthpharm.finance.service.GeneralLedgerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class GeneralLedgerController {

	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private GeneralLedgerHelper generalLedgerHelper;
	
	@PostMapping("/save/generalledger")
	public ResponseEntity<BaseDto<GeneralLedgerModel>> saveGeneralLedger(@RequestBody GeneralLedgerModel generaLedgerModel){
		log.info("Request Object to insert is :"+generaLedgerModel);
		
		GeneralLedgerModel response=generalLedgerService.saveGeneraledger(generaLedgerModel);
		System.out.println(response);
		return new BaseDto<>(response,generalLedgerHelper.getSaveGeneralLedgerMessage(),OK).respond();
	}
	
	@PostMapping("/save/multipleAccountPayables/generalledgers")
	public ResponseEntity<BaseDto<List<GeneralLedgerModel>>> saveMultipleLedgersSaving(@RequestBody List<AccountPayablesModel> generalLedgerModels){
		System.out.println(generalLedgerModels);
		List<GeneralLedgerModel> modelRes = generalLedgerService.saveMutipleLedgersData(generalLedgerModels);
		return new BaseDto<>(modelRes, generalLedgerHelper.getSaveGeneralLedgerMessage(), OK).respond();
	}
	
	@PostMapping("/save/multipleAccountRecievabes/generalledgers")
	public ResponseEntity<BaseDto<List<GeneralLedgerModel>>> saveMultipleLedgersForAccRecievables(@RequestBody List<AccountReceivablesModel> generalLedgerModels){
		System.out.println(generalLedgerModels);
		List<GeneralLedgerModel> modelRes=generalLedgerService.saveMultipleLedgersDataForAccRecievables(generalLedgerModels);
		return new BaseDto<>(modelRes, generalLedgerHelper.getSaveGeneralLedgerMessage(), OK).respond();
	}
  	
	
	
	
}
