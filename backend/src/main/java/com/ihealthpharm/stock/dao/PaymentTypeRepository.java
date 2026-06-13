package com.ihealthpharm.stock.dao;

import java.util.List;

import com.ihealthpharm.stock.model.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeModel, Integer> {

	List<PaymentTypeModel> findAllByOrderByTypeDesc();
}
