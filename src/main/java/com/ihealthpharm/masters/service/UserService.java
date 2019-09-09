package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.UsersModel;


public interface UserService {
	
	public UsersModel saveUsersData(UsersModel user);

	public UsersModel updateUsersData(UsersModel user);
	
	public List<UsersModel> updateUsersData(List<UsersModel> users);
	
	public List<UsersModel> findAllUsers();
	
	public UsersModel findUserById(int UserId);
	
	public void deleteUsersById(int UserIds);
	
	public void deleteUsersById(int[] UsersIds);

}
