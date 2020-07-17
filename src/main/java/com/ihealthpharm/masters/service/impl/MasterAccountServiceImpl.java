package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.MasterAccountRepository;
import com.ihealthpharm.masters.helper.MasterAccountHelper;
import com.ihealthpharm.masters.model.MasterAccountModel;
import com.ihealthpharm.masters.model.MembershipModel;
import com.ihealthpharm.masters.service.MasterAccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MasterAccountServiceImpl implements MasterAccountService {

	@Autowired
	MasterAccountRepository masterAccountRepository;
	
	@Autowired
	MasterAccountHelper masterAccountHelper;
	
	@Override
	public MasterAccountModel save(MasterAccountModel masterAccount) {
		
		return masterAccountRepository.save(masterAccount);
	}

	@Override
	public MasterAccountModel update(MasterAccountModel masterAccount) {
		
		MasterAccountModel masterAccountRes = getValidMembership(masterAccount.getMasterAccountId());

		if (!Objects.nonNull(masterAccountRes)) {
			throw new IHealthPharmException(masterAccountHelper.getNotFoundMasterAccountMessage(), HttpStatus.NOT_FOUND);
		}
		masterAccountRes = masterAccountRepository.save(masterAccount);
		log.info("Membership data with ID : " + masterAccountHelper.getRetrieveMasterAccountMessage() + " updated succesfully");
		
		return masterAccountRes;
	}

	@Override
	public void delete(MasterAccountModel masterAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public MasterAccountModel findById(Integer masterAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MasterAccountModel> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private MasterAccountModel getValidMembership(Integer masterAccountId) {
		MasterAccountModel masterAccountRes = null;
		try {
			masterAccountRes = masterAccountRepository.findById(masterAccountId).get();
			return masterAccountRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(masterAccountHelper.getNotFoundMasterAccountMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
