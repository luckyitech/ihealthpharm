package com.ihealthpharm.expenses.dao;

import com.ihealthpharm.expenses.model.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Integer>{

}
