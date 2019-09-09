package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.QuotationModel;

public interface QuotationRepository extends JpaRepository<QuotationModel, Integer> {

}
