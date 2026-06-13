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
import com.ihealthpharm.masters.helper.ItemFormsHelper;
import com.ihealthpharm.masters.model.ItemFormModel;
import com.ihealthpharm.masters.service.ItemFormService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Tarun
 *
 */

@RestController
@Slf4j
@CrossOrigin
public class ItemFormsController {
	
	@Autowired
	private ItemFormService itemFormService;
	
	@Autowired
	private ItemFormsHelper itemFormsHelper;
	
	@PostMapping("/save/itemform")
	public ResponseEntity<BaseDto<ItemFormModel>> insertFormData(@Valid @RequestBody ItemFormModel itemFormModel) {
		log.info("Request Object insert is: "+ itemFormModel);
		ItemFormModel itemFormModelRes = itemFormService.saveItemFormData(itemFormModel);
		return new BaseDto<>(itemFormModelRes,itemFormsHelper.getSaveItemFormsMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemform")
	public ResponseEntity<BaseDto<ItemFormModel>> updateFormData(@Valid @RequestBody ItemFormModel itemFormModel) {
		log.info("Request Object for update is: ",itemFormModel);
		ItemFormModel itemFormModelRes=itemFormService.updateItemFormData(itemFormModel);
		return new BaseDto<>(itemFormModelRes,itemFormsHelper.getUpdateItemFormsMessage(),OK).respond();
	}
	
	@PutMapping("/update/itemforms")
	public ResponseEntity<BaseDto<List<ItemFormModel>>> updateFormsData(@Valid @RequestBody List<ItemFormModel> itemFormsModels) {
		log.info("Request Object for update is: "+ itemFormsModels);
		List<ItemFormModel> itemFormModelRes=itemFormService.updateItemFormsData(itemFormsModels);
		return new BaseDto<>(itemFormModelRes,itemFormsHelper.getUpdateItemFormsMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/itemform")
	public ResponseEntity<BaseDto<Object>> deleteFormData(@RequestParam Integer itemformId) {
		log.info("Request Object for delete is: ", itemformId);
		itemFormService.deleteItemFormById(itemformId);
		return new BaseDto<>(itemFormsHelper.getDeleteItemFormsMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/itemforms")
	public ResponseEntity<BaseDto<Object>> deleteFormsData(@RequestParam Integer[] itemformIds) {
		log.info("Request Object for delete is: "+ itemformIds);
		itemFormService.deleteMultipleItemFormsById(itemformIds);
		return new BaseDto<>(itemFormsHelper.getDeleteItemFormsMessage(), OK).respond();
	}
	
	@GetMapping("/getactiveitemformsdata")
	public ResponseEntity<BaseDto<List<ItemFormModel>>> getActiveItemFormsdata() {
		List<ItemFormModel> result = itemFormService.findItemFormByActive();
		return new BaseDto<>(result, itemFormsHelper.getRetrieveItemFormsMessage(), OK).respond();
	}
	
	@GetMapping("/geitemformdatabyid")
	public ResponseEntity<BaseDto<ItemFormModel>> getItemFormsDataById(@RequestParam Integer itemformIds) {
		ItemFormModel result = itemFormService.findItemFormById(itemformIds);
		return new BaseDto<>(result, itemFormsHelper.getRetrieveItemFormsMessage(), OK).respond();
	}
	
	@GetMapping("/getitemformsbysearch")
	public ResponseEntity<BaseDto<List<ItemFormModel>>> getActiveItemForms(@RequestParam String medicalOrNonMedical,@RequestParam String searchTerm) {
		List<ItemFormModel> result = itemFormService.findAllItemFormsData(medicalOrNonMedical, searchTerm);
		return new BaseDto<>(result, itemFormsHelper.getRetrieveItemFormsMessage(), OK).respond();
	}
	
	@GetMapping("/getallitemforms")
	public ResponseEntity<BaseDto<List<ItemFormModel>>> getAllItemFormsdata() {
		List<ItemFormModel> result = itemFormService.findAllItemForms();
		return new BaseDto<>(result, itemFormsHelper.getRetrieveItemFormsMessage(), OK).respond();
	}

}
