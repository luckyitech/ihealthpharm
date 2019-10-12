package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeePharmacyRoleRepository;
import com.ihealthpharm.masters.dto.EmployeePharmacyRoleDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeePharmacyRoleModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.service.EmployeePharmacyRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePharmacyRoleServiceImpl implements EmployeePharmacyRoleService {

	@Autowired
	EmployeePharmacyRoleRepository employeePharmacyRoleRepository;

	@Override
	public void deleteEmployeePharmacyRoleData(Integer employeePharmacyRoleId) {
		EmployeePharmacyRoleModel employeePharmacyRoleRes = getValidEmployeePharmacyRole(employeePharmacyRoleId);
		if (!Objects.nonNull(employeePharmacyRoleRes)) {
			throw new IHealthPharmException("employeePharmacyRole Roles not Found", HttpStatus.NOT_FOUND);
		}
		employeePharmacyRoleRepository.delete(employeePharmacyRoleRes);
		log.info(employeePharmacyRoleRes.getPharmacyRolesModel() + " deleted successfully");

	}

	@Override
	public EmployeePharmacyRoleModel findEmployeePharmacyRoleDataById(Integer employeePharmacyRoleId) {
		EmployeePharmacyRoleModel employeePharmacyRoleRes = getValidEmployeePharmacyRole(employeePharmacyRoleId);
		if (!Objects.nonNull(employeePharmacyRoleRes)) {
			throw new IHealthPharmException("employeePharmacyRole Roles not Found", HttpStatus.NOT_FOUND);
		}

		log.info(employeePharmacyRoleRes.getPharmacyRolesModel() + " retrived successfully");
		return employeePharmacyRoleRes;
	}

	@Override
	public List<EmployeePharmacyRoleModel> findAllEmployeePharmacyRoleData() {

		return employeePharmacyRoleRepository.findAll();
	}

	@Override
	public EmployeePharmacyRoleModel saveEmployeePharmacyRoleData(EmployeePharmacyRoleDTO employeePharmacyRoleDTO) {
		EmployeePharmacyRoleModel employeePharmacyRoleRes = new EmployeePharmacyRoleModel();
		EmployeePharmacyRoleModel employeePharmacyRoleModel = new EmployeePharmacyRoleModel();
		for (PharmacyModel pharmacy : employeePharmacyRoleDTO.getPharmacyModel()) {
			employeePharmacyRoleModel = new EmployeePharmacyRoleModel();
			employeePharmacyRoleRes = new EmployeePharmacyRoleModel();
		employeePharmacyRoleModel.setAuditId(employeePharmacyRoleDTO.getAuditId());
		employeePharmacyRoleModel.setCreatedUser(employeePharmacyRoleDTO.getCreatedUser());
		employeePharmacyRoleModel.setCreationTimeStamp(employeePharmacyRoleDTO.getCreationTimeStamp());
		employeePharmacyRoleModel.setEmployee(employeePharmacyRoleDTO.getEmployee());
		employeePharmacyRoleModel.setEmployeePharmacyRoleId(employeePharmacyRoleDTO.getEmployeePharmacyRoleId());
		employeePharmacyRoleModel.setLastUpdateTimestamp(employeePharmacyRoleDTO.getLastUpdateTimestamp());
		employeePharmacyRoleModel.setLastUpdateUser(employeePharmacyRoleDTO.getLastUpdateUser());
		employeePharmacyRoleModel.setPharmacyRolesModel(employeePharmacyRoleDTO.getPharmacyRolesModel());

		
			employeePharmacyRoleModel.setPharmacyModel(pharmacy);
			employeePharmacyRoleRes = employeePharmacyRoleRepository.save(employeePharmacyRoleModel);
			//log.info("------------------------------------------------------");
			log.info("Employee Pharmacy Role Saved with Id:" + employeePharmacyRoleRes.getEmployeePharmacyRoleId() + " for Employee" + employeePharmacyRoleRes.getEmployee().getEmployeeId());
		}
		return employeePharmacyRoleRes;
	}

	@Override
	public EmployeePharmacyRoleModel updateEmployeePharmacyRoleData(
			EmployeePharmacyRoleModel employeePharmacyRoleModel) {
		EmployeePharmacyRoleModel employeePharmacyRoleRes = getValidEmployeePharmacyRole(
				employeePharmacyRoleModel.getEmployeePharmacyRoleId());
		if (!Objects.nonNull(employeePharmacyRoleRes)) {
			throw new IHealthPharmException("employeePharmacyRole Roles not Found", HttpStatus.NOT_FOUND);
		}
		employeePharmacyRoleRes = employeePharmacyRoleRepository.save(employeePharmacyRoleModel);
		log.info(employeePharmacyRoleRes.getPharmacyRolesModel() + " updated successfully");
		return employeePharmacyRoleRes;
	}

	public EmployeePharmacyRoleModel getValidEmployeePharmacyRole(int employeePharmacyRoleId) {
		EmployeePharmacyRoleModel employeePharmacyRoleRes = null;

		try {
			employeePharmacyRoleRes = employeePharmacyRoleRepository.findById(employeePharmacyRoleId).get();
			return employeePharmacyRoleRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException("employeePharmacyRoleRes not foulnd", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<EmployeePharmacyRoleModel> findEmployeePharmacyRoleDataByEmployeeId(EmployeeModel employee) {

		return employeePharmacyRoleRepository.findByEmployee(employee);
	}

}