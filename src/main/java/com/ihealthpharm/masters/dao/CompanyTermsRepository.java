package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.CompanyTermsModel;

@Repository
public interface CompanyTermsRepository extends JpaRepository<CompanyTermsModel, Integer> {

	List<CompanyTermsModel> findAllByOrderByLastUpdateTimestampDesc();
	
}
