package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.model.ModeModel;

public interface ModeRepository extends JpaRepository<ModeModel, Integer> {

}
