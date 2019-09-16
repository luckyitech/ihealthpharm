package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeCredentialsModel;

public interface EmployeeCredentialsRepository extends JpaRepository<EmployeeCredentialsModel, Integer>{

	public EmployeeCredentialsModel findByUserNameAndCurrentPassword(String userName, String currentPassword);

	public EmployeeCredentialsModel findByUserName(String userName);
}
