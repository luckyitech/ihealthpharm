package com.ihealthpharm.finance.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ExpensesModel;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Integer>{

}
