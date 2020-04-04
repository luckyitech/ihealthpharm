package com.ihealthpharm.tax.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.tax.helper.TaxCategoryHelper;
import com.ihealthpharm.tax.model.TaxCategoryModel;
import com.ihealthpharm.tax.service.TaxCategoryService;


@RestController
@CrossOrigin
public class TaxCategoryController {
	
	@Autowired
	private TaxCategoryService taxService;
	
	@Autowired
	private TaxCategoryHelper taxHelper;
	
	
	@GetMapping("/getall/taxcategories")
	public ResponseEntity<BaseDto<List<TaxCategoryModel>>> getAllTaxCategories(){
		List<TaxCategoryModel> response=taxService.getAllTaxCategories();
		return new BaseDto<>(response, taxHelper.getRetrieveTaxCategoryMessage(), OK).respond();
	}

}
