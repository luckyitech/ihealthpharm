package com.ihealthpharm.finance.service;

import com.ihealthpharm.finance.model.ExpensesModel;

public interface ExpensesService {

	ExpensesModel saveExpenses(ExpensesModel expenseModel);

	Integer updatePettyBalance(Integer pettyCashId, Double balance);

	}
