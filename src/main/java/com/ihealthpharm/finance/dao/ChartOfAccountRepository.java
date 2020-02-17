package com.ihealthpharm.finance.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ChartOfAccountsModel;

@Repository
public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccountsModel, Integer> {

}
