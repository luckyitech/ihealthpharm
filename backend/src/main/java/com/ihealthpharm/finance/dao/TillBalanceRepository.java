package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.finance.model.TillBalanceModel;

public interface TillBalanceRepository extends JpaRepository<TillBalanceModel, Integer>{

	
	@Query("select distinct t.customerName from TILL_BALANCE t where t.customerName like :customer%")
	List<String> findAllTillCustomersBySearch(@Param("customer")String customer);

	@Query("select distinct t.customerName from TILL_BALANCE t")
	List<String> findAllTillcustomers();

	@Query("select distinct (concat(ca.accountName,' : ',ca.accountNo)) from CHART_OF_ACCOUNTS ca "
			+ "inner join TILL_BALANCE t on ca.accountId=t.tillAccount.accountId "
			+ "where (concat(ca.accountName,' : ',ca.accountNo)) like :tillAccount%")
	List<String> findTillAccountsBySearch(@Param("tillAccount")String tillAccount);

	@Query("select distinct (concat(ca.accountName,' : ',ca.accountNo)) from CHART_OF_ACCOUNTS ca "
			+ "inner join TILL_BALANCE t on ca.accountId=t.tillAccount.accountId")
	List<String> findAllTillAccounts();

}
