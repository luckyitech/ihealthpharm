package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.FamilyAccountRepository;
import com.ihealthpharm.masters.helper.FamilyAccountHelper;
import com.ihealthpharm.masters.model.FamilyAccountModel;
import com.ihealthpharm.masters.service.FamilyAccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FamilyAccountServiceImpl implements FamilyAccountService{

	@Autowired
	private FamilyAccountRepository familyAccountRepository;
	
	@Autowired
	FamilyAccountHelper familyAccountHelper;
	
	@Override
	public FamilyAccountModel save(FamilyAccountModel familyAccount) {
		
		return familyAccountRepository.save(familyAccount);
	}

	@Override
	public FamilyAccountModel update(FamilyAccountModel familyAccount) {
		
		FamilyAccountModel familyAccountRes = getValidMembership(familyAccount.getFamilyAccountId());

		if (!Objects.nonNull(familyAccountRes)) {
			throw new IHealthPharmException(familyAccountHelper.getNotFoundFamilyAccountMessage(), HttpStatus.NOT_FOUND);
		}
		familyAccountRes = familyAccountRepository.save(familyAccount);
		log.info("Membership data with ID : " + familyAccountHelper.getRetrieveFamilyAccountMessage() + " updated succesfully");
		
		return familyAccountRes;
	}

	@Override
	public void delete(FamilyAccountModel familyAccount) {
		
		familyAccountRepository.delete(familyAccount);
		
	}

	@Override
	public FamilyAccountModel findById(Integer familyAccountId) {
		FamilyAccountModel familyAccountRes = getValidMembership(familyAccountId);

		if (!Objects.nonNull(familyAccountRes)) {
			throw new IHealthPharmException(familyAccountHelper.getNotFoundFamilyAccountMessage(), HttpStatus.NOT_FOUND);
		}
		return familyAccountRes;
	}

	@Override
	public List<FamilyAccountModel> findByAll() {
		
		return familyAccountRepository.findAll();
	}
	
	private FamilyAccountModel getValidMembership(Integer familyAccountId) {
		FamilyAccountModel familyAccountRes = null;
		try {
			familyAccountRes = familyAccountRepository.findById(familyAccountId).get();
			return familyAccountRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(familyAccountHelper.getNotFoundFamilyAccountMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
