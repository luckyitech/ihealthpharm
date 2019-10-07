package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseOrderStatusModel;


@Repository
public interface PurchaseOrderStatusRepository extends JpaRepository<PurchaseOrderStatusModel, Integer> {

	List<PurchaseOrderStatusModel> findAllByOrderByStatusDesc();
	
	@Query("select p from purchase_order_status p where p.status = :status ")
	PurchaseOrderStatusModel findByStatus(@Param("status") String status);
}
