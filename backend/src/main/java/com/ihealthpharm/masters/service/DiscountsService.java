package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.DiscountsModel;

public interface DiscountsService {
	
	public DiscountsModel saveDiscount(DiscountsModel discount);
	
	public void deleteDiscount(DiscountsModel discountId);
	
	public List<DiscountsModel> getAllDiscountsWithActiveYes();
	
	public List<DiscountsModel> getAllDiscounts();
	
	public DiscountsModel getDiscountByDiscountId(Integer discount);
	
	public DiscountsModel updateDiscount(DiscountsModel discount);
	
	public Integer getMaxDiscount();
}
