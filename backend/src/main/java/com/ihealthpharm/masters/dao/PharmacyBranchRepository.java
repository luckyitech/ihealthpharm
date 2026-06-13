/**
 * 
 */
package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyModel;

@Repository
public interface PharmacyBranchRepository extends JpaRepository<PharmacyBranchModel, Integer> {

	List<PharmacyBranchModel> findByPharmacy(PharmacyModel pharmacyModel);
	
	List<PharmacyBranchModel> findAllByOrderByCreationTimeStampDesc();
}
