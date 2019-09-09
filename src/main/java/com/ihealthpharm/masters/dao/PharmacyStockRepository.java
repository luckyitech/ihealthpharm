/**
 * 
 */
package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyStockModel;

/**
 * @author Vikas
 *
 */
@Repository
public interface PharmacyStockRepository extends JpaRepository<PharmacyStockModel, Serializable> {

	PharmacyStockModel findByStockPointId(int stockPointId);

	List<PharmacyStockModel> findByPharmacyBranch(PharmacyBranchModel pharmacyBranchModel);

	
	
}
