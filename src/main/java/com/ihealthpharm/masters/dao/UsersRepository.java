package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Integer>{

}
