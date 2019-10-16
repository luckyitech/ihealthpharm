package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.EmployeeImageModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeImageService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeImageController {

	@Autowired
	EmployeeImageService employeeImageService;
	
	@PostMapping("save/employeeImage")
	public ResponseEntity<BaseDto<EmployeeImageModel>> insertEmployeeImageData(@Valid @RequestParam("employee") String employeeData,
			@RequestParam("imageDesc") String imageDesc
			,@RequestParam("image") MultipartFile image
			) throws IOException {
		EmployeeImageModel employeeImageModel = new EmployeeImageModel();
		EmployeeModel employeeModel = null;
		log.info(employeeData);
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeImageModel.setEmployeeImageId(null);
			employeeImageModel.setEmployee(employeeModel);
			employeeImageModel.setImageDesc(imageDesc);
			employeeImageModel.setImage(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeImageModel employeeRes = employeeImageService.saveEmployeeImage(employeeImageModel);
		return new BaseDto<>(employeeRes, "Employee Image Saved wiht Desc:"+imageDesc, OK).respond();
	}
	
	@PutMapping("update/employeeImage")
	public ResponseEntity<BaseDto<EmployeeImageModel>> updateEmployeeImageData(@Valid @RequestParam("employee") String employeeData,
			@RequestParam("imageDesc") String imageDesc,
			@RequestParam("imageId") Integer employeeImageId,
			@RequestParam("image") MultipartFile image) throws IOException {
		EmployeeImageModel employeeImageModel = new EmployeeImageModel();
		EmployeeModel employeeModel = null;
		log.info(employeeData);
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeImageModel.setEmployeeImageId(employeeImageId);
			employeeImageModel.setEmployee(employeeModel);
			employeeImageModel.setImageDesc(imageDesc);
			employeeImageModel.setImage(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeImageModel employeeRes = employeeImageService.updateEmployeeImage(employeeImageModel);
		return new BaseDto<>(employeeRes, "Employee Image Updated wiht Desc:"+imageDesc, OK).respond();
	}
	
	@PostMapping("/getimagesbyemployeeid")
	public ResponseEntity<BaseDto<List<EmployeeImageModel>>> getImagesByEmployeeId(@RequestBody EmployeeModel employeeId){
		
		List<EmployeeImageModel> imageList = employeeImageService.getByEmployeeId(employeeId);
		
		return new BaseDto<>(imageList, "Employee Image Retrived Successfully", OK).respond();
	}
	
	@GetMapping("/getallimages")
	public ResponseEntity<BaseDto<List<EmployeeImageModel>>> getAllImages(){
		
		List<EmployeeImageModel> imageList = employeeImageService.getAllEmployeeImages();
		
		return new BaseDto<>(imageList, "Employee Image Retrived Successfully", OK).respond();
	}
	
	@DeleteMapping("delete/byimageid")
	public ResponseEntity<BaseDto<Object>> deleteImages(@RequestParam Integer imageId){
		
		employeeImageService.deleteEmployeeImage(imageId);
		
		return new BaseDto<>("Employee Image Deleted Successfully", OK).respond();
	}
	
}
