package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsModel, Serializable> {

	public List<ItemsModel> findByActiveS(String s);
	
	List<ItemsModel> findAllByOrderByCreationTimeStampDesc();

}
