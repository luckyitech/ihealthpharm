package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.DeliveryTypesModel;


@Repository
public interface DeliveryTypesRepository extends JpaRepository<DeliveryTypesModel, Integer> {

	List<DeliveryTypesModel> findAllByOrderByTypeDesc();
}
