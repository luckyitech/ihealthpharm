package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.MasterAccountModel;

public interface MasterAccountService {

	public MasterAccountModel save(MasterAccountModel masterAccount);
	
	public MasterAccountModel update(MasterAccountModel masterAccount);
	
	public void delete(MasterAccountModel masterAccount);
	
	public MasterAccountModel findById(Integer masterAccountId);
	
	public List<MasterAccountModel> findByAll();
}
