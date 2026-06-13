package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.UserRolesModel;

public interface UserRolesRepository extends JpaRepository<UserRolesModel, Integer>{

}
