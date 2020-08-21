package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.FamilyAccountRepository;
import com.ihealthpharm.masters.dao.MasterAccountRepository;
import com.ihealthpharm.masters.dto.MasterAccDTO;
import com.ihealthpharm.masters.helper.MasterAccountHelper;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.FamilyAccountModel;
import com.ihealthpharm.masters.model.MasterAccountModel;
import com.ihealthpharm.masters.service.MasterAccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MasterAccountServiceImpl implements MasterAccountService {

	@Autowired
	MasterAccountRepository masterAccountRepository;
	
	@Autowired
	FamilyAccountRepository familyAccountRepository;
	
	@Autowired
	MasterAccountHelper masterAccountHelper;
	
	@Override
	public MasterAccountModel save(MasterAccountModel masterAccount) {
		if (!customerIdCheck(masterAccount.getCustomerId().getCustomerId())) {
			throw new IHealthPharmException("Customer Don't Have ID", HttpStatus.BAD_REQUEST);
		}
		return masterAccountRepository.save(masterAccount);
	}

	@Override
	public MasterAccountModel update(MasterAccountModel masterAccount) {
		
		MasterAccountModel masterAccountRes = getValidMembership(masterAccount.getMasterAccountId());

		if (!Objects.nonNull(masterAccountRes)) {
			throw new IHealthPharmException(masterAccountHelper.getNotFoundMasterAccountMessage(), HttpStatus.NOT_FOUND);
		}
		
		if (!customerIdCheck(masterAccountRes.getCustomerId().getCustomerId())) {
			throw new IHealthPharmException("Customer Don't Have ID", HttpStatus.BAD_REQUEST);
		}
		for(FamilyAccountModel familyAccountModel:masterAccountRes.getFamilyAccounts()) {
			familyAccountRepository.delete(familyAccountModel);
		}
		masterAccount.setEntryType("Master Update");
		masterAccountRes = masterAccountRepository.save(masterAccount);
		log.info("Membership data with ID : " + masterAccountHelper.getRetrieveMasterAccountMessage() + " updated succesfully");
		
		return masterAccountRes;
	}

	@Override
	public void delete(Integer masterAccountId) {
		MasterAccountModel masterAccount = new MasterAccountModel();
		masterAccount.setMasterAccountId(masterAccountId);
		masterAccountRepository.delete(masterAccount);

	}

	@Override
	public MasterAccountModel findById(Integer masterAccountId) {
		MasterAccountModel masterAccountRes = getValidMembership(masterAccountId);

		if (!Objects.nonNull(masterAccountRes)) {
			throw new IHealthPharmException(masterAccountHelper.getNotFoundMasterAccountMessage(), HttpStatus.NOT_FOUND);
		}
		
		return masterAccountRes;
	}

	@Override
	public List<MasterAccountModel> findByAll() {
		
		return masterAccountRepository.findAll();
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

	@Override
	public List<CustomerModel> getCustomersList() {
		 Pageable limit=PageRequest.of(0, 100);
		return masterAccountRepository.findCustomersNotInMastersAndFamily(limit);
	}

	@Override
	public List<CustomerModel> getCustomersListbyName(String name) {
		Pageable limit= PageRequest.of(0, 100);
		return masterAccountRepository.findCustomersByNameNotInMastersAndFamily(name,limit);
	}

	@Override
	public MasterAccountModel getMasterByCustomer(Integer customerId) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerId(customerId);
		MasterAccountModel masterAccountRes = masterAccountRepository.findMasterAccountByCustomer(customerModel);
		
		if (!Objects.nonNull(masterAccountRes)) {
			masterAccountRes = masterAccountRepository.findMasterInFamilyAccountByCustomer(customerModel);
		}
		
		return masterAccountRes;
	}

	@Override
	public Integer updateMasterAccountByAccountId(Integer masterAccountId, Integer creditLimitLeft,Integer lastUpdatedUser,String entryType,String salesBillNo) {
		
		return masterAccountRepository.updateMasterAccountByAccountId(masterAccountId,creditLimitLeft,lastUpdatedUser,entryType,salesBillNo);
	}

	@Override
	public List<MasterAccDTO> getMastersForRecievables( Integer start, Integer end) {
		Pageable limit = PageRequest.of(start, end);
		return masterAccountRepository.getAccounts(limit);
	}

	@Override
	public List<MasterAccDTO> getMastersForRecievablesBySearch(String creditNumber) {
		return masterAccountRepository.getAllMastersBySearch(creditNumber);
	}

	@Override
	public List<String> getMastersAccountNoBySearch(String creditNo) {
		
		return masterAccountRepository.getMasterAccountNoBySearch(creditNo);
	}

	@Override
	public List<String> getAllMastersAccountNo() {
		
		return masterAccountRepository.getAllMasterAccountNo();
	}

	@Override
	public List<String> getMastersAccountCustomerBySearch(String name) {
		
		return masterAccountRepository.getMastersAccountCustomers(name);
	}

	@Override
	public List<String> getFamilyAccountCustomerBySearch(String name) {
		// TODO Auto-generated method stub
		return masterAccountRepository.getFamilyAccountCustomers(name);
	}

	@Override
	public List<String> getAccByCreditNumber() {
		// TODO Auto-generated method stub
		return masterAccountRepository.getAccByCreditNo();
	}
	
	public Boolean customerIdCheck(Integer customerId)
	{
		Integer res = masterAccountRepository.checkCustomerHaveId(customerId);
		if(Objects.nonNull(res))
		{
			return true;
		}
		else {
			return false;
		}
	}
}
