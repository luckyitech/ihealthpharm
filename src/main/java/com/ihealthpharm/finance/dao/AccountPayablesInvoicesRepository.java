package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.AccountPayablesInvoicesModel;

@Repository
public interface AccountPayablesInvoicesRepository extends JpaRepository<AccountPayablesInvoicesModel,Integer>
{
	List<AccountPayablesInvoicesModel> findAll();
	List<AccountPayablesInvoicesModel> findAllByOrderByLastUpdateTimestampDesc();
}