package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.UsersModel;


public interface UserService {
	
	 UsersModel saveUsersData(UsersModel user);

	 UsersModel updateUsersData(UsersModel user);
	
	 List<UsersModel> updateUsersData(List<UsersModel> users);
	
	 List<UsersModel> findAllUsers();
	
	 UsersModel findUserById(Long UserId);
	
	 UsersModel findUserByUserName(String userName);
	
	 void deleteUsersById(Long UserIds);
	
	 void deleteUsersById(Long[] UsersIds);

}
