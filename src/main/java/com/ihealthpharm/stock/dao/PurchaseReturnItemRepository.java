package com.ihealthpharm.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseReturnItemModel;

@Repository
public interface PurchaseReturnItemRepository extends JpaRepository<PurchaseReturnItemModel, Integer> {

}
