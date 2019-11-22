package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.RolesRepository;
import com.ihealthpharm.masters.helper.RolesHelper;
import com.ihealthpharm.masters.model.RolesModel;
import com.ihealthpharm.masters.service.RolesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	RolesHelper rolesHelper;
	
	@Override
	public RolesModel saveRolesData(RolesModel role) {
		RolesModel rolesRes = rolesRepository.save(role);
		log.info("Roles data with ID : " + rolesRes.getRoleId() + " Saved succesfully");
		return rolesRes;
	}

	@Override
	public RolesModel updateRolesData(RolesModel role) {
		RolesModel rolesRes = getValidUsers(role.getRoleId());
		if (!Objects.nonNull(rolesRes)) {
			throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
		}

		rolesRes = rolesRepository.save(role);
		log.info("Roles data with ID : " + rolesRes.getRoleId() + " updated succesfully");
		return rolesRes;
	}

	@Override
	public List<RolesModel> updateRolesData(List<RolesModel> roles) {
		for (RolesModel role : roles) {
			RolesModel rolesRes = getValidUsers(role.getRoleId());
			if (!Objects.nonNull(rolesRes)) {
				throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
			}

			rolesRes = rolesRepository.save(role);
			log.info("Roles data with ID : " + rolesRes.getRoleId() + " updated succesfully");
		}
		return roles;
	}

	@Override
	public List<RolesModel> findAllRoles() {
		
		return rolesRepository.findAll();
	}

	@Override
	public RolesModel findRoleById(Integer roleId) {
		RolesModel rolesRes = getValidUsers(roleId);
		if (!Objects.nonNull(rolesRes)) {
			throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
		}

		log.info("Roles data with ID : " + rolesRes.getRoleId() + " retrived succesfully");
		return rolesRes;
	}

	@Override
	public void deleteRolesById(Integer roleIds) {
		RolesModel rolesRes = getValidUsers(roleIds);
		if (!Objects.nonNull(rolesRes)) {
			throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Roles data with ID : " + rolesRes.getRoleId() + " Deleted succesfully");
		rolesRepository.delete(rolesRes);

	}

	@Override
	public void deleteRolesById(Integer[] roleIds) {
		RolesModel rolesRes;
		for (int role : roleIds) {
			rolesRes = getValidUsers(role);
			if (!Objects.nonNull(rolesRes)) {
				throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
			}
			
			log.info("Roles data with ID : " + rolesRes.getRoleId() + " Deleted succesfully");
			rolesRepository.delete(rolesRes);
		}

	}
	
	public RolesModel getValidUsers(Integer roleId) {
		RolesModel rolesRes = null;

		try {
			rolesRes = rolesRepository.findById(roleId).get();
			return rolesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(rolesHelper.getNotFoundRolesMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
