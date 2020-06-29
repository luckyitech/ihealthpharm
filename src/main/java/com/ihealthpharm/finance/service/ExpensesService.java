package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.dto.expensesDTO;
import com.ihealthpharm.finance.model.ExpensesModel;

public interface ExpensesService {

	ExpensesModel saveExpenses(ExpensesModel expenseModel);

	Integer updatePettyBalance(Integer pettyCashId, Double balance);

	List<String> getAllPartiesBySearch(String searchTerm);

	List<String> getAllParties();
	
	List<expensesDTO> findAllTransactionId(String transactionId);

	List<String> getAllCounterParties();

	List<String> getAllCounterPartiesBySearch(String searchTerm);

	}
