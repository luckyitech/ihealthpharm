package com.ihealthpharm.stock.dao;

import java.util.List;

import com.ihealthpharm.stock.model.InvoiceItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemModel, Integer> {

	List<InvoiceItemModel> findAllByOrderByCreationTimeStampDesc();
	
}
