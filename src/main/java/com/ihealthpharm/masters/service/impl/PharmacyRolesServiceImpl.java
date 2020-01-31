package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PharmacyRolesRepository;
import com.ihealthpharm.masters.model.PharmacyRolesModel;
import com.ihealthpharm.masters.service.PharmacyRolesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PharmacyRolesServiceImpl implements PharmacyRolesService {
    
    

    @Autowired
    PharmacyRolesRepository pharmacyRolesRepository;

	@Override
	public void deletePharmacyRolesData(Integer pharmacyRolesId) {
		
		PharmacyRolesModel pharmacyRolesRes = getValidPharmacyRoles(pharmacyRolesId);
		if (!Objects.nonNull(pharmacyRolesRes)) {
			throw new IHealthPharmException("Pharmacy Roles not Found",
					HttpStatus.NOT_FOUND);
		}
		pharmacyRolesRepository.delete(pharmacyRolesRes);
		log.info(pharmacyRolesRes.getRoleId()+" deleted successfully");
	}

	@Override
	public PharmacyRolesModel findPharmacyRolesDataById(Integer pharmacyRolesId) {
		PharmacyRolesModel pharmacyRolesRes = getValidPharmacyRoles(pharmacyRolesId);
		if (!Objects.nonNull(pharmacyRolesRes)) {
			throw new IHealthPharmException("Pharmacy Roles not Found",
					HttpStatus.NOT_FOUND);
		}
		return pharmacyRolesRes;
	}

	@Override
	public List<PharmacyRolesModel> findAllPharmacyRolesData() {
		return pharmacyRolesRepository.findAll();
	}

	@Override
	public PharmacyRolesModel savePharmacyRolesData(PharmacyRolesModel pharmacyRolesModel) {
		pharmacyRolesModel = pharmacyRolesRepository.save(pharmacyRolesModel);
		return pharmacyRolesModel;
	}

	@Override
	public PharmacyRolesModel updatePharmacyRolesData(PharmacyRolesModel pharmacyRolesModel) {
		PharmacyRolesModel pharmacyRolesRes = getValidPharmacyRoles(pharmacyRolesModel.getRoleId());
		if (!Objects.nonNull(pharmacyRolesRes)) {
			throw new IHealthPharmException("Pharmacy Roles not Found",
					HttpStatus.NOT_FOUND);
		}
		pharmacyRolesRes = pharmacyRolesRepository.save(pharmacyRolesModel);
		return pharmacyRolesRes;
	}
    
	public PharmacyRolesModel getValidPharmacyRoles(Integer pharmacyRolesId) {
		PharmacyRolesModel PharmacyRolesRes = null;

		try {
			PharmacyRolesRes = pharmacyRolesRepository.findById(pharmacyRolesId).get();
			return PharmacyRolesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException("PharmacyRoles not foulnd",
					HttpStatus.NOT_FOUND);
		}
	}
    
}