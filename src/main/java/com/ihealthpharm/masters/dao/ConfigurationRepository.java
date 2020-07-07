package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.masters.model.ConfigurationModel;

public interface ConfigurationRepository extends JpaRepository<ConfigurationModel, Integer> {
	@Query("select c.configValue from configuration c where c.configDesc='maxdiscount' and c.activeS='Y'")
	public Integer findMaxDiscount();

	@Query("select c from configuration c  where c.configDesc=:configDesc")
	public List<ConfigurationModel> findByConfigDesc(@Param("configDesc") String configDesc);

	@Query("select c.configValue from configuration c where c.configDesc='margin' and c.activeS='Y'")
	public Integer findMargin();

	@Transactional
	@Modifying
	@Query("update stock s set s.unitSaleRate=((s.unitPurchaseRate*(1+(:margin/100)))*(1+(:markup/100))) where s.unitPurchaseRate is not null")
	public Integer updateStockPrice(@Param("margin") Integer margin, @Param("markup") Integer markup);

}
