package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.InsuranceModel;

@Repository
public interface InsuranceRepository  extends JpaRepository<InsuranceModel, Integer>{

	@Query("select i from insurance i where i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<InsuranceModel> findAllLastestRecords();

	InsuranceModel findByPolicyCode(String policyCode);

	@Query("select i from insurance i where i.policyDescription like :searchTerm% or i.policyCode like :searchTerm%")
	List<InsuranceModel> findByPolicyCodeOrPolicyDescription(@Param("searchTerm") String searchTerm);
}
