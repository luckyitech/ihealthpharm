package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.CustomerImagesModel;

public interface CustomerImagesRepository extends JpaRepository<CustomerImagesModel, Integer> {

	
	@Query("select c from customer_id_images c where c.customerId=:customerId")
	public CustomerImagesModel findByCustomerId(@Param("customerId") Integer customerId);

}
