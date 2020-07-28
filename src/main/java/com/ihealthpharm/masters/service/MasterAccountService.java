package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.MasterAccountModel;

public interface MasterAccountService {

	public MasterAccountModel save(MasterAccountModel masterAccount);
	
	public MasterAccountModel update(MasterAccountModel masterAccount);
	
	public void delete(Integer masterAccountId);
	
	public MasterAccountModel findById(Integer masterAccountId);
	
	public List<MasterAccountModel> findByAll();
	
	public List<CustomerModel> getCustomersList();
	
	public List<CustomerModel> getCustomersListbyName(String name);
	
	public MasterAccountModel getMasterByCustomer(Integer customerId);
	
	public Integer updateMasterAccountByAccountId(Integer masterAccountId,Integer creditLimitLeft);
}
