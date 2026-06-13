package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.FamilyAccountModel;

public interface FamilyAccountRepository extends JpaRepository<FamilyAccountModel, Integer> {

}
