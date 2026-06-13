package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ihealthpharm.finance.model.GeneralLedgerModel;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedgerModel,Integer> {
	
	
	@Query("SELECT sum(debit) from general_ledger g where g.counterParty='PETTY CASH' group by g.counterParty")
	Double getTotalLimitFromGL();

}
