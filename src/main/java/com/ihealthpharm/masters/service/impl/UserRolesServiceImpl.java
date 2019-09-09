package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.UserRolesRepository;
import com.ihealthpharm.masters.helper.UserRolesHelper;
import com.ihealthpharm.masters.model.UserRolesModel;
import com.ihealthpharm.masters.service.UserRolesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	UserRolesRepository userRolesRepository;

	@Autowired
	UserRolesHelper userRolesHelper;

	@Override
	public UserRolesModel saveUserRolesData(UserRolesModel userRole) {
		UserRolesModel userRolesRes = userRolesRepository.save(userRole);
		log.info("User Roles data with ID : " + userRolesRes.getUserRolesId() + " Saved succesfully");
		return userRolesRes;
	}

	@Override
	public UserRolesModel updateUserRolesData(UserRolesModel userRole) {
		UserRolesModel userRolesRes = getValidUsers(userRole.getUserRolesId());
		if (!Objects.nonNull(userRolesRes)) {
			throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
		}

		userRolesRes = userRolesRepository.save(userRole);
		log.info("Users data with ID : " + userRole.getUserRolesId() + " updated succesfully");
		return userRolesRes;
	}

	@Override
	public List<UserRolesModel> updateUserRolesData(List<UserRolesModel> userRoles) {
		for (UserRolesModel userRole : userRoles) {
			UserRolesModel userRolesRes = getValidUsers(userRole.getUserRolesId());
			if (!Objects.nonNull(userRolesRes)) {
				throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
			}

			userRolesRes = userRolesRepository.save(userRole);
			log.info("User Roles data with ID : " + userRole.getUserRolesId() + " updated succesfully");

		}
		return userRoles;
	}

	@Override
	public List<UserRolesModel> findAllUserRoles() {

		return userRolesRepository.findAll();
	}

	@Override
	public UserRolesModel findUserRoleById(int userRoleId) {
		UserRolesModel userRolesRes = getValidUsers(userRoleId);
		if (!Objects.nonNull(userRolesRes)) {
			throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("User Roles data with ID : " + userRolesRes.getUserRolesId() + " retrived succesfully");
		return userRolesRes;
	}

	@Override
	public void deleteUserRolesById(int userRoleId) {
		UserRolesModel userRolesRes = getValidUsers(userRoleId);
		if (!Objects.nonNull(userRolesRes)) {
			throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
		}
		userRolesRepository.delete(userRolesRes);
		log.info("User Roles data with ID : " + userRolesRes.getUserRolesId() + " retrived succesfully");

	}

	@Override
	public void deleteUserRolesById(int[] userRoleIds) {
		UserRolesModel userRolesRes = null;
		for (int userRoleId : userRoleIds) {
			userRolesRes = getValidUsers(userRoleId);
			if (!Objects.nonNull(userRolesRes)) {
				throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
			}
			userRolesRepository.delete(userRolesRes);
			log.info("User Roles data with ID : " + userRolesRes.getUserRolesId() + " retrived succesfully");
		}
	}

	public UserRolesModel getValidUsers(int userRoleId) {
		UserRolesModel userRolesRes = null;

		try {
			userRolesRes = userRolesRepository.findById(userRoleId).get();
			return userRolesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(userRolesHelper.getNotFoundUserRolesMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
