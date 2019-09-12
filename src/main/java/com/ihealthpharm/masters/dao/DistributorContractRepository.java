package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.DistributorContractModel;

public interface DistributorContractRepository extends JpaRepository<DistributorContractModel, Integer>{

	List<DistributorContractModel> findByActiveS(char c);

	List<DistributorContractModel> findAllByOrderByLastUpdateTimestampDesc();

}
