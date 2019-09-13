package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.DistributorContractItemsModel;

public interface DistributorContractItemsRepository extends JpaRepository<DistributorContractItemsModel, Integer>{

	List<DistributorContractItemsModel> findByActiveS(char c);

	List<DistributorContractItemsModel> findAllByOrderByLastUpdateTimestampDesc();
}
