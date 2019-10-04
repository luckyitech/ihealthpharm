package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CompanyTermsModel;

public interface CompanyTermsService {


	CompanyTermsModel saveCompanyTermsData(CompanyTermsModel companyTermsModel);

	CompanyTermsModel updateCompanyTermsData(CompanyTermsModel companyTermsModel);

	List<CompanyTermsModel> updateCompanyTermsData(List<CompanyTermsModel> companyTermsModels);

	List<CompanyTermsModel> findAllCompanyTerms();

	CompanyTermsModel findCompanyTermsById(int companyTermsId);

	void deleteCompanyTermsById( int companyTermsId);


	void deleteCompanyTermsDataByTds(int[] companyTermsIds);

}
