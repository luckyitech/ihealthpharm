package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.DrugManufactures;

@Repository
public interface DrugManufactureRepository extends JpaRepository<DrugManufactures, Serializable>{

	List<DrugManufactures> findByDrugModel(ItemsModel drugInfoModel);

}
