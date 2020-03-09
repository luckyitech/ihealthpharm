package com.ihealthpharm.sales.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.sales.helper.ItemInstructionsHelper;
import com.ihealthpharm.sales.model.ItemInstructionsModel;
import com.ihealthpharm.sales.service.ItemInstructionsService;

@RestController
@CrossOrigin
public class ItemInstructionsController {
	
	@Autowired
	private ItemInstructionsService itemInstrService;
	
	@Autowired
	private ItemInstructionsHelper itemInstructionsHelper;
	
	@GetMapping("/getall/iteminstructions")
	public ResponseEntity<BaseDto<List<ItemInstructionsModel>>> getAllInstructions(){
		List<ItemInstructionsModel> response=itemInstrService.getAllData();
		return new BaseDto<>(response,itemInstructionsHelper.getRetrieveItemsInstructionsMessage(),OK).respond();
	}
	
	@PostMapping("/save/iteminstructions")
	public ResponseEntity<BaseDto<List<ItemInstructionsModel>>> saveInstructionsData(@Valid @RequestBody List<ItemInstructionsModel> itemInstructionsModel ){
		System.out.println(itemInstructionsModel.toString());
		List<ItemInstructionsModel> result=itemInstrService.saveInstructionsData(itemInstructionsModel);
		return new BaseDto<>(result,itemInstructionsHelper.getSaveItemsInstructionsMessage(),OK).respond();
	}

}
