package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.EmployeeAccessModel;

@Repository
public interface EmployeeAccessRepository
extends JpaRepository<EmployeeAccessModel,Integer>
{
}