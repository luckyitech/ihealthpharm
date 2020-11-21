package com.ihealthpharm.finance.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;

@Repository
public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccountsModel, Integer> {
	
	@Query("select new com.ihealthpharm.finance.model.ChartOfAccountsModel(ca.accountId, ca.accountNo,concat(ca.accountName,' : ',ca.accountNo),"
			+ "ca.accountType,ca.date, ca.asOfDate,ca.transactionLimit, ca.totalLimit, ca.currentBalance, ca.pharmacyModel, ca.auditId) from CHART_OF_ACCOUNTS ca where ca.pharmacyModel.pharmacyId=:pharmacyId")
	List<ChartOfAccountsModel> getCOAByPharmaId(@Param("pharmacyId")Integer pharmacyId);

	@Query("select c from CHART_OF_ACCOUNTS c where c.accountNo like :accountNo%")
	List<ChartOfAccountsModel> getAllChartOfAccounts(@Param("accountNo") String accountNo);

	@Query("select c.currentBalance from CHART_OF_ACCOUNTS c where c.accountId=:accountId")
	public Double findBalanceRepo(@Param("accountId")Integer accountId);

	@Query("select new com.ihealthpharm.finance.model.ChartOfAccountsModel(ca.accountId, ca.accountNo,concat(ca.accountName,' : ',ca.accountNo),"
			+ "ca.accountType,ca.date, ca.asOfDate,ca.transactionLimit, ca.totalLimit, ca.currentBalance, ca.pharmacyModel, ca.auditId) from CHART_OF_ACCOUNTS ca")
	List<ChartOfAccountsModel> findAllChartOfAccounts();

	@Query("select c from CHART_OF_ACCOUNTS c where c.accountType.accountType=:accountType")
	List<ChartOfAccountsModel> getByAccType(@Param("accountType")String accountType);

	@Transactional
	@Modifying
	@Query("update CHART_OF_ACCOUNTS set currentBalance=:amount where accountId=:party")
	void updatePartyBalance(@Param("party")Integer party, @Param("amount")Double amount);

	@Transactional
	@Modifying
	@Query("update CHART_OF_ACCOUNTS set currentBalance=:amount where accountId=:counterParty")
	void updateCounterPartyBalance(@Param("counterParty")Integer party, @Param("amount")Double amount);

	@Query("select c from CHART_OF_ACCOUNTS c where c.accountId=:accountId")
	ChartOfAccountsModel getChartOfAccountById(@Param("accountId")Integer accountId);
}
