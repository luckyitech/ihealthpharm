package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.DiscountsModel;

public interface DiscountsRepository extends JpaRepository<DiscountsModel,Integer>{

	@Query(value="select distinct max(d.discountValue) from discounts d where d.activeS=Y")
	public Integer findMaxDiscountValue();

	@Query(value="select d from discounts d where d.activeS=:status and d.discountValue<(select c.configValue from configuration c where c.configDesc='maxdiscount' and c.activeS='Y')")
	public List<DiscountsModel> findAllByActiveS(@Param("status") Character status);

}
