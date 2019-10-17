package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.SupplierContractItemsModel;

public interface SupplierContractItemsRepository extends JpaRepository<SupplierContractItemsModel, Integer>{

	List<SupplierContractItemsModel> findByActiveS(char c);

	List<SupplierContractItemsModel> findAllByOrderByLastUpdateTimestampDesc();
}
