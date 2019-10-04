package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.model.EmployeeCredentialsRetriveModel;

public interface EmployeeCredentialsRetriveRepository extends JpaRepository<EmployeeCredentialsRetriveModel, Integer>{

	public EmployeeCredentialsRetriveModel findByUserNameAndCurrentPassword(String userName, String currentPassword);

	public EmployeeCredentialsRetriveModel findByUserName(String userName);
}
