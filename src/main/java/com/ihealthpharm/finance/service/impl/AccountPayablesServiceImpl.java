package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountPayablesRepository;
import com.ihealthpharm.finance.helper.AccountPayablesHelper;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.service.AccountPayablesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountPayablesServiceImpl implements AccountPayablesService{

	@Autowired
	AccountPayablesRepository accountPayablesRepository;
	
	@Autowired
	AccountPayablesHelper accountPayablesHelper;
	
	@Override
	public AccountPayablesModel saveAccountPayablesData(AccountPayablesModel accountPayables) {
		AccountPayablesModel accountPayablesRes = accountPayablesRepository.save(accountPayables);
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " Saved succesfully");
		return accountPayablesRes;
	}

	@Override
	public AccountPayablesModel updateAccountPayablesData(AccountPayablesModel accountPayables) {
		AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayables.getAccountPayablesId());
		if (!Objects.nonNull(accountPayablesRes)) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}

		accountPayablesRes = accountPayablesRepository.save(accountPayables);
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " updated succesfully");
		return accountPayablesRes;
	}

	@Override
	public List<AccountPayablesModel> updateAccountsPayablesData(List<AccountPayablesModel> accountsPayables) {
		for (AccountPayablesModel accountPayables : accountsPayables) {
			AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayables.getAccountPayablesId());
			if (!Objects.nonNull(accountPayablesRes)) {
				throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
			}

			accountPayablesRes = accountPayablesRepository.save(accountPayables);
			log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " updated succesfully");
		}
		return accountsPayables;
	}
	@Override
	public List<AccountPayablesModel> findAllAccountsPayables() {
		
		return accountPayablesRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountPayablesModel findAccountPayablesById(int accountPayablesId) {
		AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayablesId);
		if (!Objects.nonNull(accountPayablesRes)) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " retrieved succesfully");
		return accountPayablesRes;
	}

	

	
	@Override
	public void deleteAccountPayablesById(int accountPayablesId) {
		AccountPayablesModel accountPayablesRes = accountPayablesRepository.getOne(accountPayablesId);
		if (!Objects.nonNull(accountPayablesRes)) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " Deleted succesfully");
		accountPayablesRepository.delete(accountPayablesRes);
		
	}

	@Override
	public void deleteAccountsPayablesById(int[] accountPayablesIds) {
		AccountPayablesModel accountPayablesRes;
		for (int accountPayables : accountPayablesIds) {
			accountPayablesRes = getValidAccountsPayables(accountPayables);
			if (!Objects.nonNull(accountPayablesRes)) {
				throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
			}
			accountPayablesRepository.delete(accountPayablesRes);
			log.info("AccountPayables data with ID: " + accountPayablesRes.getAccountPayablesId() + " deleted succesfully");
		}
		
	}
	
	public AccountPayablesModel getValidAccountsPayables(int accountPayablesId) {
		AccountPayablesModel accountPayablesRes = null;

		try {
			accountPayablesRes =accountPayablesRepository.findById(accountPayablesId).get();
			return accountPayablesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
