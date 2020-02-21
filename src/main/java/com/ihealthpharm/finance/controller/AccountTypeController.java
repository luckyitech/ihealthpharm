package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.AccountTypeHelper;
import com.ihealthpharm.finance.model.AccountTypeModel;
import com.ihealthpharm.finance.service.AccountTypeService;


@RestController
@CrossOrigin
public class AccountTypeController {
	@Autowired
	private AccountTypeService accountTypeService;
	
	@Autowired
	private AccountTypeHelper accountTypeHelper;
	
	@GetMapping("/getall/accounttypes")
	public ResponseEntity<BaseDto<List<AccountTypeModel>>> getAllAccountTypes(){
		List<AccountTypeModel> response=accountTypeService.findAllAccountTypes();
		return new BaseDto<>(response, accountTypeHelper.getRetrieveAccountTypeMessage(), OK).respond();
	}
}
