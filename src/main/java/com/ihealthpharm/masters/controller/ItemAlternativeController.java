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
import com.ihealthpharm.masters.dto.AlternativeItemsRequestDTO;
import com.ihealthpharm.masters.helper.ItemAlternativeHelper;
import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemAlternativeService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class ItemAlternativeController {

	@Autowired
	ItemAlternativeService itemAlternativeService;
	
	@Autowired
	ItemAlternativeHelper itemAlternativeHelper;
	
	@PostMapping("/save/itemalternative")
	public ResponseEntity<BaseDto<ItemAlternativeModel>> insertItemAlternativeData(@Valid @RequestBody AlternativeItemsRequestDTO alternativeItemsRequestDTO) {
		log.info("Request Object insert is: "+alternativeItemsRequestDTO);
		List<ItemsModel> itemAlternativeModels = alternativeItemsRequestDTO.getItemAlternativeModels();
		ItemsModel item = alternativeItemsRequestDTO.getItem();
		ItemAlternativeModel itemAlternativeRes = itemAlternativeService.saveItemAlternative(itemAlternativeModels, item);
		log.info(itemAlternativeHelper.getSaveItemAlternativeMessage());
		return new BaseDto<>(itemAlternativeRes,itemAlternativeHelper.getSaveItemAlternativeMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemalternative")
	public ResponseEntity<BaseDto<ItemAlternativeModel>> updateItemAlternativeData(@Valid @RequestBody List<ItemAlternativeModel> itemAlternativeModels) {
		log.info("Request Object for update is: "+ itemAlternativeModels.toString());
		ItemAlternativeModel itemAlternativeRes = itemAlternativeService.updateItemAlternative(itemAlternativeModels);
		log.info(itemAlternativeHelper.getUpdateItemAlternativeMessage());
		return new BaseDto<>(itemAlternativeRes,itemAlternativeHelper.getUpdateItemAlternativeMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemalternative")
	public ResponseEntity<BaseDto<Object>> deleteItemAlternativeData(@RequestParam int itemAlternativeId) {
		log.info("Request Object for delete is: ", itemAlternativeId);
		itemAlternativeService.delete(itemAlternativeId);
		return new BaseDto<>(itemAlternativeHelper.getDeleteItemAlternativeMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemalternativedata")
	public ResponseEntity<BaseDto<List<ItemAlternativeModel>>> getAllItemAlternativeData() {
		List<ItemAlternativeModel> result = itemAlternativeService.findAll();
		return new BaseDto<>(result, itemAlternativeHelper.getRetrieveItemAlternativeMessage(), OK).respond();
	}
	
	@GetMapping("/getitemalternativedatabyid")
	public ResponseEntity<BaseDto<ItemAlternativeModel>> getItemAlternativeDataById(@RequestParam int itemAlternativeId) {
		ItemAlternativeModel result = itemAlternativeService.findByItemAlternativeId(itemAlternativeId);
		return new BaseDto<>(result,  itemAlternativeHelper.getRetrieveItemAlternativeMessage(), OK).respond();
	}
	
	@PostMapping("/getitemalternativedatabyitemid")
	public ResponseEntity<BaseDto<List<ItemAlternativeModel>>> getItemAlternativeDataByEmployeeId(@RequestBody ItemsModel item) {
		List<ItemAlternativeModel> result = itemAlternativeService.findByItemId(item);
		return new BaseDto<>(result,  itemAlternativeHelper.getRetrieveItemAlternativeMessage(), OK).respond();
	}
	
	@PutMapping("/updateItemAlternativebasedonItem")
	public ResponseEntity<BaseDto<Object>> postItemAlternativeBasedOnItem(@Valid @RequestBody AlternativeItemsRequestDTO alternativeItemsRequestDTO){
		log.info("Request Object for update is: "+ alternativeItemsRequestDTO.toString());
		List<ItemsModel> itemAlternativeModels = alternativeItemsRequestDTO.getItemAlternativeModels();
		ItemsModel item = alternativeItemsRequestDTO.getItem();
		  itemAlternativeService.updateItemAlternativeBasedOnItemModel(itemAlternativeModels,item);
		log.info(itemAlternativeHelper.getUpdateItemAlternativeMessage());
		return new BaseDto<>(itemAlternativeHelper.getUpdateItemAlternativeMessage(),OK).respond();
		
	}
	
	
}
