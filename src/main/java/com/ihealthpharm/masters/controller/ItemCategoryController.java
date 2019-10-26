package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.ItemCategoryHelper;
import com.ihealthpharm.masters.model.ItemCategoryModel;
import com.ihealthpharm.masters.service.ItemCategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ItemCategoryController {

	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	private ItemCategoryHelper itemCategoryHelper;
	
	
	@PostMapping("/save/itemcategory")
	public ResponseEntity<BaseDto<ItemCategoryModel>> insertItemCategoryData(@Valid @RequestBody ItemCategoryModel itemCategoryModel) {
		System.out.println("item cat save");
		log.info("Request Object insert is: "+ itemCategoryModel);
		
		ItemCategoryModel itemCategoryModelRes =  itemCategoryService.saveItemCategoryData(itemCategoryModel);
		return new BaseDto<>(itemCategoryModelRes,itemCategoryHelper.getSaveItemCategoryMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemcategory")
	public ResponseEntity<BaseDto<ItemCategoryModel>> updateItemCategoryData(@Valid @RequestBody ItemCategoryModel itemCategoryModel) {
		log.info("Request Object for update is: ",itemCategoryModel);
		ItemCategoryModel itemCategoryModelRes = itemCategoryService.updateItemCategoryData(itemCategoryModel);
		return new BaseDto<>(itemCategoryModelRes,itemCategoryHelper.getUpdateItemCategoryMessage(),OK).respond();
	}
	
	

	@PutMapping("/update/itemcategories")
	public ResponseEntity<BaseDto<List<ItemCategoryModel>>> updateItemCategoriesData(@Valid @RequestBody List<ItemCategoryModel> itemCategoryModels) {
		log.info("Request Object for update is: "+ itemCategoryModels);
		List<ItemCategoryModel> itemCategoryModelRes = itemCategoryService.updateItemCategoriesData(itemCategoryModels);
		return new BaseDto<>(itemCategoryModelRes,itemCategoryHelper.getUpdateItemCategoryMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemcategory")
	public ResponseEntity<BaseDto<Object>> deleteItemCategoryData(@RequestParam int itemCategoryId) {
		log.info("Request Object for delete is: ", itemCategoryId);
		itemCategoryService.deleteItemCategoryById(itemCategoryId);
		return new BaseDto<>(itemCategoryHelper.getDeleteItemCategoryMessage(), OK).respond();
	}
	
	
	@DeleteMapping("/delete/itemcategories")
	public ResponseEntity<BaseDto<Object>> deleteItemCategoriesData(@RequestParam int[] itemCategoryIds) {
		log.info("Request Object for delete is: "+ itemCategoryIds);
		itemCategoryService.deleteMultipleItemCategoriesById(itemCategoryIds);
		return new BaseDto<>(itemCategoryHelper.getDeleteItemCategoryMessage(), OK).respond();
	}
	

	@GetMapping("/getallitemcategories")
	public ResponseEntity<BaseDto<List<ItemCategoryModel>>> getAllItemGenericsdata() {
		List<ItemCategoryModel> result = itemCategoryService.findAllCategories();
		return new BaseDto<>(result, itemCategoryHelper.getRetrieveItemCategoryMessage(), OK).respond();
	}
	

	@GetMapping("/getactiveitemcategoriesdata")
	public ResponseEntity<BaseDto<List<ItemCategoryModel>>> getItemCategorydata() {
		List<ItemCategoryModel> result = itemCategoryService.findItemCategoryByActive();
		return new BaseDto<>(result, itemCategoryHelper.getRetrieveItemCategoryMessage(), OK).respond();
	}
	
	@GetMapping("/getitemcategorydatabyid")
	public ResponseEntity<BaseDto<ItemCategoryModel>> getItemCategoryDataById(@RequestParam int itemCategoryId) {
		ItemCategoryModel result = itemCategoryService.findItemCategoryById(itemCategoryId);
		return new BaseDto<>(result, itemCategoryHelper.getRetrieveItemCategoryMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemcategoriesdata")
	public ResponseEntity<BaseDto<List<ItemCategoryModel>>> getAllItemCategoriesdata(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm) {
		List<ItemCategoryModel> result = itemCategoryService.findAllItemCategoryData(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(result, itemCategoryHelper.getRetrieveItemCategoryMessage(), OK).respond();
	}
	
	
}
