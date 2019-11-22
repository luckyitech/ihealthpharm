package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PharmacyBranchRepository;
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.service.PharmacyBranchService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PharmacyBranchServiceImpl implements PharmacyBranchService {
	
	@Autowired
	private PharmacyBranchRepository PharmaBranchRepo;
	
	@Autowired
	private PharmacyHelper pharmacyHelper;
	
	@Override
	public PharmacyBranchModel savePharmacyBranch(PharmacyBranchModel pharmacyBranchModel) {

           pharmacyBranchModel=PharmaBranchRepo.save(pharmacyBranchModel);
		
		log.info("PharmacyBranch data with ID : "+pharmacyBranchModel.getPharmacyBranchId()+"saved successfully");
		
		return pharmacyBranchModel;
		
	}
	
	
	private PharmacyBranchModel getValidPharmacyBranch(@PathVariable int pharmacyBranchId){
		
		PharmacyBranchModel branchRes = null;
		try {
			branchRes =  PharmaBranchRepo.findById(pharmacyBranchId).get();
			return branchRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	

	@Override
	public PharmacyBranchModel updateBranch(@Valid PharmacyBranchModel pharmacyBranchModel) {

		
		PharmacyBranchModel branchModelRes = getValidPharmacyBranch(pharmacyBranchModel.getPharmacyBranchId());

		if(!Objects.nonNull(branchModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(),HttpStatus.NOT_FOUND);
		}

		branchModelRes =PharmaBranchRepo.save(pharmacyBranchModel);
		log.info("PharmacyBranch data with ID : "+ branchModelRes.getPharmacyBranchId()+" updated succesfully");
		return branchModelRes;
		
	}

	
	@Override
	public List<PharmacyBranchModel> updateMultipleBranches(List<PharmacyBranchModel> pharmacyBranchModels) {
		
		for (PharmacyBranchModel branch : pharmacyBranchModels) {
			PharmacyBranchModel branchRes =getValidPharmacyBranch(branch.getPharmacyBranchId());
			if (!Objects.nonNull(branchRes)) {
				throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(), HttpStatus.NOT_FOUND);
			}

			branchRes =PharmaBranchRepo.save(branch);
			log.info("PharmacyBranch data with Multiple IDs : " +branchRes.getPharmacyBranchId() + " updated succesfully");
		}
		
		return pharmacyBranchModels;
	}

	@Override
	public void deletePharmacyBranch(Integer pharmacyBranchId) {
		
		PharmacyBranchModel branchModelRes =getValidPharmacyBranch(pharmacyBranchId) ;
		if(!Objects.nonNull(branchModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(),HttpStatus.NOT_FOUND);
		}
		PharmaBranchRepo.delete(branchModelRes);
		log.info("PharmacyBranch data with ID: "+ branchModelRes.getPharmacyBranchId()+" deleted succesfully");
		
	}



	@Override
	public PharmacyBranchModel findByPharmacyId(Integer pharmacyId) {
		
		PharmacyBranchModel branchModelRes = getValidPharmacyBranch(pharmacyId);

		if(!Objects.nonNull(branchModelRes))
		{
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("PharmacyBranch data with ID : "+ branchModelRes.getPharmacyBranchId()+" retrieved succesfully");
		return branchModelRes;

	}


	@Override
	public List<PharmacyBranchModel> findAllBranches() {
		return PharmaBranchRepo.findAllByOrderByCreationTimeStampDesc();
	}
	
	
}
