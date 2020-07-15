package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.BillTypeHelper;
import com.ihealthpharm.finance.helper.CreditNoteHelper;
import com.ihealthpharm.finance.model.BillTypeModel;
import com.ihealthpharm.finance.service.BillTypesService;

@RestController
@CrossOrigin
public class BillTypesController {

	@Autowired
	BillTypesService billTypesService;
	
	@Autowired
	private BillTypeHelper billTypeHelper;
	
	@GetMapping("/getallBillTypes")
	public List<BillTypeModel> getAllBillTypes(){
		return billTypesService.getAllBillTypes();
	}
	
	@GetMapping("/getTypes")
	public List<String> getAllCNBillTypes(){
		return billTypesService.getCNByBillType();
	}
	
//	@GetMapping("/getTypes")
//	public ResponseEntity<BaseDto<List<String>>> getAllCNBillTypes(){
//		List<String> results=billTypesService.getCNByBillType();
//		return new BaseDto<>(results,billTypeHelper.getRetriveBillTypeMessage(),OK).respond();
//	}
	

}
