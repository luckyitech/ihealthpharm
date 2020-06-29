package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.Discounts;

public interface DiscountsService {
	
	public Discounts saveDiscount(Discounts discount);
	
	public void deleteDiscount(Discounts discountId);
	
	public List<Discounts> getAllDiscounts();
	
	public Discounts getDiscountByDiscountId(Integer discount);
	
	public Discounts updateDiscount(Discounts discount);
	
	public Integer getMaxDiscount();
}
