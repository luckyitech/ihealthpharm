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
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.helper.ItemSupplierHelper;
import com.ihealthpharm.masters.model.ItemSupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.ItemSupplierService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 * All the related API's available here..
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class ItemSupplierController {
	
	@Autowired
	private ItemSupplierService itemSupplierService;
	
	@Autowired
	private ItemSupplierHelper itemSupplierHelper;
	
	
/*	@PostMapping("/save/itemdistributor")
	public ResponseEntity<BaseDto<ItemDistributorModel>> insertDistributorData(@Valid @RequestParam int[] itemsId,@Valid @RequestParam int[] distributorsId ) {
		log.info("Request Object insert is: "+ itemsId+distributorsId);
		
		ItemDistributorModel itemDistributorModelRes = itemDistributorService.saveItemDistributorData(itemsId,distributorsId);
		return new BaseDto<>(itemDistributorModelRes,itemDistributorHelper.getSaveItemDistributorMessage(),OK).respond();
	}*/
	
	@PostMapping("/save/itemsupplier")
	public ResponseEntity<BaseDto<ItemSupplierModel>> insertSupplierData(@Valid @RequestBody ItemSupplierModel itemSupplierModel ) {
		log.info("request :"+itemSupplierModel);
		ItemSupplierModel itemSupplierModelRes = itemSupplierService.saveItemSupplierDataModel(itemSupplierModel);
		return new BaseDto<>(itemSupplierModelRes,itemSupplierHelper.getSaveItemSupplierMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/itemSuppliers")
	public ResponseEntity<BaseDto<Object>> updateItemSupplierData(@Valid @RequestParam int itemSupplierId,@Valid @RequestParam String activeS){
		
		log.info("Request Object for update :"+itemSupplierId+"recieved status:"+activeS);
		itemSupplierService.saveItemSuppliersById(itemSupplierId,activeS);
		return new BaseDto<>(itemSupplierHelper.getUpdateItemSupplierMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/itemsupplier")
	public ResponseEntity<BaseDto<ItemSupplierModel>> updateSupplierData(@Valid @RequestBody ItemSupplierModel itemSupplierModel) {
		log.info("Request Object for update is: ",itemSupplierModel);
		ItemSupplierModel itemSupplierModelRes = itemSupplierService.updateItemSupplierData(itemSupplierModel);
		return new BaseDto<>(itemSupplierModelRes,itemSupplierHelper.getUpdateItemSupplierMessage(),OK).respond();
	}

	@PutMapping("/update/itemsuppliers")
	public ResponseEntity<BaseDto<List<ItemSupplierModel>>> updateSuppliersData(@Valid @RequestBody List<ItemSupplierModel> itemSupplierModels) {
		log.info("Request Object for update is: "+ itemSupplierModels);
		List<ItemSupplierModel> itemSupplierModelRes = itemSupplierService.updateItemSuppliersData(itemSupplierModels);
		return new BaseDto<>(itemSupplierModelRes,itemSupplierHelper.getUpdateItemSupplierMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemsupplier")
	public ResponseEntity<BaseDto<Object>> deleteSupplierData(@RequestParam int itemSupplierId) {
		log.info("Request Object for delete is: ", itemSupplierId);
		itemSupplierService.deleteItemSupplierById(itemSupplierId);
		return new BaseDto<>(itemSupplierHelper.getDeleteItemSupplierMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/itemsuppliers")
	public ResponseEntity<BaseDto<Object>> deleteSuppliersData(@RequestParam int[] itemsupplierIds) {
		log.info("Request Object for delete is: "+ itemsupplierIds);
		itemSupplierService.deleteMultipleItemSuppliersById(itemsupplierIds);
		return new BaseDto<>(itemSupplierHelper.getDeleteItemSupplierMessage(), OK).respond();
	}
	
	

	@GetMapping("/getactiveitemsuppliersdata")
	public ResponseEntity<BaseDto<List<ItemSupplierModel>>> getSuppliersdata() {
		List<ItemSupplierModel> result = itemSupplierService.findItemSupplierByActive();
		return new BaseDto<>(result, itemSupplierHelper.getRetrieveItemSupplierMessage(), OK).respond();
	}
	
	@GetMapping("/getitemsupplierdatabyid")
	public ResponseEntity<BaseDto<ItemSupplierModel>> getSupplierDataById(@RequestParam int itemsupplierId) {
		ItemSupplierModel result = itemSupplierService.findItemSupplierById(itemsupplierId);
		return new BaseDto<>(result, itemSupplierHelper.getRetrieveItemSupplierMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemsuppliers")
	public ResponseEntity<BaseDto<List<ItemSupplierModel>>> getAllItemSuppliers(){
		List<ItemSupplierModel> response=itemSupplierService.findAllItemSuppliers();
		return new BaseDto<>(response,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	
	
	//itemsupplier dropdown item search
	
	@GetMapping("/getunmapped/suppliers")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getAllSuppliers(@RequestParam int itemId){
		List<SupplierModel> response=itemSupplierService.findAllUnMappedItemSuppliersData(itemId);
		return new BaseDto<>(response,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	
	//ItemSupplier dropdown search for unmapped suppliers
	
	@GetMapping("/getunmapped/supplierssearch/byName")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getAllSuppliersUnmappedSearch(@RequestParam int itemId,@RequestParam String searchTerm){
		List<SupplierModel> response=itemSupplierService.findAllUnmappedSuppliersNamesSearch(itemId,searchTerm);
		return new BaseDto<>(response,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	//ItemSuppliers dropdown search for unmapped items
	@GetMapping("/getunmapped/itemssearch/byitemName")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItemsUnmappedSearch(@RequestParam int supplierId,@RequestParam String searchTerm){
		log.info("searchTerm :"+searchTerm +"distId :"+supplierId);
		List<ItemsModel> result=itemSupplierService.finAllUnmppedItemsNameSearch(supplierId,searchTerm);
		return new BaseDto<>(result,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	
	//supplierItem dropdown search
	
	@GetMapping("/getunmapped/items")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllItems(@RequestParam int supplierId){
		List<ItemsModel> result=itemSupplierService.findAllUnMappedSupplierItems(supplierId);
		return new BaseDto<>(result,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	//Grid Options ItemSuppliers mapped data
	@GetMapping("/getitemsuppliers/mapppeddata")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getAllItemSuppliersMapped(){
		List<ItemSupplierDTO> result=itemSupplierService.findAllMappedItemSuppliers();
		return new BaseDto<>(result,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	//for getting grid data based on itemId
	@GetMapping("/getitemsuppliers/basedonItemId")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getAllItemSuppliersBasedOnItemId(@RequestParam int  itemId){
		List<ItemSupplierDTO> response=itemSupplierService.findAllMappedItemSuppliersOnItemName(itemId);
		return new BaseDto<>(response,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	
	@GetMapping("/getitemsuppliers/basedonSupplierId")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getAllSupplierItemsBasedonSupplierId(@RequestParam int supplierId){
		List<ItemSupplierDTO> result=itemSupplierService.findAllSupplierItemOnSupplierId(supplierId);
		return new BaseDto<>(result,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
	 

	@GetMapping("/getitemsuppliersbyitemid")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getAllSuppliersItemId(@RequestParam int itemId){
		List<SupplierModel> result=itemSupplierService.getAllSuppliersByItemId(itemId);
		log.info("---------------------------------");
		log.info(result.toString());
		log.info("---------------------------------");
		return new BaseDto<>(result,itemSupplierHelper.getRetrieveItemSupplierMessage(),OK).respond();
	}
}
