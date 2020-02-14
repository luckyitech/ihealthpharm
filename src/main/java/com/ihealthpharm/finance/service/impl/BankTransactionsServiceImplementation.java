package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.BankTransactionsRepository;
import com.ihealthpharm.finance.helper.BankTransactionsHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.service.BankTransactionsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BankTransactionsServiceImplementation implements BankTransactionsService {

	@Autowired
	BankTransactionsRepository bankTransRepo;

	@Autowired
	BankTransactionsHelper bankHelper;

	public List<BankTransactionsModel> findAllBankTransactions() {
		return bankTransRepo.findAll();
	}

}
