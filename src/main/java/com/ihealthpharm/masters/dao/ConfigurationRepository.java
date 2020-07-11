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
	@Query("update stock s set s.unitSaleRate= CASE WHEN s.unitPurchaseRate is not null THEN ROUND(((s.unitPurchaseRate*(1+(:margin/100)))*(1+(:markup/100))),2) ELSE 0 END, s.entryType='sales price update',s.mrp=s.unitSaleRate "
			+ " where s.unitPurchaseRate is not null and "
			+ "s.item.itemId in (select i.itemId from items i left join items_categories ic on i.itemCategory.itemCategoryId=ic.itemCategoryId where ic.marginPercentage is null and i.itemId=s.item.itemId)")
	public Integer updateStockPrice(@Param("margin") Integer margin, @Param("markup") Integer markup);
	
	@Transactional
	@Modifying
	@Query(value="update stock s, items i, items_categories ic set " + 
			"s.UNIT_SALE_RATE = CASE WHEN ic.MARGIN_PERCENTAGE is not null THEN ROUND(((s.UNIT_PURCHASE_RATE*(1+(ic.MARGIN_PERCENTAGE/100)))*(1+(:markup/100))),2) ELSE "
			+ " ROUND(((s.UNIT_PURCHASE_RATE*(1+(:margin/100)))*(1+(:markup/100))),2) END, "
			+ "s.ENTRY_TYPE='sales price update',s.MRP=s.UNIT_PURCHASE_RATE " + 
			"where i.ITEM_ID=s.ITEM_ID and i.ITEM_CATEGORIE_ID=ic.ITEM_CATEGORIE_ID and s.UNIT_PURCHASE_RATE is not null and ic.MARGIN_PERCENTAGE is not null",nativeQuery = true)
	public Integer updateStockWithCategory(@Param("margin") Integer margin, @Param("markup") Integer markup);

}

