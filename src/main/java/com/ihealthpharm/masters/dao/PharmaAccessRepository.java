package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmaAccessModel;

@Repository
public interface PharmaAccessRepository extends JpaRepository<PharmaAccessModel,Integer>
{
	
	@Query("SELECT pa FROM pharma_access pa where pa.activeS='Y' order by pa.lastUpdateTimestamp  desc")
	List<PharmaAccessModel> findAllLatestRecords();
}