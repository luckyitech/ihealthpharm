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
import com.ihealthpharm.masters.helper.ItemGenericNamesHelper;
import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.masters.service.ItemGenericNamesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ItemGenericNameController {
	
	@Autowired
	private ItemGenericNamesService itemGenericNamesService;
	
	@Autowired
	private ItemGenericNamesHelper itemGenericHelper;
	
	@PostMapping("/save/itemgeneric")
	public ResponseEntity<BaseDto<ItemGenericNamesModel>> insertItemGenericData(@Valid @RequestBody ItemGenericNamesModel itemGenericNamesModel) {
		log.info("Request Object insert is: "+ itemGenericNamesModel);
		ItemGenericNamesModel itemGenericModelRes = itemGenericNamesService.saveItemGenericNamesData(itemGenericNamesModel);
		return new BaseDto<>(itemGenericModelRes,itemGenericHelper.getSaveItemGenericNameMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemgeneric")
	public ResponseEntity<BaseDto<ItemGenericNamesModel>> updateItemGenericData(@Valid @RequestBody ItemGenericNamesModel itemGenericNamesModel) {
		log.info("Request Object for update is: ",itemGenericNamesModel);
		ItemGenericNamesModel itemModelRes = itemGenericNamesService.updateItemGenericNameData(itemGenericNamesModel);
		return new BaseDto<>(itemModelRes,itemGenericHelper.getUpdateItemGenericNameMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemgenerics")
	public ResponseEntity<BaseDto<List<ItemGenericNamesModel>>> updateItemsGenericNamesData(@Valid @RequestBody List<ItemGenericNamesModel> itemGenericNamesModels) {
		log.info("Request Object for update is: "+ itemGenericNamesModels);
		List<ItemGenericNamesModel> itemRes = itemGenericNamesService.updateItemGenericNamesData(itemGenericNamesModels);
		return new BaseDto<>(itemRes,itemGenericHelper.getUpdateItemGenericNameMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemgeneric")
	public ResponseEntity<BaseDto<Object>> deleteItemGenericData(@RequestParam Integer itemGenericId) {
		log.info("Request Object for delete is: ", itemGenericId);
		itemGenericNamesService.deleteItemGenericNameById(itemGenericId);
		return new BaseDto<>(itemGenericHelper.getDeleteItemGenericNameMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/itemgenerics")
	public ResponseEntity<BaseDto<Object>> deleteItemsGenericNamesData(@RequestParam Integer[] itemGenericIds) {
		log.info("Request Object for delete is: "+ itemGenericIds);
		itemGenericNamesService.deleteMultipleItemGenericNamesById(itemGenericIds);
		return new BaseDto<>(itemGenericHelper.getDeleteItemGenericNameMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemgenericsdata")
	public ResponseEntity<BaseDto<List<ItemGenericNamesModel>>> getAllItemGenericsdata() {
		List<ItemGenericNamesModel> result = itemGenericNamesService.getAllGenerics();
		return new BaseDto<>(result, itemGenericHelper.getRetrieveItemGenericNameMessage(), OK).respond();
	}

	@GetMapping("/getactiveitemgenericsdata")
	public ResponseEntity<BaseDto<List<ItemGenericNamesModel>>> getItemGenericdata() {
		List<ItemGenericNamesModel> result = itemGenericNamesService.findItemGenericNameByActive();
		return new BaseDto<>(result, itemGenericHelper.getRetrieveItemGenericNameMessage(), OK).respond();
	}
	
	@GetMapping("/getitemgenericdatabyid")
	public ResponseEntity<BaseDto<ItemGenericNamesModel>> getItemGenericDataById(@RequestParam Integer itemGenericId) {
		ItemGenericNamesModel result = itemGenericNamesService.findItemGenericNameById(itemGenericId);
		return new BaseDto<>(result, itemGenericHelper.getRetrieveItemGenericNameMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemgenericnamesdata")
	public ResponseEntity<BaseDto<List<ItemGenericNamesModel>>> getAllItemGenericNamesdata(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm,@RequestParam Integer itemGroupId) {
		List<ItemGenericNamesModel> result = itemGenericNamesService.findAllItemGenericNamesData(medicalOrNonMedical, searchTerm, itemGroupId);
		return new BaseDto<>(result, itemGenericHelper.getRetrieveItemGenericNameMessage(), OK).respond();
	}

}
