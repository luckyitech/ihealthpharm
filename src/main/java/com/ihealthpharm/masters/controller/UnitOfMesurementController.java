package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.ItemUOMHelper;
import com.ihealthpharm.masters.model.UnitOfMeasurementModel;
import com.ihealthpharm.masters.service.UnitOfMessurementService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vikas
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class UnitOfMesurementController {
	
	
	@Autowired
	private UnitOfMessurementService uomService;
	
	@Autowired
	private ItemUOMHelper uomHelper;
	
	@PostMapping("/uom/save")
	public ResponseEntity<BaseDto<UnitOfMeasurementModel>> saveUOM(@Valid @RequestBody UnitOfMeasurementModel unitOfMeasurementModel) {
		log.info("Request Object insert is: "+ unitOfMeasurementModel);
		UnitOfMeasurementModel response = uomService.save(unitOfMeasurementModel);
		return new BaseDto<UnitOfMeasurementModel>(response,uomHelper.getSaveUomMessage(), OK).respond();
	}
	
	@PutMapping("/uom/update")
	public ResponseEntity<BaseDto<UnitOfMeasurementModel>> updateUOM(@Valid @RequestBody UnitOfMeasurementModel unitOfMeasurementModel) {
		log.info("Request Object for update is: ",unitOfMeasurementModel);
		UnitOfMeasurementModel response = uomService.update(unitOfMeasurementModel);
		return new BaseDto<UnitOfMeasurementModel>(response,uomHelper.getUpdateUomMessage(), OK).respond();
	}
	
	@GetMapping("uom/find/by/id")
	public ResponseEntity<BaseDto<UnitOfMeasurementModel>> getUOMById(@RequestParam int id){
		log.info("Finding UOM Obj with ID : ",id);
		UnitOfMeasurementModel response = uomService.findById(id);
		return new BaseDto<UnitOfMeasurementModel>(response,uomHelper.getRetrieveUomMessage(), OK).respond();
	}
	
	
	@GetMapping("uom/findAll")
	public ResponseEntity<BaseDto<List<UnitOfMeasurementModel>>> findAllUOM(){
		List<UnitOfMeasurementModel> response = uomService.findAll();
		log.info("All uom data size is : ",response.size());
		return new BaseDto<List<UnitOfMeasurementModel>>(response,uomHelper.getRetrieveUomMessage(), OK).respond();
	}
	
	@GetMapping("uom/findby/staus")
	public ResponseEntity<BaseDto<List<UnitOfMeasurementModel>>> findByActiveStatus(){
		List<UnitOfMeasurementModel> response = uomService.findbyActiveS();
		log.info("All uom data size is : ",response.size());
		return new BaseDto<List<UnitOfMeasurementModel>>(response,uomHelper.getRetrieveUomMessage(), OK).respond();
	}
	
	@GetMapping("uom/delete/ids")
	public ResponseEntity<BaseDto<String>> deleteUOMs(@RequestParam int[] ids){
		 uomService.remove(ids);
		log.info("UOM ids deleted: ",ids);
		return new BaseDto<String>(uomHelper.getDeleteUomMessage(), OK).respond();
	}
	
	@GetMapping("uom/findAll/measurements")
	public ResponseEntity<BaseDto<List<UnitOfMeasurementModel>>> findAllUnitOfMeasurements(){
		
		List<UnitOfMeasurementModel> result=uomService.findAllMeasurements();
      return new BaseDto<>(result,uomHelper.getRetrieveUomMessage(),OK).respond();
	}
	
	
	
	

}
