/**
 * 
 */
package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyModel;

/**
 * @author Vikas
 *
 */
@Repository
public interface PharmacyBranchRepository extends JpaRepository<PharmacyBranchModel, Serializable> {

	PharmacyBranchModel findByPharmacyBranchId(int branchModelID);

	List<PharmacyBranchModel> findByPharmacy(PharmacyModel pharmacyModel);

	
}
