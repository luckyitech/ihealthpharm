package com.ihealthpharm.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.GeneralLedgerRepository;
import com.ihealthpharm.finance.helper.GeneralLedgerHelper;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.model.GeneralLedgerModel;
import com.ihealthpharm.finance.service.GeneralLedgerService;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.uniquecode.service.UniqueCodeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class GeneralLedgerServiceImpl implements GeneralLedgerService {


	@Autowired
	private GeneralLedgerRepository generalLegderRepo;

	//@Autowired
	//private GeneralLedgerHelper generalLedgerHelper;
	
	@Autowired
	UniqueCodeService uniqueCodeService;

	@Override
	public GeneralLedgerModel saveGeneraledger(GeneralLedgerModel generalLedgerModel) {

		GeneralLedgerModel generalRes = generalLegderRepo.save(generalLedgerModel);
		log.info("GeneralLedger data" +generalRes.getGeneralLedgerId() + "saved successfully");
		return generalRes;
	}

	

	@Override
	public List<GeneralLedgerModel> saveMutipleLedgersData(List<AccountPayablesModel> accountPayablesModels) {
		List<GeneralLedgerModel> modelRes = new ArrayList<>();
		
		int i=0;
		for (AccountPayablesModel accountPayablesModel : accountPayablesModels) {
			Integer empId = accountPayablesModel.getApprovedBy().getEmployeeId() - i;
			EmployeeModel emp = new EmployeeModel();
			emp.setEmployeeId(empId);
			accountPayablesModel.setApprovedBy(emp);
			GeneralLedgerModel generalLedgerModel=new GeneralLedgerModel();
			generalLedgerModel.setJournalId(uniqueCodeService.findByUniqueCodeName("GL"));
			generalLedgerModel.setJournalRef(accountPayablesModel.getPaymentNumber());
			generalLedgerModel.setEntryNo(accountPayablesModel.getSourceRef());
			generalLedgerModel.setEntryType(accountPayablesModel.getSourceType());
			generalLedgerModel.setCounterParty(accountPayablesModel.getSupplierName());
			generalLedgerModel.setEntryDate(new Date());
			
			if(accountPayablesModel.getSourceType().equalsIgnoreCase("Debit Note")) {
				generalLedgerModel.setDebit(-1 * accountPayablesModel.getTotalAmountPaid());
				generalLedgerModel.setCredit(new Float(0.0));
				
			}else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Credit Note")) {
				generalLedgerModel.setDebit(new Float(0.0));
				generalLedgerModel.setCredit(-1 * accountPayablesModel.getTotalAmountPaid());
			}else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Purchase Returns - Debit Note")) {
				generalLedgerModel.setDebit(-1 * accountPayablesModel.getTotalAmountPaid());
				generalLedgerModel.setCredit(new Float(0.0));
			}/*else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Invoice")) {
				ā
			}*/
			
			generalLedgerModel.setBalance(0.0);
			generalLedgerModel.setPharmacyModel(accountPayablesModel.getPharmacyModel());
			
			

			modelRes.add(generalLegderRepo.save(generalLedgerModel));
			

			log.info("GeneralLedger data saved succesfully");
			i++;
		}
		return modelRes;

	}



	@Override
	public List<GeneralLedgerModel> saveMultipleLedgersDataForAccRecievables(List<AccountReceivablesModel> accountRecievabelsModels) {
		List<GeneralLedgerModel> modelRes = new ArrayList<>();

		int i=0;
		for (AccountReceivablesModel accountRecievablesModel : accountRecievabelsModels) {
			Integer empId = accountRecievablesModel.getApprovedBy().getEmployeeId() - i;
			EmployeeModel emp = new EmployeeModel();
			emp.setEmployeeId(empId);
			accountRecievablesModel.setApprovedBy(emp);
			
			GeneralLedgerModel generalLedgerModel=new GeneralLedgerModel();
			
			generalLedgerModel.setJournalId(uniqueCodeService.findByUniqueCodeName("GL"));
			generalLedgerModel.setJournalRef(accountRecievablesModel.getReceiptNumber());
			generalLedgerModel.setEntryNo(accountRecievablesModel.getSourceRef());
			generalLedgerModel.setEntryType(accountRecievablesModel.getSourceType());
			generalLedgerModel.setCounterParty(accountRecievablesModel.getCustomerName());
			generalLedgerModel.setEntryDate(new Date());
			
			if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Debit Note")) {
				
				generalLedgerModel.setDebit(new Float(0.0));
				generalLedgerModel.setCredit(accountRecievablesModel.getAmountReceived());
				
			}else if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Credit Note")) {
				
				
				generalLedgerModel.setDebit(accountRecievablesModel.getAmountReceived());
				generalLedgerModel.setCredit(new Float(0.0));
				
			}else if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Sales Returns - Credit Note")) {
				generalLedgerModel.setDebit(accountRecievablesModel.getAmountReceived());
				generalLedgerModel.setCredit(new Float(0.0));
			}
			/*else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Invoice")) {
				ā
			}*/
			
			generalLedgerModel.setBalance(0.0);
			generalLedgerModel.setPharmacyModel(accountRecievablesModel.getPharmacyModel());
			
			

			modelRes.add(generalLegderRepo.save(generalLedgerModel));
			

			log.info("GeneralLedger data saved succesfully");
			i++;
		}
		return modelRes;
	}

}
