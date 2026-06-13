package com.ihealthpharm.stock.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.stock.model.AutoQuotationsModel;

public interface AutoQuotationRepository extends JpaRepository<AutoQuotationsModel, Integer>{

	
	@Modifying
	@Transactional
	@Query("update auto_quotations aq set aq.activeS =:flag "
			+ "where aq.itemId.itemId=:itemId and aq.supplierId=:supplierId")
	void updateAutoQuotationItemStatus(@Param("itemId")Integer itemId,@Param("supplierId")Integer supplierId,
			@Param("flag")Character flag);

	@Query("select aq from auto_quotations aq where aq.autoQuotId=:autoQuotId")
	AutoQuotationsModel findByAutoQuotId(@Param("autoQuotId")Integer autoQuotId);

	
	
}
