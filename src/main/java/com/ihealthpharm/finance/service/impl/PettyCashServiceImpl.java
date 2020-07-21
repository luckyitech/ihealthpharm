package com.ihealthpharm.finance.service.impl;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.PettyCashRepository;
import com.ihealthpharm.finance.helper.PettyCashHelper;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.PettyCashService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PettyCashServiceImpl implements PettyCashService{

	@Autowired
	PettyCashRepository pettyCashRepo;

	@Autowired
	PettyCashHelper pettyCashHelper;

	@Override
	public List<PettyCashModel> findAllPettyCash() {
		return pettyCashRepo.findAll();
	}

	@Override
	public PettyCashModel savepettyCashData(PettyCashModel pettyCashDetails) {
		PettyCashModel pettyCashRes = pettyCashRepo.save(pettyCashDetails);
		return pettyCashRes;
	}

	@Override
	public PettyCashModel findPettyCashById(Integer pettyCashId) {
		PettyCashModel pettyCashRes = getValidPettyCash(pettyCashId);

		if(!Objects.nonNull(pettyCashRes)) {
			throw new IHealthPharmException(pettyCashHelper.getNotFoundpettyCashMessage(),HttpStatus.NOT_FOUND);
		}
		return pettyCashRes;
	}

	private PettyCashModel getValidPettyCash(Integer pettyCashId) {

		PettyCashModel pettyCashRes= null;
		try {
			pettyCashRes = pettyCashRepo.findById(pettyCashId).get();
			return pettyCashRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(pettyCashHelper.getNotFoundpettyCashMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<String> getAllCounterPartyDetails() {
		
		return pettyCashRepo.findAllCounterPartyAccountDetails();
	}

	@Override
	public List<String> getBySearchCounterPartyDetails(String searchTerm) {
		
		return pettyCashRepo.findCounterPartyAccountDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllPartyDetails() {
		
		return pettyCashRepo.findAllPartyAccountDetails();
	}

	@Override
	public List<String> getBySearchPartyDetails(String searchTerm) {
		
		return pettyCashRepo.findPartyAccountDetailsBySearch(searchTerm);
	}
}
