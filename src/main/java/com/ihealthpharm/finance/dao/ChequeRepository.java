package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ChequeModel;

@Repository
public interface ChequeRepository extends JpaRepository<ChequeModel, Integer>{

}
