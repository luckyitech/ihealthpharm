package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.DrugAllergies;
import com.ihealthpharm.masters.model.ItemsModel;

@Repository
public interface DrugAllergyRepository extends JpaRepository<DrugAllergies, Serializable>{

	List<DrugAllergies> findByDrugModel(ItemsModel drugInfoModel);

}
