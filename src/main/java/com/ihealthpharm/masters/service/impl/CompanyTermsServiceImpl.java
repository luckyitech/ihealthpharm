package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.CompanyTermsRepository;
import com.ihealthpharm.masters.helper.CompanyTermsAndConditionsHelper;
import com.ihealthpharm.masters.model.CompanyTermsModel;
import com.ihealthpharm.masters.service.CompanyTermsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CompanyTermsServiceImpl implements  CompanyTermsService {

	
	@Autowired
	CompanyTermsRepository companyTermsRepository;
	
	@Autowired
	CompanyTermsAndConditionsHelper companyTermsHelper;
	
	
	
	@Override
	public CompanyTermsModel saveCompanyTermsData(CompanyTermsModel companyTermsModel) {
		companyTermsModel = companyTermsRepository.save(companyTermsModel);
		log.info("CompanyTerms data with ID: "+ companyTermsModel.getCompanyTermsId()+" saved succesfully");
		return companyTermsModel;
	}

	@Override
	public CompanyTermsModel updateCompanyTermsData(CompanyTermsModel companyTermsModel) {

		CompanyTermsModel companyTermsRes = getValidCompanyTerms(companyTermsModel.getCompanyTermsId());
		if(!Objects.nonNull(companyTermsRes))
		{
			throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(),HttpStatus.NOT_FOUND);
		}

		companyTermsRes = companyTermsRepository.save(companyTermsModel);
		log.info("CompanyTerms data with ID : "+ companyTermsRes.getCompanyTermsId()+" updated succesfully");
		return companyTermsRes;
		
	}

	@Override
	public List<CompanyTermsModel> findAllCompanyTerms() {
		
		return companyTermsRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public CompanyTermsModel findCompanyTermsById(int companyTermsId) {
		CompanyTermsModel companyTermsRes = getValidCompanyTerms(companyTermsId);

		if(!Objects.nonNull(companyTermsRes))
		{
			throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("CompanyTerms data with ID : "+ companyTermsRes.getCompanyTermsId()+" retrieved succesfully");
		return companyTermsRes;

	}

	@Override
	public void deleteCompanyTermsById(int companyTermsId) {

		CompanyTermsModel companyTermsRes = getValidCompanyTerms(companyTermsId);
		if(!Objects.nonNull(companyTermsRes))
		{
			throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(),HttpStatus.NOT_FOUND);
		}		
		companyTermsRepository.delete(companyTermsRes);
		log.info("CompanyTermsAndConditions data with ID: "+ companyTermsRes.getCompanyTermsId()+" deleted succesfully");
	}

	private CompanyTermsModel getValidCompanyTerms(int companyTermsId)
	{
		CompanyTermsModel CompanyTermsRes = null;
		try {
			CompanyTermsRes =  companyTermsRepository.findById(companyTermsId).get();
			return CompanyTermsRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(),HttpStatus.NOT_FOUND);
		}


	}

	@Override
	public List<CompanyTermsModel> updateCompanyTermsData(List<CompanyTermsModel> companyTermsModels) {


		for (CompanyTermsModel terms : companyTermsModels) {
			CompanyTermsModel companyTermsRes = getValidCompanyTerms(terms.getCompanyTermsId());
			if (!Objects.nonNull(companyTermsRes)) {
				throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(), HttpStatus.NOT_FOUND);
			}

			companyTermsRes = companyTermsRepository.save(terms);
			log.info("CompanyTerms data with Multiple IDs : " + companyTermsRes.getCompanyTermsId() + " updated succesfully");
		}
		
		
		return companyTermsModels;
	}

	@Override
	public void deleteCompanyTermsDataByTds(int[] companyTermsIds) {

		CompanyTermsModel companyTermsRes;
		for (int companyTerms : companyTermsIds) {
			companyTermsRes = getValidCompanyTerms(companyTerms);
			if (!Objects.nonNull(companyTermsRes)) {
				throw new IHealthPharmException(companyTermsHelper.getNotFoundComapanyTermsMessage(), HttpStatus.NOT_FOUND);
			}
			companyTermsRepository.delete(companyTermsRes);
			log.info("CompanyTerms data with ID: " + companyTermsRes.getCompanyTermsId() + " deleted succesfully");
		}
		
	}

	

}
