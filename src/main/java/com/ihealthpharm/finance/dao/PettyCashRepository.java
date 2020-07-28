package com.ihealthpharm.finance.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.finance.model.PettyCashModel;

@Repository
public interface PettyCashRepository extends JpaRepository<PettyCashModel, Integer>{

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from general_ledger gl,CHART_OF_ACCOUNTS ca where ca.accountNo=gl.party and gl.entryType='Petty Cash' or gl.entryType='Exp PettyCash' and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findPartyAccountDetailsBySearch(@Param("searchTerm") String searchTerm);

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  general_ledger gl,CHART_OF_ACCOUNTS ca where ca.accountNo=gl.party and gl.entryType='Petty Cash' or gl.entryType='Exp PettyCash' order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllPartyAccountDetails();

	
	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  general_ledger gl,CHART_OF_ACCOUNTS ca where ca.accountNo=gl.counterParty and gl.entryType='Petty Cash' or gl.entryType='Exp PettyCash' and (concat(ca.accountName,' : ',ca.accountNo)) like :searchTerm%")
	List<String> findCounterPartyAccountDetailsBySearch(@Param("searchTerm") String searchTerm);

	@Query("select distinct ((concat(ca.accountName,' : ',ca.accountNo))) from  general_ledger gl,CHART_OF_ACCOUNTS ca where ca.accountNo=gl.counterParty and gl.entryType='Petty Cash' or gl.entryType='Exp PettyCash' order by (concat(ca.accountName,' : ',ca.accountNo))")
	List<String> findAllCounterPartyAccountDetails();
}
