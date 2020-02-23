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
import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemService;
import com.ihealthpharm.stock.dto.StockAdjustmentItemDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun All the drug related API's available here..
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
		log.info("Request Object insert is: " + itemsModel);
		ItemsModel itemModelRes = itemService.saveItemsData(itemsModel);
		return new BaseDto<>(itemModelRes, propertyHelper.getSaveMessage(), OK).respond();
	}

	@PutMapping("/update/item")
	public ResponseEntity<BaseDto<ItemsModel>> updateItemData(@Valid @RequestBody ItemsModel itemsModel) {
		log.info("Request Object for update is: ", itemsModel);
		ItemsModel itemModelRes = itemService.updateItemData(itemsModel);
		return new BaseDto<>(itemModelRes, propertyHelper.getUpdateMessage(), OK).respond();
	}

	@PutMapping("/update/items")
	public ResponseEntity<BaseDto<List<ItemsModel>>> updateItemsData(@Valid @RequestBody List<ItemsModel> itemsModels) {
		log.info("Request Object for update is: " + itemsModels);
		List<ItemsModel> itemRes = itemService.updateItemsData(itemsModels);
		return new BaseDto<>(itemRes, propertyHelper.getUpdateMessage(), OK).respond();
	}

	@DeleteMapping("/delete/item")
	public ResponseEntity<BaseDto<Object>> deleteItemData(@RequestParam Integer itemId) {
		log.info("Request Object for delete is: ", itemId);
		itemService.deleteItemsById(itemId);
		return new BaseDto<>(propertyHelper.getDeleteMessage(), OK).respond();
	}

	@DeleteMapping("/delete/items")
	public ResponseEntity<BaseDto<Object>> deleteItemsData(@RequestParam Integer[] itemIds) {
		log.info("Request Object for delete is: " + itemIds);
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
	public ResponseEntity<BaseDto<ItemsModel>> getItemDataById(@RequestParam Integer itemId) {
		ItemsModel result = itemService.findItemsById(itemId);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on medical search and itemName

	@GetMapping("/getallby/MedicalandItemnameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemName(@RequestParam String medicalOrNonMedical,
			@RequestParam String searchTerm) {

		List<ItemsModel> results = itemService.findAllByMedicalAndItemName(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(results, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on medical and itemDesc
	@GetMapping("/getallby/MedicalAndItemDescSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllMedicalAndItemDesc(@RequestParam String medicalOrNonMedical,
			@RequestParam String searchTerm) {
		List<ItemsModel> result = itemService.findAllByMedicalAndItemDesc(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on itemName search
	@GetMapping("/getallby/ItemNameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemNameSearch(@RequestParam String searchTerm) {
		List<ItemsModel> results = itemService.findAllByItemName(searchTerm);
		return new BaseDto<>(results, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	@GetMapping("/getallby/ItemNameSearch/foritemsupplier")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemNameSearchForItemSupplier(@RequestParam String searchTerm) {
		List<ItemsModel> results = itemService.findAllByItemNameForItemSupplier(searchTerm);
		return new BaseDto<>(results, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on itemCode search
	@GetMapping("/getallby/ItemCodeSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllByItemCodeSearch(@RequestParam String searchTerm) {
		List<ItemsModel> results = itemService.findAllByItemCode(searchTerm);
		return new BaseDto<>(results, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on itemDescription
	@GetMapping("/getallby/ItemDescriptionSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsByItemDesc(@RequestParam String searchTerm) {
		List<ItemsModel> result = itemService.findAllByItemDescription(searchTerm);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on itemgeneric name
	@GetMapping("/getallby/genericnameSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsBySearchGenericName(@RequestParam String searchTerm) {
		List<ItemsModel> result = itemService.findAllGerericNamesBySearch(searchTerm);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on groupcode
	@GetMapping("/getallby/groupcodeSearch")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsByGroupCodeSearch(@RequestParam String searchTerm) {
		List<ItemsModel> response = itemService.findAllByItemGroupCodeSearch(searchTerm);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	// based on search key
	@GetMapping("/getallby/searchkey")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsBySearchkey(@RequestParam String searchTerm) {
		log.info(searchTerm);
		List<ItemsModel> response = itemService.findBySearchKey(searchTerm);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	@GetMapping("/getallby/searchkeyandsearchcode")
	public ResponseEntity<BaseDto<List<ItemDTO>>> getAllItemsBySearchkeyAndCode(@RequestParam String searchTerm,@RequestParam String searchCode,
			@RequestParam Integer start,@RequestParam Integer end) {
		List<ItemDTO> response = itemService.findBySearchKey(searchTerm,searchCode,start,end);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	@GetMapping("/getlimiteditemdata")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getLimitetems() {
		List<ItemsModel> response = itemService.getLimitedItems();
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	@GetMapping("/getitemsdatabyname")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByName(@RequestParam("key") String itemName) {
		List<AlternativeItemDTO> result = itemService.findItemsByName(itemName);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
		//sdsdd
	}
	
	
	@GetMapping("/getitemsdatabyname/forstock")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByNameForStockAdjust(@RequestParam("key") String itemName){
		List<AlternativeItemDTO> results=itemService.findItemsByNameForStock(itemName);
		return new BaseDto<>(results, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	@GetMapping("/getitemsdatabycode")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByCode(@RequestParam("key")String itemCode){
		List<AlternativeItemDTO> result=itemService.findItemsByCode(itemCode);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	@GetMapping("/getitemsdatabycode/forstock")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByCodeForStockAdjust(@RequestParam("key")String itemCode){
		List<AlternativeItemDTO> result=itemService.findItemsByCodeForStock(itemCode);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	@GetMapping("/getitemsdatabygenericname")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByGeneric(@RequestParam("key")String itemGeneric){
		List<AlternativeItemDTO> result=itemService.findItemsByGenericName(itemGeneric);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	@GetMapping("/getitemsdatabygenericname/forstock")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByGenericForStockAdjust(@RequestParam("key")String itemGeneric){
		List<AlternativeItemDTO> result=itemService.findItemsByGenericNameForStock(itemGeneric);
		return new BaseDto<>(result,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	

	@GetMapping("/getitemsdatabydesc")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByDesc(@RequestParam("key")String itemdesc){
		List<AlternativeItemDTO> response=itemService.findItemsByDesc(itemdesc);
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	@GetMapping("/getitemsdatabydesc/forstock")
	public ResponseEntity<BaseDto<List<AlternativeItemDTO>>> getItemsDataByDescForStock(@RequestParam("key")String itemdesc){
		List<AlternativeItemDTO> response=itemService.findItemsByDescForStock(itemdesc);
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	//getitemdatabylimitForStockAdjust/Grid

	@GetMapping("/getitemdatabylimit")
	public ResponseEntity<BaseDto<List<ItemDTO>>> getItemDataByIdLimit(@RequestParam Integer start,@RequestParam Integer end) {
		List<ItemDTO> result = itemService.findItemsByLimit(start,end);
		log.info("Seiz: "+result.size());
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	@GetMapping("/getitemdatabylimit/withitemcode")
	public ResponseEntity<BaseDto<List<StockAdjustmentItemDTO>>> getItemDataByIdLimitWithItemCode(@RequestParam Integer start,@RequestParam Integer end) {
		List<StockAdjustmentItemDTO> response=itemService.findItemsByLimitWithItemCode(start,end);
		log.info("size :"+response.size());
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	@GetMapping("/getitemdatabylimit/withitemdesc")
	public ResponseEntity<BaseDto<List<StockAdjustmentItemDTO>>> getItemDataByLimitWithItemDesc(@RequestParam Integer start,@RequestParam Integer end) {
		List<StockAdjustmentItemDTO> resp=itemService.findItemsByLimitWithItemDesc(start,end);
        log.info("size :"+resp.size());
        return new BaseDto<>(resp,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	@GetMapping("/getitemdatabylimit/withitem/genericname")
	public ResponseEntity<BaseDto<List<StockAdjustmentItemDTO>>> getItemDataByLimitWithItemGenericName(@RequestParam Integer start,@RequestParam Integer end) {
		List<StockAdjustmentItemDTO> resp=itemService.findItemsByLimitWithItemGenericName(start,end);
        log.info("size :"+resp.size());
        return new BaseDto<>(resp,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	@GetMapping("/getitemsbyanysearch")
	public ResponseEntity<BaseDto<List<ItemDTO>>> getAllByItemSearches(@RequestParam String searchTerm){
		List<ItemDTO> response=itemService.findAllByItemsSearch(searchTerm);
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}

	@GetMapping("/getitemscountbysearch")
	public ResponseEntity<BaseDto<Integer>> getCountOfItemsBySearch(@RequestParam String searchTerm,@RequestParam String searchType){
		Integer response=itemService.findItemsCountBySearch(searchTerm,searchType);
		return new BaseDto<>(response,propertyHelper.getRetrieveMessage(),OK).respond();
	}
	
	/*@GetMapping("/getitemdataby/itemname/forStockAdjustement")
	public ResponseEntity<BaseDto<List<ItemsForStockAdjustDTO>>> getItemDataByForItemSearch(@RequestParam String searchTerm){
		List<ItemsForStockAdjustDTO> response=itemService.findItemsDataByItemName(searchTerm);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}*/
	
	@GetMapping("/getitemsdatabynamesearch/stockadjustGrid")
	public ResponseEntity<BaseDto<List<ItemsForStockAdjustDTO>>> getCustomersDataBySearchingName(@RequestParam("key")String itemName){
		List<ItemsForStockAdjustDTO> response=itemService.findItemsDataByItemName(itemName);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallstocks/forStockAdjustGrid")
	public ResponseEntity<BaseDto<List<ItemsForStockAdjustDTO>>> getAllStockAdjsutForGridRecords(){
		List<ItemsForStockAdjustDTO> response = itemService.getAllStockAdjustRecords();
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	@GetMapping("/getitemsdatabyrackandshelf/stockadjustGrid")
	public ResponseEntity<BaseDto<List<ItemsForStockAdjustDTO>>> getAllStockAdjust(@RequestParam String rack,@RequestParam String shelf){
		List<ItemsForStockAdjustDTO> response = itemService.getAllStockAdjustRecordBasedOnRackAndShelf(rack,shelf);
		return new BaseDto<>(response, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	

}
