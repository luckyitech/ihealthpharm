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
import com.ihealthpharm.masters.helper.ItemDistributorHelper;
import com.ihealthpharm.masters.model.ItemDistributorModel;
import com.ihealthpharm.masters.service.ItemDistributorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ItemDistributorController {
	
	@Autowired
	private ItemDistributorService itemDistributorService;
	
	@Autowired
	private ItemDistributorHelper itemDistributorHelper;
	
	
	@PostMapping("/save/itemdistributor")
	public ResponseEntity<BaseDto<ItemDistributorModel>> insertDistributorData(@Valid @RequestParam int[] itemsId,@Valid @RequestParam int[] distributorsId ) {
		log.info("Request Object insert is: "+ itemsId+distributorsId);
		
		ItemDistributorModel itemDistributorModelRes = itemDistributorService.saveItemDistributorData(itemsId,distributorsId);
		return new BaseDto<>(itemDistributorModelRes,itemDistributorHelper.getSaveItemDistributorMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemdistributor")
	public ResponseEntity<BaseDto<ItemDistributorModel>> updateDistributorData(@Valid @RequestBody ItemDistributorModel itemDistributorModel) {
		log.info("Request Object for update is: ",itemDistributorModel);
		ItemDistributorModel itemDistributorModelRes = itemDistributorService.updateItemDistributorData(itemDistributorModel);
		return new BaseDto<>(itemDistributorModelRes,itemDistributorHelper.getUpdateItemDistributorMessage(),OK).respond();
	}

	@PutMapping("/update/itemdistributors")
	public ResponseEntity<BaseDto<List<ItemDistributorModel>>> updateDistributorsData(@Valid @RequestBody List<ItemDistributorModel> itemDistributorModels) {
		log.info("Request Object for update is: "+ itemDistributorModels);
		List<ItemDistributorModel> itemDistributorModelRes = itemDistributorService.updateItemDistributorsData(itemDistributorModels);
		return new BaseDto<>(itemDistributorModelRes,itemDistributorHelper.getUpdateItemDistributorMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemdistributor")
	public ResponseEntity<BaseDto<Object>> deleteDistributorData(@RequestParam int itemdistributorId) {
		log.info("Request Object for delete is: ", itemdistributorId);
		itemDistributorService.deleteItemDistributorById(itemdistributorId);
		return new BaseDto<>(itemDistributorHelper.getDeleteItemDistributorMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/itemdistributors")
	public ResponseEntity<BaseDto<Object>> deleteDistributorsData(@RequestParam int[] itemdistributorIds) {
		log.info("Request Object for delete is: "+ itemdistributorIds);
		itemDistributorService.deleteMultipleItemDistributorsById(itemdistributorIds);
		return new BaseDto<>(itemDistributorHelper.getDeleteItemDistributorMessage(), OK).respond();
	}
	
	

	@GetMapping("/getactiveitemdistributorsdata")
	public ResponseEntity<BaseDto<List<ItemDistributorModel>>> getDistributorsdata() {
		List<ItemDistributorModel> result = itemDistributorService.findItemDistributorByActive();
		return new BaseDto<>(result, itemDistributorHelper.getRetrieveItemDistributorMessage(), OK).respond();
	}
	
	@GetMapping("/getitemdistributordatabyid")
	public ResponseEntity<BaseDto<ItemDistributorModel>> getDistributorDataById(@RequestParam int itemdistributorId) {
		ItemDistributorModel result = itemDistributorService.findItemDistributorById(itemdistributorId);
		return new BaseDto<>(result, itemDistributorHelper.getRetrieveItemDistributorMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemdistributors")
	public ResponseEntity<BaseDto<List<ItemDistributorModel>>> getAllItemDistributors(){
		List<ItemDistributorModel> response=itemDistributorService.findAllItemDistributors();
		return new BaseDto<>(response,itemDistributorHelper.getRetrieveItemDistributorMessage(),OK).respond();
	}
	
	 

}
