package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountPayablesInvoicesRepository;
import com.ihealthpharm.finance.helper.AccountPayablesInvoicesHelper;
import com.ihealthpharm.finance.model.AccountPayablesInvoicesModel;
import com.ihealthpharm.finance.service.AccountPayablesInvoicesService;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class AccountPayablesInvoicesServiceImpl implements AccountPayablesInvoicesService {

	@Autowired
	AccountPayablesInvoicesRepository accountPayablesInvoicesRepository;
	
	@Autowired
	AccountPayablesInvoicesHelper accountPayablesInvoicesHelper;
	
	@Override
	public AccountPayablesInvoicesModel saveAccountPayablesInvoicesData(AccountPayablesInvoicesModel accountPayablesInvoices) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes = accountPayablesInvoicesRepository.save(accountPayablesInvoices);
		log.info("AccountPayablesInvoices data with ID : " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " Saved succesfully");
		return accountPayablesInvoicesRes;
	}

	@Override
	public AccountPayablesInvoicesModel updateAccountPayablesInvoicesData(AccountPayablesInvoicesModel accountPayablesInvoices) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes = getValidAccountsPayablesInvoices(accountPayablesInvoices.getAccountPayablesInvoicesId());
		if (!Objects.nonNull(accountPayablesInvoicesRes)) {
			throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
		}

		accountPayablesInvoicesRes = accountPayablesInvoicesRepository.save(accountPayablesInvoices);
		log.info("AccountPayablesInvoices data with ID : " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " updated succesfully");
		return accountPayablesInvoicesRes;
	}

	@Override
	public List<AccountPayablesInvoicesModel> updateAccountsPayablesInvoicesData(List<AccountPayablesInvoicesModel> accountsPayablesInvoices) {
		for (AccountPayablesInvoicesModel accountPayablesInvoices : accountsPayablesInvoices) {
			AccountPayablesInvoicesModel accountPayablesInvoicesRes = getValidAccountsPayablesInvoices(accountPayablesInvoices.getAccountPayablesInvoicesId());
			if (!Objects.nonNull(accountPayablesInvoicesRes)) {
				throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
			}

			accountPayablesInvoicesRes = accountPayablesInvoicesRepository.save(accountPayablesInvoices);
			log.info("AccountPayablesInvoices data with ID : " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " updated succesfully");
		}
		return accountsPayablesInvoices;
	}

	@Override
	public List<AccountPayablesInvoicesModel> findAllAccountsPayablesInvoices() {
		
		return accountPayablesInvoicesRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountPayablesInvoicesModel findAccountPayablesInvoicesById(Integer accountPayablesInvoicesId) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes = getValidAccountsPayablesInvoices(accountPayablesInvoicesId);
		if (!Objects.nonNull(accountPayablesInvoicesRes)) {
			throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayablesInvoices data with ID : " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " retrieved succesfully");
		return accountPayablesInvoicesRes;
	}

	

	
	@Override
	public void deleteAccountPayablesInvoicesById(Integer accountPayablesInvoicesId) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes = accountPayablesInvoicesRepository.getOne(accountPayablesInvoicesId);
		if (!Objects.nonNull(accountPayablesInvoicesRes)) {
			throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayablesInvoices data with ID : " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " Deleted succesfully");
		accountPayablesInvoicesRepository.delete(accountPayablesInvoicesRes);
		
	}

	@Override
	public void deleteAccountsPayablesInvoicesById(Integer[] accountPayablesInvoicesIds) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes;
		for (int accountPayablesInvoices : accountPayablesInvoicesIds) {
			accountPayablesInvoicesRes = getValidAccountsPayablesInvoices(accountPayablesInvoices);
			if (!Objects.nonNull(accountPayablesInvoicesRes)) {
				throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
			}
			accountPayablesInvoicesRepository.delete(accountPayablesInvoicesRes);
			log.info("AccountPayablesInvoices data with ID: " + accountPayablesInvoicesRes.getAccountPayablesInvoicesId() + " deleted succesfully");
		}
		
	}
	
	public AccountPayablesInvoicesModel getValidAccountsPayablesInvoices(Integer accountPayablesInvoicesId) {
		AccountPayablesInvoicesModel accountPayablesInvoicesRes = null;

		try {
			accountPayablesInvoicesRes =accountPayablesInvoicesRepository.findById(accountPayablesInvoicesId).get();
			return accountPayablesInvoicesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountPayablesInvoicesHelper.getNotFoundAccountPayablesInvoicesMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
