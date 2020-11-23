package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;

@Repository
public interface BankTransactionsRepository extends JpaRepository<BankTransactionsModel, Integer> {

	
	@Query("select new  com.ihealthpharm.finance.dto.BankTransactionDTO(b.transactionId) from bank_transactions b where b.transactionId =:transactionId")
	List<BankTransactionDTO> getAllTransactionId(@Param("transactionId") String transactionId);
	
//	com.ihealthpharm.finance.model.BankTransactionsModel
	
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  bank_transactions bt,CHART_OF_ACCOUNTS ca where ca.accountId=bt.party.accountId and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findPartyAccountDetailsBySearch(@Param("searchTerm") String searchTerm);

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  bank_transactions bt,CHART_OF_ACCOUNTS ca where ca.accountId=bt.party.accountId order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllPartyAccountDetails();

	
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  bank_transactions bt,CHART_OF_ACCOUNTS ca where ca.accountId=bt.counterParty.accountId and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findCounterPartyAccountDetailsBySearch(@Param("searchTerm") String searchTerm);

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  bank_transactions bt,CHART_OF_ACCOUNTS ca where ca.accountId=bt.counterParty.accountId order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllCounterPartyAccountDetails();

	
	@Query("select bt from bank_transactions bt where bt.transactionRef like :referenceNo%")
	List<BankTransactionsModel> getAllTransactionsByRefNoSearch(@Param("referenceNo")String referenceNo);

	@Query("select bt from bank_transactions bt where bt.bankTransactionId=:bankTransactionId")
	BankTransactionsModel getBankTxnDetailsById(@Param("bankTransactionId")Integer bankTransactionId);

	@Query("select bt from bank_transactions bt")
	List<BankTransactionsModel> getAllTxnsByPagination(Pageable pagination);
	
}
