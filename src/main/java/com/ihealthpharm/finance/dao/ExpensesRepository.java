package com.ihealthpharm.finance.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.dto.expensesDTO;
import com.ihealthpharm.finance.model.ExpensesModel;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Integer>{

	@Query("update PETTY_CASH p set p.balance=:balance where p.counterPartyNo.accountId=:pettyCashId")
	@Transactional
	@Modifying
	Integer updateBalanceInPettyCash(@Param("pettyCashId")Integer pettyCashId,@Param("balance") Double balance);

	//expenses report queries
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  expenses ex,CHART_OF_ACCOUNTS ca where ca.accountId=ex.account.accountId and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findPartyDetailsBySearch(@Param("searchTerm") String searchTerm);

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  expenses ex,CHART_OF_ACCOUNTS ca where ca.accountId=ex.account.accountId order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllPartyDetails();
	
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  expenses ex,CHART_OF_ACCOUNTS ca where ca.accountId=ex.counterPartyNo.accountId order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllCounterPartyDetails();
	
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  expenses ex,CHART_OF_ACCOUNTS ca where ca.accountId=ex.counterPartyNo.accountId and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findExpenseCounterPartyBySearch(@Param("searchTerm") String searchTerm);

	
	@Query("select new  com.ihealthpharm.finance.dto.expensesDTO(ex.txnId) from expenses ex where ex.txnId =:transactionId")
	List<expensesDTO> getAllTransactionId(@Param("transactionId") String transactionId);
	
	@Query("select e from expenses e where e.expensesId is not null")
	List<ExpensesModel> getAllExpencesByPagination(Pageable limit);
	
	@Query("select count(e) from expenses e where e.expensesId is not null")
	Integer getExpecncesCount();
}
