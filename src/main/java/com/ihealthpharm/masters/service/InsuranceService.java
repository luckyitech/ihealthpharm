package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.InsuranceModel;

public interface InsuranceService {

	InsuranceModel saveInsurance(@Valid InsuranceModel insuranceModel);

	List<InsuranceModel> findAllByInsurances();

	InsuranceModel updateInsuranceData(@Valid InsuranceModel insuranceModel);

	List<InsuranceModel> updateMultipleInsurances(@Valid List<InsuranceModel> insuranceModels);
	
	InsuranceModel findInsuranceById(Integer insurancePolicyId);
	
	InsuranceModel findInsuranceByPolicyCode(String policyCode);
	
	List<InsuranceModel> findInsuranceByPolicyCodeOrPolicyDescription(String searchTerm);
	
	void delete(Integer insurancePolicyId);

	void deleteMultipleInsurances(Integer[] insurancePolicyId);
	
}
