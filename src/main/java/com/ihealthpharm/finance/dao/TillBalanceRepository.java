package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.finance.model.TillBalanceModel;

public interface TillBalanceRepository extends JpaRepository<TillBalanceModel, Integer>{

}
