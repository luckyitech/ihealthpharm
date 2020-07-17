package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.MasterAccountModel;

public interface MasterAccountRepository extends JpaRepository<MasterAccountModel, Integer> {

}
