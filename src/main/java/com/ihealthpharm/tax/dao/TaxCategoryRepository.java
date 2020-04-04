package com.ihealthpharm.tax.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.tax.model.TaxCategoryModel;

@Repository
public interface TaxCategoryRepository extends JpaRepository<TaxCategoryModel, Integer> {

}
