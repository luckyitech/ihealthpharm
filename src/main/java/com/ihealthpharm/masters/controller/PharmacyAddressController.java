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
import com.ihealthpharm.masters.helper.PharmacyAddressHelper;
import com.ihealthpharm.masters.model.PharmacyAddressModel;
import com.ihealthpharm.masters.service.PharmacyAddressService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PharmacyAddressController {
	
	@Autowired
	private PharmacyAddressService pharmacyAddressService;
	
	@Autowired
	private PharmacyAddressHelper pharmacyAddrHelper;
	
	@PostMapping("/save/pharmacyAddress")
	public ResponseEntity<BaseDto<PharmacyAddressModel>> insertPharmacyAddressData(@Valid @RequestBody PharmacyAddressModel pharmacyAddressModel) {
		log.info("Request Object insert is: "+ pharmacyAddressModel);
		
		PharmacyAddressModel addrModelRes = pharmacyAddressService.savePharmacyAddressData(pharmacyAddressModel);
		return new BaseDto<>(addrModelRes,pharmacyAddrHelper.getSavePharmaAddrMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacyAddress")
	public ResponseEntity<BaseDto<PharmacyAddressModel>> updatePharmacyAddressData(@Valid @RequestBody PharmacyAddressModel pharmacyAddressModel) {
		log.info("Request Object for update is: ",pharmacyAddressModel);
		PharmacyAddressModel addrModelRes = pharmacyAddressService.updatePharmacyAddressData(pharmacyAddressModel);
		return new BaseDto<>(addrModelRes,pharmacyAddrHelper.getUpdatePharmaAddrMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/pharmacyAddress")
	public ResponseEntity<BaseDto<Object>> deletePharmacyAddressData(@RequestParam Integer pharmacyAddressId) {
		log.info("Request Object for delete is: ", pharmacyAddressId);
		pharmacyAddressService.deletePharmacyAddressById(pharmacyAddressId);
		return new BaseDto<>(pharmacyAddrHelper.getDeletePharmaAddrMessage(), OK).respond();
	}
	

	@GetMapping("/getactivepharmacyAddresses")
	public ResponseEntity<BaseDto<List<PharmacyAddressModel>>> getActivePharmaciesAddressData() {
		List<PharmacyAddressModel> result = pharmacyAddressService.findPharmacyAddressByActive();
		return new BaseDto<>(result, pharmacyAddrHelper.getRetrievePharmaAddrMessage(), OK).respond();
	}
	

	@GetMapping("/getpharmacydatabyid")
	public ResponseEntity<BaseDto<PharmacyAddressModel>> getPharmacyDataById(@RequestParam Integer pharmacyAddressId) {
		PharmacyAddressModel result =pharmacyAddressService.findPharmacyAddressById(pharmacyAddressId);
		return new BaseDto<>(result, pharmacyAddrHelper.getRetrievePharmaAddrMessage(), OK).respond();
	}
	

}
