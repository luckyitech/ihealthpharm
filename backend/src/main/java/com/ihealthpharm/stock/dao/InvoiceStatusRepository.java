package com.ihealthpharm.stock.dao;

import java.util.List;

import com.ihealthpharm.stock.model.InvoiceStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatusModel, Integer> {

	List<InvoiceStatusModel> findAllByOrderByStatusDesc();
}
