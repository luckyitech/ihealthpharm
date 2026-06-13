package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ihealthpharm.finance.model.BankTransactionHistoryModel;


public interface BankTransactionsHistoryRepository extends JpaRepository<BankTransactionHistoryModel, Integer>{

}
