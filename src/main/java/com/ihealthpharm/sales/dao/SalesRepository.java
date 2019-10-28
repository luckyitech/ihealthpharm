package com.ihealthpharm.sales.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesRepository
extends JpaRepository<SalesModel,Integer>
{
}