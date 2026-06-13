package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.UserRolesModel;

public interface UserRolesService {

	 UserRolesModel saveUserRolesData(UserRolesModel userRole);

	 UserRolesModel updateUserRolesData(UserRolesModel userRole);
	
	 List<UserRolesModel> updateUserRolesData(List<UserRolesModel> userRole);
	
	 List<UserRolesModel> findAllUserRoles();
	
	 UserRolesModel findUserRoleById(Integer roleId);
	
	 void deleteUserRolesById(Integer roleIds);
	
	 void deleteUserRolesById(Integer[] roleIds);
}
