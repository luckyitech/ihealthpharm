package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.RolesModel;

public interface RolesService {

	public RolesModel saveRolesData(RolesModel role);

	public RolesModel updateRolesData(RolesModel role);
	
	public List<RolesModel> updateRolesData(List<RolesModel> role);
	
	public List<RolesModel> findAllRoles();
	
	public RolesModel findRoleById(int roleId);
	
	public void deleteRolesById(int roleIds);
	
	public void deleteRolesById(int[] roleIds);
}
