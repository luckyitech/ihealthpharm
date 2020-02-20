package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}
