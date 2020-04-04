package com.ihealthpharm.tax.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.tax.dao.TaxCategoryRepository;
import com.ihealthpharm.tax.model.TaxCategoryModel;
import com.ihealthpharm.tax.service.TaxCategoryService;


@Service
@Transactional
public class TaxCategoryServiceImpl implements TaxCategoryService{
	
	
	@Autowired
	private TaxCategoryRepository taxRepo;
	
	/*@Autowired
	private TaxCategoryHelper taxHelper;*/
	
	
	@Override
	public List<TaxCategoryModel> getAllTaxCategories() {
		return taxRepo.findAll();
	}

}
