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
import com.ihealthpharm.finance.helper.ChequeHelper;
import com.ihealthpharm.finance.model.ChequeModel;
import com.ihealthpharm.finance.service.ChequeService;

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
    	public ResponseEntity<BaseDto<ChequeModel>> saveCheque(@Valid @RequestBody ChequeModel chequeModel) {
    		ChequeModel model = service.saveCheque(chequeModel);
    		model.setChequeItems(null);
    		return new BaseDto<>(model, chequeHelper.getSaveChequeMessage(), OK).respond();
    	}
    	
        
    	/**
    	 * @author Tarun ,Service is to get the cheque
    	 *         details
    	 */
    	@GetMapping("/get/cheque")
    	public ResponseEntity<BaseDto<List<ChequeModel>>> getCheque() {
    		List<ChequeModel> result=service.getAllCheques();
    		return new BaseDto<>(result, chequeHelper.getRetrieveChequeMessage(), OK).respond();
    	}
    	
    	/**
    	 * @author Tarun ,Service is to save the cheque and cheque items
    	 *         details
    	 */
    	@PostMapping("/update/cheque")
    	public ResponseEntity<BaseDto<ChequeModel>> updateCheque(@Valid @RequestBody ChequeModel chequeModel) {
    		ChequeModel model = service.updateCheque(chequeModel);
    		return new BaseDto<>(model, chequeHelper.getUpdateChequeMessage(), OK).respond();
    	}
    	
    	/**
    	 * @author Tarun ,Service is to get the cheque
    	 *         details
    	 */
    	@GetMapping("/getAllApproved/cheques")
    	public ResponseEntity<BaseDto<List<ChequeModel>>> getAllApprovedCheques() {
    		List<ChequeModel> result=service.getApprovedCheques();
    		return new BaseDto<>(result, chequeHelper.getRetrieveChequeMessage(), OK).respond();
    	}
    	
    	/**
    	 * @author Tarun ,Service is to get the cheque data based on search
    	 *         details for pending
    	 */
    	@GetMapping("/getAllPending/cheques/basedOnSearch")
    	public ResponseEntity<BaseDto<List<ChequeModel>>> getAllPendingCheques(@RequestParam String chequeNumber,@RequestParam Integer employeeId) {
    		List<ChequeModel> result=service.getAllPendingCheques(chequeNumber,employeeId);
    		return new BaseDto<>(result, chequeHelper.getRetrieveChequeMessage(), OK).respond();
    	}
	
    	/**
    	 * @author Tarun ,Service is to get the cheque data based on search
    	 *         details and for approved
    	 */
    	@GetMapping("/getAllApproved/cheques/basedOnSearch")
    	public ResponseEntity<BaseDto<List<ChequeModel>>> getAllApprovedCheques(@RequestParam String chequeNumber) {
    		List<ChequeModel> result=service.getAllApprovedCheques(chequeNumber);
    		return new BaseDto<>(result, chequeHelper.getRetrieveChequeMessage(), OK).respond();
    	}
    	
    	@GetMapping("/getChequeApprovalStatus/byEmpId")
    	public ResponseEntity<BaseDto<List<ChequeModel>>> getEmployeeAccessForCheques(@RequestParam Integer employeeId){
    		List<ChequeModel> response=service.getAllEmployeeForCheques(employeeId);
    		return new BaseDto<>(response,"retrieved",OK).respond();
    	}
    	
    	@GetMapping("/delete/allChequeItems")
    	public ResponseEntity<BaseDto<Integer>> getChequeId(@RequestParam Integer chequeId ){
    		Integer response=service.deleteAllChequeItems(chequeId);
    		return new BaseDto<>(response,"Deleted",OK).respond();
    	}
    	
    	@GetMapping("/delete/ChequeItem")
    	public ResponseEntity<BaseDto<Integer>> deleteChequeItem(@RequestParam Integer accountPayableId ){
    		
    		Integer response=service.deleteChequeItem(accountPayableId);
    		return new BaseDto<>(response,"Deleted",OK).respond();
    	}
    	
}
