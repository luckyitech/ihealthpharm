package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.UserRolesModel;

public interface UserRolesService {

	public UserRolesModel saveUserRolesData(UserRolesModel userRole);

	public UserRolesModel updateUserRolesData(UserRolesModel userRole);
	
	public List<UserRolesModel> updateUserRolesData(List<UserRolesModel> userRole);
	
	public List<UserRolesModel> findAllUserRoles();
	
	public UserRolesModel findUserRoleById(int roleId);
	
	public void deleteUserRolesById(int roleIds);
	
	public void deleteUserRolesById(int[] roleIds);
}
