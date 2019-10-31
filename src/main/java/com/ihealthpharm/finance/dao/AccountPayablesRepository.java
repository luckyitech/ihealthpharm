package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.AccountPayablesModel;

@Repository
public interface AccountPayablesRepository extends JpaRepository<AccountPayablesModel,Integer>
{
	List<AccountPayablesModel> findAll();

}