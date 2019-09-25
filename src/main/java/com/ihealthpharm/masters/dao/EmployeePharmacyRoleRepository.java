package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.EmployeePharmacyRoleModel;

@Repository
public interface EmployeePharmacyRoleRepository
extends JpaRepository<EmployeePharmacyRoleModel,Integer>
{
}