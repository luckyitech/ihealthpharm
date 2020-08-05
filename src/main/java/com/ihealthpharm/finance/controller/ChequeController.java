package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.ChequeHelper;
import com.ihealthpharm.finance.model.ChequeModel;
import com.ihealthpharm.finance.service.ChequeService;
import com.ihealthpharm.stock.model.InvoiceModel;
import com.ihealthpharm.stock.model.PurchaseReturnModel;

@RestController
@CrossOrigin
public class ChequeController {

        @Autowired
        private ChequeHelper chequeHelper;
        
        @Autowired
        private ChequeService service;
        
    	/**
    	 * @author Tarun ,Service is to save the cheque and cheque items
    	 *         details
    	 */
    	@PostMapping("/save/cheque")
    	public ResponseEntity<BaseDto<ChequeModel>> saveInvoice(@Valid @RequestBody ChequeModel chequeModel) {
    		ChequeModel model = service.saveCheque(chequeModel);
    		model.setChequeItems(null);
    		return new BaseDto<>(model, chequeHelper.getSaveChequeMessage(), OK).respond();
    	}
    	
	
}
