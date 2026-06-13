package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.model.ModeModel;

public interface ModeRepository extends JpaRepository<ModeModel, Integer> {

	
	@Query("select m from modes m where m.type='Expenses'")
	List<ModeModel> getAllModesForExpenses();
	
	@Query("select m from modes m where m.type='Credit'")
	List<ModeModel> getAllModesOnCredit();
	
	@Query("select m from modes m where m.type='Debit'")
	List<ModeModel> getAllModesOnDebit();
	
	@Query("select m from modes m where m.type='Debit' and m.type='Credit'")
	List<ModeModel> getAllModesOnCreditAndDebit();
}
