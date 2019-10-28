package com.ihealthpharm.stock.dao;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends JpaRepository<StockModel, Integer> {

	List<StockModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select s.item from stock s")
	List<ItemsModel> findAllItem();
}
