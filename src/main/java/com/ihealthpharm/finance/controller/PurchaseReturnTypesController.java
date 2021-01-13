package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.model.PurchaseReturnTypesModel;
import com.ihealthpharm.finance.service.PurchaseReturnTypesService;

@RestController
@CrossOrigin
public class PurchaseReturnTypesController {
	
	@Autowired
	private PurchaseReturnTypesService prTypesService;
	
	@GetMapping("/getAll/purchaseReturnTypes")
	public ResponseEntity<BaseDto<List<PurchaseReturnTypesModel>>> getAllPurchaseReturnTypes() {
		List<PurchaseReturnTypesModel> prTypes = prTypesService.getAllPrTypes();
		return new BaseDto<>(prTypes,"Recieved Purchase Return Types SuccessFully",OK).respond();
	}

}
