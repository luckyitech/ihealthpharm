package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.BankTransactionsModel;

@Repository
public interface BankTransactionsRepository extends JpaRepository<BankTransactionsModel, Integer> {

}
