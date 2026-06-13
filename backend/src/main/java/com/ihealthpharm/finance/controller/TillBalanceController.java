package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.TillBalanceHelper;
import com.ihealthpharm.finance.model.TillBalanceModel;
import com.ihealthpharm.finance.service.TillBalanceService;

@RestController
@CrossOrigin
public class TillBalanceController {
	
	@Autowired
	private TillBalanceService tillBalService;
	
	@Autowired
	private TillBalanceHelper tillBalhelper;
	
	@PostMapping("/save/tillbalancedata")
	public ResponseEntity<BaseDto<TillBalanceModel>> insertTillBalance(@Valid @RequestBody TillBalanceModel tillBalModel) {
		TillBalanceModel tillBalRes = tillBalService.saveTillBalanceDetails(tillBalModel);
		return new BaseDto<>(tillBalRes,tillBalhelper.getSavetillBalanceMessage(),OK).respond();
	}
	
	
	@GetMapping("/getAll/tillCustomerNames/bySearch")
	public ResponseEntity<BaseDto<List<String>>> getAllTillCustomerNamesBySearch(@RequestParam String customer) {
		List<String> tillBalRes = tillBalService.findAllCustomerNamesBySearch(customer);
		return new BaseDto<>(tillBalRes,tillBalhelper.getRetrivetillBalanceMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/tillCustomerNames")
	public ResponseEntity<BaseDto<List<String>>> getAllTillCustomerNames() {
		List<String> tillBalRes = tillBalService.findTillCustomerNames();
		return new BaseDto<>(tillBalRes,tillBalhelper.getRetrivetillBalanceMessage(),OK).respond();
	}

	
	@GetMapping("/getAll/tillAccountNames/bySearch")
	public ResponseEntity<BaseDto<List<String>>> getAllTillAccountsBySearch(@RequestParam String tillAccount) {
		List<String> tillBalRes = tillBalService.findAllTillAccountsBySearch(tillAccount);
		return new BaseDto<>(tillBalRes,tillBalhelper.getRetrivetillBalanceMessage(),OK).respond();
	}
	
	@GetMapping("/getAll/tillAccountNames")
	public ResponseEntity<BaseDto<List<String>>> getAllTillAccounts() {
		List<String> tillBalRes = tillBalService.findTillAccounts();
		return new BaseDto<>(tillBalRes,tillBalhelper.getRetrivetillBalanceMessage(),OK).respond();
	}
}
