package com.ihealthpharm.sales.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesReturnItemsModel;

@Repository
public interface SalesReturnItemRepository extends JpaRepository<SalesReturnItemsModel, Integer> {

}
