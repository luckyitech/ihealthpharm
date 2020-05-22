package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.ModeHelper;
import com.ihealthpharm.finance.model.ModeModel;
import com.ihealthpharm.finance.service.ExpensesService;
import com.ihealthpharm.finance.service.ModeService;

@RestController
@CrossOrigin
public class ModeController {
	@Autowired
	private ModeService modeService;
	
	@Autowired
	private ModeHelper modeHelper;
	
	@GetMapping("/getAllModes")
	public ResponseEntity<BaseDto<List<ModeModel>>> getAllModes(){
		List<ModeModel> expensesRes = modeService.getAllModes();
		return new BaseDto<>(expensesRes,modeHelper.getRetrieveModeMessage(),OK).respond();
	}
	
	@GetMapping("/getAllModes/expenses")
	public ResponseEntity<BaseDto<List<ModeModel>>> getAllModesExpenses(){
		List<ModeModel> expensesRes = modeService.getAllModesForExpences();
		return new BaseDto<>(expensesRes,modeHelper.getRetrieveModeMessage(),OK).respond();
	}
	
	@GetMapping("/getAllModesOnCredit")
	public ResponseEntity<BaseDto<List<ModeModel>>> getAllModesOnCredit(){
		List<ModeModel> expensesRes = modeService.getAllModesOnCredit();
		return new BaseDto<>(expensesRes,modeHelper.getRetrieveModeMessage(),OK).respond();
	}
	
	@GetMapping("/getAllModesOnDebit")
	public ResponseEntity<BaseDto<List<ModeModel>>> getAllModesOnDebit(){
		List<ModeModel> expensesRes = modeService.getAllModesOnDebit();
		return new BaseDto<>(expensesRes,modeHelper.getRetrieveModeMessage(),OK).respond();
	}
	@GetMapping("/getAllModesOnDebitAndCredit")
	public ResponseEntity<BaseDto<List<ModeModel>>> getAllModesOnDebitAndCredit(){
		List<ModeModel> expensesRes = modeService.getAllModesOnCreditAndDebit();
		return new BaseDto<>(expensesRes,modeHelper.getRetrieveModeMessage(),OK).respond();
	}
}
