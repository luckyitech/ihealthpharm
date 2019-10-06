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
import com.ihealthpharm.masters.helper.ItemGroupHelper;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.service.ItemGroupService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ItemGroupController {

	@Autowired
	private ItemGroupService itemGroupService;
	
	@Autowired
	private ItemGroupHelper itemGroupHelper;
	
	@PostMapping("/save/itemgroup")
	public ResponseEntity<BaseDto<ItemGroupModel>> insertItemGroupData(@Valid @RequestBody ItemGroupModel itemGroupModel) {
		log.info("Request Object insert is: "+ itemGroupModel);
		ItemGroupModel itemGroupModelRes = itemGroupService.saveItemGroupData(itemGroupModel);
		return new BaseDto<>(itemGroupModelRes,itemGroupHelper.getSaveItemGroupMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemgroup")
	public ResponseEntity<BaseDto<ItemGroupModel>> updateItemGroupData(@Valid @RequestBody ItemGroupModel itemGroupModel) {
		log.info("Request Object for update is: ",itemGroupModel);
		ItemGroupModel itemModelRes = itemGroupService.updateItemGroupData(itemGroupModel);
		return new BaseDto<>(itemModelRes,itemGroupHelper.getUpdateItemGroupMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/itemgroups")
	public ResponseEntity<BaseDto<List<ItemGroupModel>>> updateItemsGroupData(@Valid @RequestBody List<ItemGroupModel> itemGroupModels) {
		log.info("Request Object for update is: "+ itemGroupModels);
		List<ItemGroupModel> itemRes = itemGroupService.updateItemGroupsData(itemGroupModels);
		return new BaseDto<>(itemRes,itemGroupHelper.getUpdateItemGroupMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemgroup")
	public ResponseEntity<BaseDto<Object>> deleteItemGroupData(@RequestParam int itemGroupId) {
		log.info("Request Object for delete is: ", itemGroupId);
		itemGroupService.deleteItemGroupById(itemGroupId);
		return new BaseDto<>(itemGroupHelper.getDeleteItemGroupMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/itemgroups")
	public ResponseEntity<BaseDto<Object>> deleteItemsGroupData(@RequestParam int[] itemGroupIds) {
		log.info("Request Object for delete is: "+ itemGroupIds);
		itemGroupService.deleteMultipleItemGroupsById(itemGroupIds);
		return new BaseDto<>(itemGroupHelper.getDeleteItemGroupMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallitemgroupdata")
	public ResponseEntity<BaseDto<List<ItemGroupModel>>> getAllItemdata() {
		List<ItemGroupModel> result = itemGroupService.findAllItemGroups();
		return new BaseDto<>(result, itemGroupHelper.getRetrieveItemGroupMessage(), OK).respond();
	}

	@GetMapping("/getactiveitemgroupsdata")
	public ResponseEntity<BaseDto<List<ItemGroupModel>>> getItemGroupdata() {
		List<ItemGroupModel> result = itemGroupService.findItemGroupByActive();
		return new BaseDto<>(result, itemGroupHelper.getRetrieveItemGroupMessage(), OK).respond();
	}
	
	@GetMapping("/getitemgroupdatabyid")
	public ResponseEntity<BaseDto<ItemGroupModel>> getItemGroupDataById(@RequestParam int itemGroupId) {
		ItemGroupModel result = itemGroupService.findItemGroupById(itemGroupId);
		return new BaseDto<>(result, itemGroupHelper.getRetrieveItemGroupMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemgroupsdata")
	public ResponseEntity<BaseDto<List<ItemGroupModel>>> getAllItemGroupdata(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm) {
		log.info(medicalOrNonMedical + " "+searchTerm);
		List<ItemGroupModel> result = itemGroupService.findAllItemGroupData(medicalOrNonMedical, searchTerm);
		log.info(result.toString());
		return new BaseDto<>(result, itemGroupHelper.getRetrieveItemGroupMessage(), OK).respond();
	}
	
	
}
