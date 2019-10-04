package com.ihealthpharm.stock.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseOrderModel;

@Repository
public interface PurchaseOrderRepository
extends JpaRepository<PurchaseOrderModel,Integer>
{
}