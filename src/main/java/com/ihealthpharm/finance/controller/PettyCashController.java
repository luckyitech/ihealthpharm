package com.ihealthpharm.finance.controller;
import static org.springframework.http.HttpStatus.OK;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.PettyCashHelper;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.PettyCashService;

@RestController
@CrossOrigin
public class PettyCashController {

	@Autowired
	private PettyCashService pettyCashService;
	
	@Autowired
	private PettyCashHelper pettyCashHelper;
	
	@GetMapping("/getall/pettycashdetails")
	public ResponseEntity<BaseDto<List<PettyCashModel>>> getAllPettyCash(){
		List<PettyCashModel> response=pettyCashService.findAllPettyCash();
		return new BaseDto<>(response, pettyCashHelper.getRetrivepettyCashMessage(), OK).respond();
	}
	@PostMapping("/save/pettycashdetails")
	public ResponseEntity<BaseDto<PettyCashModel>> insertPettyCashData(@Valid @RequestBody PettyCashModel pettyCashModel) {
		PettyCashModel pettyCashModelRes = pettyCashService.savepettyCashData(pettyCashModel);
		return new BaseDto<>(pettyCashModelRes, pettyCashHelper.getSavepettyCashMessage(), OK).respond();
	}
	
	@GetMapping("/getpettycashdetails/byid")
	public ResponseEntity<BaseDto<PettyCashModel>> getPettyCashById(@Valid @RequestParam Integer pettyCashID){
		PettyCashModel pettyCashRes= pettyCashService.findPettyCashById(pettyCashID);
		return new BaseDto<>(pettyCashRes,pettyCashHelper.getRetrivepettyCashMessage(),OK).respond();
	}
}
