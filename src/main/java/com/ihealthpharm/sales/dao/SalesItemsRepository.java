package com.ihealthpharm.sales.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesItemsModel;

@Repository
public interface SalesItemsRepository
extends JpaRepository<SalesItemsModel,Integer>
{
}