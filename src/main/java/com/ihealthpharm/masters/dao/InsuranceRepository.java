package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.InsuranceModel;

@Repository
public interface InsuranceRepository  extends JpaRepository<InsuranceModel, Integer>{

	List<InsuranceModel> findAllByOrderByCreationTimeStampDesc();
}
