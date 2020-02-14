package com.ihealthpharm.expenses.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.expenses.dao.ExpensesRepository;
import com.ihealthpharm.expenses.model.ExpensesModel;
import com.ihealthpharm.expenses.service.ExpensesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ExpensesServiceImpl implements ExpensesService{

	@Autowired
	ExpensesRepository expensesRepo;
	
	@Override
	public ExpensesModel saveExpenses(ExpensesModel expensesModel) {
		log.info("saved expensedModel with Id : "+expensesModel.getExpensesId());
		return expensesRepo.save(expensesModel);
	}

	
}
