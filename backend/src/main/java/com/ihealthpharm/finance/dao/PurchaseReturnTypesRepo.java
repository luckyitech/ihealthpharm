package com.ihealthpharm.finance.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.finance.model.PurchaseReturnTypesModel;

@Repository
public interface PurchaseReturnTypesRepo extends JpaRepository<PurchaseReturnTypesModel, Integer> {

	@Query("select p from purchase_return_types p where p.activeS='Y'")
	List<PurchaseReturnTypesModel> getAllPrTypes();

}
