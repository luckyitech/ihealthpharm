package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.ExpensesRepository;
import com.ihealthpharm.finance.dto.expensesDTO;
import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.service.ExpensesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ExpensesServiceImpl implements ExpensesService{

	@Autowired
	ExpensesRepository expensesRepo;
	
	@Override
	public ExpensesModel saveExpenses(ExpensesModel expensesModel) {
		return expensesRepo.save(expensesModel);
	}

	@Override
	public Integer updatePettyBalance(Integer pettyCashId, Double balance) {
		
		return expensesRepo.updateBalanceInPettyCash(pettyCashId,balance);
	}

	@Override
	public List<String> getAllPartiesBySearch(String searchTerm) {
	
		return expensesRepo.findPartyDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllParties() {
		
		return expensesRepo.findAllPartyDetails();
	}

	@Override
	public List<expensesDTO> findAllTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		return expensesRepo.getAllTransactionId(transactionId);
	}

	@Override
	public List<String> getAllCounterParties() {
		
		return expensesRepo.findAllCounterPartyDetails();
	}

	@Override
	public List<String> getAllCounterPartiesBySearch(String searchTerm) {
		
		return expensesRepo.findExpenseCounterPartyBySearch(searchTerm);
	}
	

	
}
