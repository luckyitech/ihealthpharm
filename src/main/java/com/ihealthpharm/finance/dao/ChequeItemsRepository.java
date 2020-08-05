package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ChequeItemsModel;

@Repository
public interface ChequeItemsRepository extends JpaRepository<ChequeItemsModel, Integer> {

}
