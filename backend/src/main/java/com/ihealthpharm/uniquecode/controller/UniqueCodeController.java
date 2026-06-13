package com.ihealthpharm.uniquecode.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.uniquecode.model.UniqueCodeModel;
import com.ihealthpharm.uniquecode.service.UniqueCodeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class UniqueCodeController {

	@Autowired
	UniqueCodeService uniqueCodeService;
	
	@PostMapping("/save/uniquecode")
	public ResponseEntity<BaseDto<UniqueCodeModel>> saveUniqueCode(@RequestBody UniqueCodeModel uniqueCode){
		
		uniqueCode = uniqueCodeService.save(uniqueCode);
		return new BaseDto<>(uniqueCode, "Unique Code Saved", OK).respond();
	}
	
	@GetMapping("/get/uniquecodebyuniquecodename")
	public ResponseEntity<BaseDto<String>> getUniqueCodeByName(@RequestParam String uniqueCodeName){
		
		String uniqueNumber = uniqueCodeService.findByUniqueCodeName(uniqueCodeName);
		return new BaseDto<>(uniqueNumber, "Unique Code Updated And Retived", OK).respond();
	}
}
