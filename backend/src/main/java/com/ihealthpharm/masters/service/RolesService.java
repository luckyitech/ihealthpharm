package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.RolesModel;

public interface RolesService {

	RolesModel saveRolesData(RolesModel role);

	RolesModel updateRolesData(RolesModel role);

	List<RolesModel> updateRolesData(List<RolesModel> role);

	List<RolesModel> findAllRoles();

	RolesModel findRoleById(Integer roleId);

	void deleteRolesById(Integer roleIds);

	void deleteRolesById(Integer[] roleIds);
}
