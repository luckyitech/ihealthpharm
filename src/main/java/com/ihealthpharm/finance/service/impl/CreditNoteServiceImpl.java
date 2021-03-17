package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.CreditNoteRepository;
import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.helper.CreditNoteHelper;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.service.CreditNoteService;
import com.ihealthpharm.masters.dao.EmployeeRepository;
import com.ihealthpharm.sales.dao.SalesReturnRepository;
import com.ihealthpharm.sales.model.SalesReturnModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CreditNoteServiceImpl implements CreditNoteService {
	
	@Autowired
	private CreditNoteRepository creditNoteRepo;
	
	@Autowired
	private SalesReturnRepository salesReturnRepo;
	
	
	@Autowired
	private CreditNoteHelper creditNoteHelper;
	
	@Autowired
	EmployeeRepository empRepo;
	

	@Override
	public void deleteCreditById(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteId);
		if (!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
		creditNoteRepo.delete(creditNoteRes);
		log.info("CreditNote data with ID: " + creditNoteRes.getCreditNoteId()+  "Deleted Successfully");

	}

	@Override
	public void deleteMultipleCredit(Integer[] creditNoteId) {
		CreditNoteModel creditNoteRes;
		for (int creditNote : creditNoteId) {
			creditNoteRes = getValidCreditNote(creditNote);
			if (!Objects.nonNull(creditNoteRes)) {
				throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
			}
			creditNoteRepo.delete(creditNoteRes);
			log.info("creditNote data with ID: " + creditNoteRes.getCreditNoteId() + " deleted succesfully");
		}				
	}

	@Override
	public List<CreditNoteModel> findAllCredit() {
		return creditNoteRepo.findAll();
	}

	@Override
	public CreditNoteModel findCreditById(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteId);
		
		if(!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("CreditNote with ID:" +creditNoteRes.getCreditNoteId()+ "retrived successfully");
		
		return creditNoteRes;
	}

	@Override
	public CreditNoteModel saveCreditData(CreditNoteModel creditNoteModel) {
		
		CreditNoteModel creditNoteRes = creditNoteRepo.save(creditNoteModel);
		log.info("CreditNote data" +creditNoteRes.getCreditNoteId() + "saved successfully");
		return creditNoteRes;
		
	}

	@Override
	public CreditNoteModel updateCreditData(CreditNoteModel creditNoteModel) {
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteModel.getCreditNoteId());

		if (!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
		creditNoteRes = creditNoteRepo.save(creditNoteModel);
		log.info("CreditNote data with ID : " + creditNoteRes.getCreditNoteId() + " updated succesfully");

		return creditNoteRes;
	}

	@Override
	public List<CreditNoteModel> updateListOfCreditData(List<CreditNoteModel> creditNoteModel) {
		creditNoteModel = creditNoteRepo.saveAll(creditNoteModel);

		log.info("Multiple CreditNote data  updated succesfully");

		return creditNoteModel;
	}
    
	private CreditNoteModel getValidCreditNote(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes= null;
		try {
			creditNoteRes = creditNoteRepo.findById(creditNoteId).get();
			return creditNoteRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<CreditCustomerDTO> getAllCustomersFromCreditNotes() {
		return creditNoteRepo.getCustomersFromCreditNote();
	}

	@Override
	public List<String> findCreditNoteNobysearchCN(String searchTerm) {
		return creditNoteRepo.findCreditNoteNoBySearch(searchTerm);
	}

	@Override
	public List<String> findallCreditNoteNoCN() {
		return creditNoteRepo.findAllCreditNoteNoINCN();
	}

	@Override
	public List<String> findAllCustomers() {
		
		return creditNoteRepo.getAllCustomers();
	}

	@Override
	public List<String> findAllCustomersBySearch(String customer) {
		return creditNoteRepo.getCustomersBySearch(customer);
	}

	@Override
	public List<String> getCreditNoteByBillType(String searchTerm) {
		return creditNoteRepo.getCreditNoteByBillTypes(searchTerm);
	}

	@Override
	public List<CreditNoteModel> getAllCreditNotes() {
		List<CreditNoteModel> res=creditNoteRepo.getAllCNData() ;
		for(int i=0;i<res.size();i++) {
			if(res.get(i).getApprovedBy() !=null) {
				int empId=res.get(i).getApprovedBy().getEmployeeId();
				String empName=empRepo.getEmpNameByEmpId(empId);
				res.get(i).setEmpName(empName);
			}
		}
		return res;
	}

	@Override
	public List<CreditNoteModel> getAllCreditNotesBySearch(String searchTerm, String searchValue) {
		if(searchTerm.equalsIgnoreCase("Credit Note No")) {
			return creditNoteRepo.getAllDataBySearchForCreditNoteNo(searchValue);
		}else {
			return creditNoteRepo.getAllDataBySearchForBills(searchValue);
		}
	}
	
		
	public List<String> getAllCreditNotesPaymentStatus() {
		return creditNoteRepo.findAllPaymentStatus();
	}

	@Override
	public CreditNoteModel getCreditNote(Integer creditNoteId) {
		return creditNoteRepo.getCreditNoteData(creditNoteId);
	}

	@Override
	public CreditNoteModel updateCreditNoteById(Integer creditNoteId, String paymentStatus) {
		
		
		creditNoteRepo.updatePaymentStatus(creditNoteId,paymentStatus);
		CreditNoteModel res=creditNoteRepo.getCreditNoteData(creditNoteId);
		String remarks="used against the sales bill - "+res.getBillId();
		creditNoteRepo.updateRemarks(remarks, res.getCreditNoteNo());
		
		if(res.getReturnTypeReason().equals("Sales Returns") && Objects.nonNull(res.getSourceRef())) {
			SalesReturnModel saleReturnModel=salesReturnRepo.getSalesReturnDataByRefNo(res.getSourceRef());
			saleReturnModel.setPaymentStatus("Paid");
			saleReturnModel.setRemarks("used against the sales bill - "+res.getBillId());
					
			salesReturnRepo.save(saleReturnModel);
		}
		
		return res;
	}

	@Override
	public CreditNoteModel updateCreditNoteRemarks(String remarks, String crNo) {
		
		CreditNoteModel res=creditNoteRepo.findByCreditNoteNo(crNo);
		
		if(Objects.nonNull(res.getRemarks())) {
		creditNoteRepo.updateRemarks(res.getRemarks()+" - "+remarks, crNo);
		}else {
			creditNoteRepo.updateRemarks(remarks, crNo);
		}
		CreditNoteModel resToSend=creditNoteRepo.findByCreditNoteNo(crNo);
		
		return resToSend;
	}

	@Override
	public List<CreditNoteModel> findAllCreditNotesByCustomerId(Integer customerId) {
		
		return creditNoteRepo.findPendingCreditNotesByCustomer(customerId);
	}

	@Override
	public CreditNoteModel updateCreditNoteRemarks(Float leftAmount, 
			String prevCreditNoteNo, String newCreditNoteNo,String billCode,Float netAmount) {
		
		CreditNoteModel res=creditNoteRepo.findByCreditNoteNo(prevCreditNoteNo);
		
		res.setPaymentStatus("Paid");
		res.setRemarks("used against the sales bill - "+billCode+
				" of amount - "+netAmount);
				
	
		creditNoteRepo.save(res);
		
		
		CreditNoteModel newCreditNote=new CreditNoteModel();
		
		newCreditNote.setCreditNoteNo(newCreditNoteNo);
		newCreditNote.setAmount(leftAmount);
		newCreditNote.setCreditDate(res.getCreditDate());
		newCreditNote.setBillId(res.getBillId());
		newCreditNote.setPharmacyModel(res.getPharmacyModel());
		newCreditNote.setActiveS(res.getActiveS());
		newCreditNote.setReturnType(res.getReturnType());
		newCreditNote.setReturnTypeReason(res.getReturnTypeReason());
		newCreditNote.setCustomerModel(res.getCustomerModel());
		newCreditNote.setApprovedBy(res.getApprovedBy());
		newCreditNote.setApprovedDate(res.getApprovedDate());
		newCreditNote.setStatus(res.getStatus());
		newCreditNote.setPaymentType(res.getPaymentType());
		newCreditNote.setTax(res.getTax());
		newCreditNote.setDiscount(res.getDiscount());
		newCreditNote.setBillType(res.getBillType());
		newCreditNote.setPaymentStatus("Pending");
		newCreditNote.setCreatedUser(String.valueOf(res.getLastUpdateUser()));
		newCreditNote.setLastUpdateUser(res.getLastUpdateUser());
		newCreditNote.setSourceRef(billCode);
	
		newCreditNote.setRemarks(prevCreditNoteNo+" - "+"used against the sales bill - "+billCode+
		" of amount - "+netAmount+" and generated new credit note with the balance left - "+leftAmount);
		
		if(res.getReturnTypeReason().equals("Sales Returns") && Objects.nonNull(res.getSourceRef())) {
			SalesReturnModel saleReturnModel=salesReturnRepo.getSalesReturnDataByRefNo(res.getSourceRef());
			saleReturnModel.setPaymentStatus("Paid");
			saleReturnModel.setRemarks("used against the sales bill - "+billCode+
					" of amount - "+netAmount);
					
			salesReturnRepo.save(saleReturnModel);
		}
		return creditNoteRepo.save(newCreditNote);
	}
   
}