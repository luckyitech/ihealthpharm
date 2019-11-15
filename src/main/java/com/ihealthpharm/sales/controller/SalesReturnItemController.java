package com.ihealthpharm.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.sales.helper.SalesReturnItemHelper;
import com.ihealthpharm.stock.helper.PurchaseReturnItemHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class SalesReturnItemController {
	
	@Autowired
	SalesReturnItemHelper salesReturnItemHelper;
	
	
	
	

}
