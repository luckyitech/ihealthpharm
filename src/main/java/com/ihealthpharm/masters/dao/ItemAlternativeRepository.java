package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemAlternativeRepository extends JpaRepository<ItemAlternativeModel, Integer> {

	List<ItemAlternativeModel> findByItemId(ItemsModel item);
}
