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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 * All the drug related API's available here..
 *
 */
@RestController
@RequestMapping("/item")
@CrossOrigin
@Slf4j
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemPropertyHelper propertyHelper;


	@PostMapping("/save/item")
	public ResponseEntity<BaseDto<ItemsModel>> insertItemData(@Valid @RequestBody ItemsModel itemsModel) {
		log.info("Request Object insert is: "+ itemsModel);

		ItemsModel itemModelRes = itemService.saveItemsData(itemsModel);
		return new BaseDto<>(itemModelRes,propertyHelper.getSaveMessage(),OK).respond();
	}


	@PutMapping("/update/item")
	public ResponseEntity<BaseDto<ItemsModel>> updateItemData(@Valid @RequestBody ItemsModel itemsModel) {
		log.info("Request Object for update is: ",itemsModel);
		ItemsModel itemModelRes =itemService.updateItemData(itemsModel); 
		return new BaseDto<>(itemModelRes,propertyHelper.getUpdateMessage(),OK).respond();
	}


	@PutMapping("/update/items")
	public ResponseEntity<BaseDto<List<ItemsModel>>> updateItemsData(@Valid @RequestBody List<ItemsModel> itemsModels) {
		log.info("Request Object for update is: "+ itemsModels);
		List<ItemsModel> itemRes =itemService.updateItemsData(itemsModels);
		return new BaseDto<>(itemRes,propertyHelper.getUpdateMessage(),OK).respond();
	}



	@DeleteMapping("/delete/item")
	public ResponseEntity<BaseDto<Object>> deleteItemData(@RequestParam int itemId) {
		log.info("Request Object for delete is: ", itemId);
		itemService.deleteItemsById(itemId);
		return new BaseDto<>(propertyHelper.getDeleteMessage(), OK).respond();
	}


	@DeleteMapping("/delete/items")
	public ResponseEntity<BaseDto<Object>> deleteItemsData(@RequestParam int[] itemIds) {
		log.info("Request Object for delete is: "+ itemIds);
		itemService.deleteMultipleItemsById(itemIds);
		return new BaseDto<>(propertyHelper.getDeleteMessage(), OK).respond();
	}


	@GetMapping("/getactiveitemsdata")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getItemdata() {
		List<ItemsModel> result = itemService.findItemsByActive();
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	@GetMapping("/getallitemsdata")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemdata() {
		List<ItemsModel> result = itemService.findAllItems();
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	@GetMapping("/getitemdatabyid")
	public ResponseEntity<BaseDto<ItemsModel>> getItemDataById(@RequestParam int itemId) {
		ItemsModel result = itemService.findItemsById(itemId); 
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}


	///////////////////////////////////////////////////////////
	//based on medical search and itemName

	@GetMapping("/getallby/MedicalandItemnameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemName(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm){

		List<ItemsModel> results=itemService.findAllByMedicalAndItemName(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(results,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	//based on medical and itemDesc
	@GetMapping("/getallby/MedicalAndItemDescSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllMedicalAndItemDesc(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm){

		List<ItemsModel> result=itemService.findAllByMedicalAndItemDesc(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond(); 
	}

	///////////////////////////////////////////////////////////




	//based on itemName search
	@GetMapping("/getallby/ItemNameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemNameSearch(@RequestParam String searchTerm){
		System.out.println("in itemname search");
		List<ItemsModel> results=itemService.findAllByItemName(searchTerm);
		System.out.println(results);
		return new BaseDto<>(results,propertyHelper.getRetrieveMessage(),OK).respond();
	}



	//based on itemDescription
	@GetMapping("/getallby/ItemDescriptionSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsByItemDesc(@RequestParam String searchTerm){

		List<ItemsModel> result=itemService.findAllByItemDescription(searchTerm);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	//based on itemgeneric name
	@GetMapping("/getallby/genericnameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsBySearchGenericName(@RequestParam String searchTerm){
		List<ItemsModel> result=itemService.findAllGerericNamesBySearch(searchTerm);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	//based on groupcode
	@GetMapping("/getallby/groupcodeSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsByGroupCodeSearch(@RequestParam String searchTerm){

		System.out.println("in groupcode search controller");
		System.out.println(searchTerm);
		List<ItemsModel> response=itemService.findAllByItemGroupCodeSearch(searchTerm);
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}



}
