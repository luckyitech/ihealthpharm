package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.InsuranceRepository;
import com.ihealthpharm.masters.helper.InsuranceHelper;
import com.ihealthpharm.masters.model.InsuranceModel;
import com.ihealthpharm.masters.service.InsuranceService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class InsuranceServiceImpl implements InsuranceService  {


	@Autowired
	private InsuranceRepository insuranceRepo;

	@Autowired
	private InsuranceHelper insuranceHelper;


	@Override
	public InsuranceModel saveInsurance( InsuranceModel insuranceModel) {
		InsuranceModel insuranceRes = insuranceRepo.save(insuranceModel);
		log.info("Insurance data with ID: " + insuranceRes.getInsurancePolicyId() + " saved succesfully");
		return insuranceRes;
	}

	@Override
	public List<InsuranceModel> findAllByInsurances() {
		return insuranceRepo.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public InsuranceModel updateInsuranceData(@Valid InsuranceModel insuranceModel) {
		InsuranceModel insuranceRes = getValidInsurance(insuranceModel.getInsurancePolicyId());

		if (!Objects.nonNull(insuranceRes)) {
			throw new IHealthPharmException(insuranceHelper.getNotFoundInsuranceMessage(), HttpStatus.NOT_FOUND);
		}
		insuranceRes = insuranceRepo.save(insuranceModel);
		log.info("Insurance data with ID : " + insuranceRes.getInsurancePolicyId() + " updated succesfully");

		return insuranceRes;
	}

	@Override
	public List<InsuranceModel> updateMultipleInsurances(@Valid List<InsuranceModel> insuranceModels) {
		insuranceModels = insuranceRepo.saveAll(insuranceModels);

		log.info("Multiple Insurance data  updated succesfully");

		return insuranceModels;

	}

	@Override
	public InsuranceModel findInsuranceById(int insurancePolicyId) {

		InsuranceModel insuranceModelRes = getValidInsurance(insurancePolicyId);
		if (!Objects.nonNull(insuranceModelRes)) {
			throw new IHealthPharmException(insuranceHelper.getNotFoundInsuranceMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Insurance data with ID : " + insuranceModelRes.getInsurancePolicyId() + " retrieved succesfully");
		return insuranceModelRes;

	}

	@Override
	public void delete(int insurancePolicyId) {
		InsuranceModel insuranceRes = getValidInsurance(insurancePolicyId);
		if (!Objects.nonNull(insuranceRes)) {
			throw new IHealthPharmException(insuranceHelper.getNotFoundInsuranceMessage(), HttpStatus.NOT_FOUND);
		}
		insuranceRepo.delete(insuranceRes);
		log.info("Insurance data with ID: " + insuranceRes.getInsurancePolicyId() + " deleted succesfully");

	}

	@Override
	public void deleteMultipleInsurances(int[] insurancePolicyId) {
		InsuranceModel insuranceRes;
		for (int insurance : insurancePolicyId) {
			insuranceRes = getValidInsurance(insurance);
			if (!Objects.nonNull(insuranceRes)) {
				throw new IHealthPharmException(insuranceHelper.getNotFoundInsuranceMessage(), HttpStatus.NOT_FOUND);
			}
			insuranceRepo.delete(insuranceRes);
			log.info("Insurance data with ID: " + insuranceRes.getInsurancePolicyId() + " deleted succesfully");
		}		
	}



	private InsuranceModel getValidInsurance(int insurancePolicyId) {
		InsuranceModel insuranceRes = null;
		try {
			insuranceRes = insuranceRepo.findById(insurancePolicyId).get();
			return insuranceRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(insuranceHelper.getNotFoundInsuranceMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
