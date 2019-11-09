package com.ihealthpharm.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.StockHistoryModel;


@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistoryModel, Integer> {


}
