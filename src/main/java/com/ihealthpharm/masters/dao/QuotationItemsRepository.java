package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.masters.model.QuotationItemsModel;

@Repository
public interface QuotationItemsRepository
extends JpaRepository<QuotationItemsModel,Integer>
{
}