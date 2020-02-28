package com.ihealthpharm.finance.dao;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ExpensesModel;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Integer>{

	@Query("update PETTY_CASH p set p.balance=:balance where p.counterPartyNo.accountId=:pettyCashId")
	@Transactional
	@Modifying
	Integer updateBalanceInPettyCash(@Param("pettyCashId")Integer pettyCashId,@Param("balance") Double balance);

}
