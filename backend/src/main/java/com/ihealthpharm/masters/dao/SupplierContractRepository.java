package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.SupplierContractModel;

public interface SupplierContractRepository extends JpaRepository<SupplierContractModel, Integer>{

	List<SupplierContractModel> findByActiveS(char c);

	List<SupplierContractModel> findAllByOrderByLastUpdateTimestampDesc();

}
