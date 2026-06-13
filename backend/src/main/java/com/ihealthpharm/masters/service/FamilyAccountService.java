package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.FamilyAccountModel;

public interface FamilyAccountService {

	public FamilyAccountModel save(FamilyAccountModel familyAccount);
	
	public FamilyAccountModel update(FamilyAccountModel familyAccount);
	
	public void delete(FamilyAccountModel familyAccount);
	
	public FamilyAccountModel findById(Integer familyAccountId);
	
	public List<FamilyAccountModel> findByAll();
}
