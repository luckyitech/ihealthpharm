package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.AccountTypeModel;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeModel, Integer>{

}
