package com.ihealthpharm.expenses.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.expenses.helper.ExpensesHelper;
import com.ihealthpharm.expenses.model.ExpensesModel;
import com.ihealthpharm.expenses.service.ExpensesService;

@RestController
@CrossOrigin
public class ExpensesController {
	
	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private ExpensesHelper expensesHelper;
	
	@PostMapping("save/expenses")
	public ResponseEntity<BaseDto<ExpensesModel>> saveExpenses(@RequestBody ExpensesModel expensesModel){
		ExpensesModel expensesRes = expensesService.saveExpenses(expensesModel);
		return new BaseDto<>(expensesRes,expensesHelper.getSaveExpensesMessage(),OK).respond();
	}
	
}
