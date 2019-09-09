package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemDistributorModel;

@Repository
public interface ItemDistributorsRepository extends JpaRepository<ItemDistributorModel, Integer> {

	//List<ItemDistributorModel> findByItemId(ItemsModel existingDrugInfo);
	List<ItemDistributorModel> findByActiveS(String active);

}
