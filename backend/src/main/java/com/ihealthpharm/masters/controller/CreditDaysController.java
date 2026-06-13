package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.CreditDaysHelper;
import com.ihealthpharm.masters.helper.CustomerHelper;
import com.ihealthpharm.masters.model.CreditDaysModel;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.service.CreditDaysService;
import com.ihealthpharm.masters.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CreditDaysController {
	
	
	@Autowired
	CreditDaysHelper creditDaysHelper;

	@Autowired
	CreditDaysService creditDaysService;
	
	@GetMapping("/getAllCreditDays")
	public ResponseEntity<BaseDto<List<String>>> getCreditDaysData() {
		List<String> result = creditDaysService.findAllCreditDays();
		return new BaseDto<>(result, creditDaysHelper.getRetrieveCreditDaysMessage(), OK).respond();
	}

}
