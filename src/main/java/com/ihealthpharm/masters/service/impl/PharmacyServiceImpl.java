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
import com.ihealthpharm.masters.dao.PharmacyBranchRepository;
import com.ihealthpharm.masters.dao.PharmacyRepository;
import com.ihealthpharm.masters.dao.PharmacyStockRepository;
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.PharmacyStockModel;
import com.ihealthpharm.masters.service.PharmacyService;

import lombok.extern.slf4j.Slf4j;



/**
 * @author Vikas
 *PharmacyServiceImpl is implementation of PharmacyService. 
 *PharmacyService is used for Pharmacy, Branch, and stock please write the code in the respected blocks
 */
@Service
@Transactional
@Slf4j
public class PharmacyServiceImpl implements PharmacyService {
	
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private PharmacyBranchRepository pharmacyBranchRepository;
	
	@Autowired
	private PharmacyStockRepository pharmacyStockRepository;
	
	@Autowired
	PharmacyHelper pharmacyHelper;
	

	@Override
	public PharmacyModel savePharmacyData(PharmacyModel pharmacyModel) {

		pharmacyModel=pharmacyRepository.save(pharmacyModel);
		
		log.info("Pharmacy data with ID : "+pharmacyModel.getPharmacyId()+"saved successfully");
		
		return pharmacyModel;
	}

	@Override
	public PharmacyModel updatePharmacyData(PharmacyModel pharmacyModel) {

		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyModel.getPharmacyId());
		
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
		}
		
		pharmacyModelRes=pharmacyRepository.save(pharmacyModel);
		log.info("Pharmacy data with ID :"+pharmacyModelRes.getPharmacyId()+"updated successfully");
		
		return pharmacyModelRes;
	}

	@Override
	public List<PharmacyModel> findPharmacyByActive() {

		return pharmacyRepository.findByActiveS("Y");
	}

	@Override
	public PharmacyModel findPharmacyById(int pharmacyId) {

		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyId);
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(),HttpStatus.NOT_FOUND);
		}
		
		log.info("Pharmacy data with ID:"+pharmacyModelRes.getPharmacyId()+"retrieved successfully");
		
		return pharmacyModelRes;
	}

	@Override
	public void deletePharmacyById(int pharmacyId) {
	
		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyId);
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.notFoundPharmacyMessage, HttpStatus.NOT_FOUND);
		}
		pharmacyRepository.delete(pharmacyModelRes);
		log.info("Pharmacy data with ID  :"+pharmacyModelRes.getPharmacyId()+"deleted successfully");
	}
	
	
	private PharmacyModel getValidPharmacy(int pharmacyId){
		PharmacyModel pharmacyRes=null;
		
		try{
			pharmacyRes=pharmacyRepository.findById(pharmacyId).get();
			
			return pharmacyRes;
			
		}catch (NoSuchElementException noSuchElementException) {
           throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PharmacyBranchModel saveAddBranch(PharmacyBranchModel pharmacyBranchModel) {
		PharmacyBranchModel branchModel = pharmacyBranchRepository.save(pharmacyBranchModel);
		return branchModel;
	}
	
	
	
	private PharmacyBranchModel getPharmacyBranch(int branchModelID){
		try{
			PharmacyBranchModel branchModel=pharmacyBranchRepository.findByPharmacyBranchId(branchModelID);
			return branchModel;
		}catch (NoSuchElementException noSuchElementException) {
           throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PharmacyBranchModel updateBranch(@Valid PharmacyBranchModel pharmacyBranchModel) {

		PharmacyBranchModel branchModel = getPharmacyBranch(pharmacyBranchModel.getPharmacyBranchId());
		if(!Objects.nonNull(branchModel)){
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyBranchMessage(), HttpStatus.NOT_FOUND);
		}
		pharmacyBranchRepository.save(branchModel);
		return pharmacyBranchModel;
	}

	@Override
	public List<PharmacyBranchModel>  updateMultipleBranches(List<PharmacyBranchModel> pharmacyBranchModel) {
		for (PharmacyBranchModel pharmacyBranchModel2 : pharmacyBranchModel) {
			pharmacyBranchModel2 = updateBranch(pharmacyBranchModel2);
		}
		
		return pharmacyBranchModel;
	}

	@Override
	public void delete(int pharmacyBranchId) {
		PharmacyBranchModel branchModel = getPharmacyBranch(pharmacyBranchId);
		if(Objects.nonNull(branchModel)){
			pharmacyBranchRepository.delete(branchModel);
		}
	}

	@Override
	public List<PharmacyBranchModel> findByPharmacyId(int pharmacyId) {
		PharmacyModel pharmacyModel = getValidPharmacy(pharmacyId);
		if(Objects.nonNull(pharmacyModel)) {
			List<PharmacyBranchModel> branchModelList = pharmacyBranchRepository.findByPharmacy(pharmacyModel);
			return branchModelList;
		}else {
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
//	Stock  Services
	@Override
	public PharmacyStockModel addStock(PharmacyStockModel pharmacyStockModel) {
		pharmacyStockModel = pharmacyStockRepository.save(pharmacyStockModel);
		return pharmacyStockModel;
	}

	@Override
	public PharmacyStockModel updateStock(@Valid PharmacyStockModel pharmacyStockModel) {
		PharmacyStockModel existingStock = findStockById(pharmacyStockModel.getStockPointId());
		if(Objects.nonNull(existingStock)) {
			pharmacyStockModel = pharmacyStockRepository.save(pharmacyStockModel);
			return pharmacyStockModel;
		}else {
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public PharmacyStockModel findStockById(int stockPointId) {
		try{
			PharmacyStockModel existingStock = pharmacyStockRepository.findByStockPointId(stockPointId);
			return existingStock;
		}catch (NoSuchElementException noSuchElementException) {
           throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<PharmacyStockModel> updateStocks(@Valid List<PharmacyStockModel> pharmacyStockModels) {
		for (PharmacyStockModel pharmacyStockModel : pharmacyStockModels) {
			pharmacyStockModel = updateStock(pharmacyStockModel);
		}
		return pharmacyStockModels;
	}

	@Override
	public void deleteStock(int stockId) {
		PharmacyStockModel existingStock = findStockById(stockId);
		if(Objects.nonNull(existingStock)) {
			pharmacyStockRepository.delete(existingStock);
		}else {
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<PharmacyStockModel> findByBranchId(int branchId) {
		PharmacyBranchModel pharmacyBranchModel  = getPharmacyBranch(branchId); 
		if(Objects.nonNull(pharmacyBranchModel)) {
			List<PharmacyStockModel> pharmacyStockModels = pharmacyStockRepository.findByPharmacyBranch(pharmacyBranchModel);
			return pharmacyStockModels; 
		}else {
			throw new IHealthPharmException(pharmacyHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

}
