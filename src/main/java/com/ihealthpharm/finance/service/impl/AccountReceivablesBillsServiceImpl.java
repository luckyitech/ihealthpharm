package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountReceivablesBillsRepository;
import com.ihealthpharm.finance.helper.AccountReceivablesBillsHelper;
import com.ihealthpharm.finance.model.AccountReceivablesBillsModel;
import com.ihealthpharm.finance.service.AccountReceivablesBillsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountReceivablesBillsServiceImpl implements AccountReceivablesBillsService{

	@Autowired
	AccountReceivablesBillsRepository accountReceivablesBillsRepository;
	
	@Autowired
	AccountReceivablesBillsHelper accountReceivablesBillsHelper;
	
	@Override
	public AccountReceivablesBillsModel saveAccountReceivablesBillsData(AccountReceivablesBillsModel accountReceivablesBills) {
		AccountReceivablesBillsModel accountReceivablesBillsRes = accountReceivablesBillsRepository.save(accountReceivablesBills);
		log.info("AccountReceivablesBills data with ID : " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " Saved succesfully");
		return accountReceivablesBillsRes;
	}

	@Override
	public AccountReceivablesBillsModel updateAccountReceivablesBillsData(AccountReceivablesBillsModel accountReceivablesBills) {
		AccountReceivablesBillsModel accountReceivablesBillsRes = getValidAccountsReceivablesBills(accountReceivablesBills.getAccountReceivablesBillsId());
		if (!Objects.nonNull(accountReceivablesBillsRes)) {
			throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
		}

		accountReceivablesBillsRes = accountReceivablesBillsRepository.save(accountReceivablesBills);
		log.info("AccountReceivablesBills data with ID : " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " updated succesfully");
		return accountReceivablesBillsRes;
	}

	@Override
	public List<AccountReceivablesBillsModel> updateAccountsReceivablesBillsData(List<AccountReceivablesBillsModel> accountsReceivablesBills) {
		for (AccountReceivablesBillsModel accountReceivablesBills : accountsReceivablesBills) {
			AccountReceivablesBillsModel accountReceivablesBillsRes = getValidAccountsReceivablesBills(accountReceivablesBills.getAccountReceivablesBillsId());
			if (!Objects.nonNull(accountReceivablesBillsRes)) {
				throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
			}

			accountReceivablesBillsRes = accountReceivablesBillsRepository.save(accountReceivablesBills);
			log.info("AccountReceivablesBills data with ID : " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " updated succesfully");
		}
		return accountsReceivablesBills;
	}

	@Override
	public List<AccountReceivablesBillsModel> findAllAccountsReceivablesBills() {
		
		return accountReceivablesBillsRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountReceivablesBillsModel findAccountReceivablesBillsById(int accountReceivablesBillsId) {
		AccountReceivablesBillsModel accountReceivablesBillsRes = getValidAccountsReceivablesBills(accountReceivablesBillsId);
		if (!Objects.nonNull(accountReceivablesBillsRes)) {
			throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountReceivablesBills data with ID : " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " retrieved succesfully");
		return accountReceivablesBillsRes;
	}

	

	
	@Override
	public void deleteAccountReceivablesBillsById(int accountReceivablesBillsId) {
		AccountReceivablesBillsModel accountReceivablesBillsRes = accountReceivablesBillsRepository.getOne(accountReceivablesBillsId);
		if (!Objects.nonNull(accountReceivablesBillsRes)) {
			throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountReceivablesBills data with ID : " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " Deleted succesfully");
		accountReceivablesBillsRepository.delete(accountReceivablesBillsRes);
		
	}

	@Override
	public void deleteAccountsReceivablesBillsById(int[] accountReceivablesBillsIds) {
		AccountReceivablesBillsModel accountReceivablesBillsRes;
		for (int accountReceivablesBills : accountReceivablesBillsIds) {
			accountReceivablesBillsRes = getValidAccountsReceivablesBills(accountReceivablesBills);
			if (!Objects.nonNull(accountReceivablesBillsRes)) {
				throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
			}
			accountReceivablesBillsRepository.delete(accountReceivablesBillsRes);
			log.info("AccountReceivablesBills data with ID: " + accountReceivablesBillsRes.getAccountReceivablesBillsId() + " deleted succesfully");
		}
		
	}
	
	public AccountReceivablesBillsModel getValidAccountsReceivablesBills(int accountReceivablesBillsId) {
		AccountReceivablesBillsModel accountReceivablesBillsRes = null;

		try {
			accountReceivablesBillsRes =accountReceivablesBillsRepository.findById(accountReceivablesBillsId).get();
			return accountReceivablesBillsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountReceivablesBillsHelper.getNotFoundAccountReceivablesBillsMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
