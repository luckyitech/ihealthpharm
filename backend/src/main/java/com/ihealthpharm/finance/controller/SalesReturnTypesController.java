package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.model.SalesReturnTypesModel;
import com.ihealthpharm.finance.service.SalesReturnTypesService;

@RestController
@CrossOrigin
public class SalesReturnTypesController {
	
	@Autowired
	private SalesReturnTypesService srService;
	
	@GetMapping("/getAll/salesReturnTypes")
	public ResponseEntity<BaseDto<List<SalesReturnTypesModel>>> getAllSalesReturnTypes() {
		List<SalesReturnTypesModel> prTypes = srService.getAllPrTypes();
		return new BaseDto<>(prTypes,"Recieved Sales Return Types SuccessFully",OK).respond();
	}

}
