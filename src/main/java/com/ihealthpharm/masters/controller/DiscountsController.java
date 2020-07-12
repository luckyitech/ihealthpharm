package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

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
import com.ihealthpharm.masters.model.DiscountsModel;
import com.ihealthpharm.masters.service.DiscountsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class DiscountsController {
	@Autowired
	private DiscountsService discountsService;
	
	@PostMapping("/save/discount")
	public ResponseEntity<BaseDto<DiscountsModel>> saveDiscount(@RequestBody DiscountsModel discount) {
		
		
		DiscountsModel discountRes = discountsService.saveDiscount(discount);
		return new BaseDto<>(discountRes, "Discount Saved", OK).respond();
		
	}
	
	@PutMapping("/update/discount")
	public ResponseEntity<BaseDto<DiscountsModel>> UpdateDiscount(@RequestBody DiscountsModel discount) {
		
		DiscountsModel discountRes = discountsService.updateDiscount(discount);
		return new BaseDto<>(discountRes, "Discount Updated", OK).respond();
		
	}
	
	@DeleteMapping("/delete/discount")
	public ResponseEntity<BaseDto<Object>> deleteDiscount(@RequestBody DiscountsModel discount) {
		
		discountsService.deleteDiscount(discount);
		return new BaseDto<>("Discount Deleted", OK).respond();
		
	}
	
	@GetMapping("/getall/discounts")
	public ResponseEntity<BaseDto<List<DiscountsModel>>> getAllDiscounts() {
		
		List<DiscountsModel> discountRes = discountsService. getAllDiscountsWithActiveYes();
		return new BaseDto<>(discountRes, "Discount Retirved", OK).respond();
	}
	
	@GetMapping("/get/discountbyid")
	public ResponseEntity<BaseDto<DiscountsModel>> getDiscountById(@RequestParam Integer discountId) {
		
		DiscountsModel discountRes = discountsService.getDiscountByDiscountId(discountId);
		return new BaseDto<>(discountRes, "Discount Retrived", OK).respond();
	}
	
	@GetMapping("/get/discountslist")
	public ResponseEntity<BaseDto<List<DiscountsModel>>> getDiscountsList() {
		
		List<DiscountsModel> discountRes = discountsService.getAllDiscounts();
		return new BaseDto<>(discountRes, "Discount Retirved", OK).respond();
	}
}
