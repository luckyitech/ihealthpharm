package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.ChartOfAccountRepository;
import com.ihealthpharm.finance.helper.ChartOfAccountsHelper;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.ChartOfAccountsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ChartOfAccountsServiceImpl implements ChartOfAccountsService {

	@Autowired
	ChartOfAccountRepository chartAccRepo;

	@Autowired
	ChartOfAccountsHelper chartAccHelper;


	@Override
	public List<ChartOfAccountsModel> findAllAccounts() {
		return chartAccRepo.findAll();
	}


	@Override
	public ChartOfAccountsModel findchartOfAccountsById(Integer accountId) {
		ChartOfAccountsModel chartOfAccountsRes = getValidAccount(accountId);

		if(!Objects.nonNull(chartOfAccountsRes)) {
			throw new IHealthPharmException(chartAccHelper.getNotFoundChartOfAccountsMessage(),HttpStatus.NOT_FOUND);
		}
		return chartOfAccountsRes;
	}

	private ChartOfAccountsModel getValidAccount(Integer accountId) {

		ChartOfAccountsModel chartOfAccountsRes= null;
		try {
			chartOfAccountsRes = chartAccRepo.findById(accountId).get();
			return chartOfAccountsRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(chartAccHelper.getNotFoundChartOfAccountsMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public ChartOfAccountsModel saveCOAData(ChartOfAccountsModel chartOfAccountsModel) {
		ChartOfAccountsModel chartOfAccRes = chartAccRepo.save(chartOfAccountsModel);
		return chartOfAccRes;
	}

}
