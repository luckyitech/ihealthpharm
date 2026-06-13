package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.UsersRepository;
import com.ihealthpharm.masters.helper.UsersHelper;
import com.ihealthpharm.masters.model.UsersModel;
import com.ihealthpharm.masters.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository usersRepository; 
	
	@Autowired
	UsersHelper usersHelper;
	
	@Override
	public UsersModel saveUsersData(UsersModel user) {
		UsersModel usersRes = usersRepository.save(user);
		log.info("Users data with ID : " + usersRes.getUserId() + " Saved succesfully");
		return usersRes;
	}

	@Override
	public UsersModel updateUsersData(UsersModel user) {
		UsersModel usersRes = getValidUsers(user.getUserId());
		if (!Objects.nonNull(usersRes)) {
			throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
		}

		usersRes = usersRepository.save(user);
		log.info("Users data with ID : " + usersRes.getUserId() + " updated succesfully");
		return usersRes;
	}

	@Override
	public List<UsersModel> updateUsersData(List<UsersModel> users) {
		
		for (UsersModel user : users) {
			UsersModel userRes = getValidUsers(user.getUserId());
			if (!Objects.nonNull(userRes)) {
				throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
			}

			userRes = usersRepository.save(user);
			log.info("Users data with ID : " + userRes.getUserId() + " updated succesfully");
		}
		return users;
	}

	@Override
	public List<UsersModel> findAllUsers() {
		
		return usersRepository.findAll();
	}

	@Override
	public UsersModel findUserById(Long userId) {
		UsersModel usersRes = getValidUsers(userId);
		if (!Objects.nonNull(usersRes)) {
			throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Users data with ID : " + usersRes.getUserId() + " retrieved succesfully");
		return usersRes;
	}

	@Override
	public void deleteUsersById(Long userId) {
		
		UsersModel usersRes = usersRepository.getOne(userId);
		if (!Objects.nonNull(usersRes)) {
			throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Users data with ID : " + usersRes.getUserId() + " Deleted succesfully");
		usersRepository.delete(usersRes);
		
	}

	@Override
	public void deleteUsersById(Long[] usersIds) {
		
		UsersModel usersRes;
		for (Long user : usersIds) {
			usersRes = getValidUsers(user);
			if (!Objects.nonNull(usersRes)) {
				throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
			}
			usersRepository.delete(usersRes);
			log.info("Users data with ID: " + usersRes.getUserId() + " deleted succesfully");
		}

	}

	public UsersModel getValidUsers(Long userId) {
		UsersModel userRes = null;

		try {
			userRes = usersRepository.findById(userId).get();
			return userRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(usersHelper.getNotFoundUsersMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UsersModel findUserByUserName(String userName) {
		
		return usersRepository.findByUserName(userName);
	}
}
