package com.ihealthpharm.masters.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PurchaseOrderModel;

@Repository
public interface PurchaseOrderRepository
extends JpaRepository<PurchaseOrderModel,Integer>
{
}