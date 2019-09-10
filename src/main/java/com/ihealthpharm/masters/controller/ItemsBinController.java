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
import com.ihealthpharm.masters.helper.ItemsBinHelper;
import com.ihealthpharm.masters.model.ItemsBinModel;
import com.ihealthpharm.masters.service.ItemsBinService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ItemsBinController {

	@Autowired
	private ItemsBinService itemsBinService;
	
	@Autowired
	private ItemsBinHelper binHelper;
	
	@PostMapping("/save/itemsbin")
	public ResponseEntity<BaseDto<ItemsBinModel>> insertItemsBinData(@Valid @RequestBody ItemsBinModel itemsBinModel) {
		log.info("Request Object insert is: "+ itemsBinModel);
		
		ItemsBinModel itemsBinModelRes = itemsBinService.saveItemsBinData(itemsBinModel);
		return new BaseDto<>(itemsBinModelRes,binHelper.getSaveItemsBinMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemsbin")
	public ResponseEntity<BaseDto<ItemsBinModel>> updateItemsBinData(@Valid @RequestBody ItemsBinModel itemsBinModel) {
		log.info("Request Object for update is: ",itemsBinModel);
		ItemsBinModel binModelRes = itemsBinService.updateItemsBinData(itemsBinModel);
		return new BaseDto<>(binModelRes,binHelper.getUpdateItemsBinMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/itemsbins")
	public ResponseEntity<BaseDto<List<ItemsBinModel>>> updateItemsBinsData( @RequestBody List<ItemsBinModel> itemBinModels) {
		log.info("Request Object for update is: "+ itemBinModels);
		List<ItemsBinModel> binRes = itemsBinService.updateItemsBinsData(itemBinModels);
		return new BaseDto<>(binRes,binHelper.getUpdateItemsBinMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemsbinbyid")
	public ResponseEntity<BaseDto<Object>> deleteItemsBinData(@RequestParam int itemBinId) {
		log.info("Request Object for delete is: ", itemBinId);
		itemsBinService.deleteItemsBinById(itemBinId);
		return new BaseDto<>(binHelper.getDeleteItemsBinMessage(), OK).respond();
	}
	
	
	@DeleteMapping("/delete/itemsbins")
	public ResponseEntity<BaseDto<Object>> deleteItemsBinsData(@RequestParam int[] itemBinIds) {
		log.info("Request Object for delete is: "+ itemBinIds.toString());
		itemsBinService.deleteMultipleItemBinsById(itemBinIds);
		return new BaseDto<>(binHelper.getDeleteItemsBinMessage(), OK).respond();
	}
	
	
	@GetMapping("/getbinsdatabyid")
	public ResponseEntity<BaseDto<ItemsBinModel>> getItemsBinDataById(@RequestParam int itemBinId) {
		ItemsBinModel result = itemsBinService.findItemsBinById(itemBinId);
		return new BaseDto<>(result, binHelper.getRetrieveItemsBinMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallitembins")
	public ResponseEntity<BaseDto<List<ItemsBinModel>>> getAllItemBinsData(){
		
		List<ItemsBinModel> result=itemsBinService.findAllBinItems();
		return new BaseDto<>(result,binHelper.getRetrieveItemsBinMessage(),OK).respond();
	}
	

	
	
}
