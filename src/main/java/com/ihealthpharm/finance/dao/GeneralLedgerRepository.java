package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.finance.model.GeneralLedgerModel;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedgerModel,Integer> {

}
