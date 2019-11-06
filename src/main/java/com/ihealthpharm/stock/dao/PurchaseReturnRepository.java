package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseReturnModel;

@Repository
public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturnModel, Integer> {
	
	List<PurchaseReturnModel> findByPurchaseReturnId(@Param("purchaseReturnId") Integer purchaseReturnId);
}
