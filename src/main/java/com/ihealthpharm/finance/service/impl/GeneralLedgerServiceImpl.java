package com.ihealthpharm.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.GeneralLedgerRepository;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.model.GeneralLedgerModel;
import com.ihealthpharm.finance.service.GeneralLedgerService;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.PharmacyService;
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

	@Autowired
	private PharmacyService pharmacyService;


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
			generalLedgerModel.setInvoiceNo(accountPayablesModel.getInvoiceNo());
			generalLedgerModel.setCreatedUser(accountPayablesModel.getCreatedUser());
			generalLedgerModel.setLastUpdateUser(accountPayablesModel.getLastUpdateUser());
			generalLedgerModel.setParty(pharmacyService.findPharmacyById(accountPayablesModel.getPharmacyModel().getPharmacyId()).getPharmacyName());

			if(accountPayablesModel.getSourceType().equalsIgnoreCase("Debit Note")) {
				generalLedgerModel.setDebit(new Float(0.0));
				//-1 * accountPayablesModel.getTotalAmountPaid()
				generalLedgerModel.setCredit( accountPayablesModel.getTotalAmountPaid());

			}else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Credit Note")) {
				generalLedgerModel.setCredit(new Float(0.0));
				generalLedgerModel.setDebit(accountPayablesModel.getTotalAmountPaid());
			}else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Purchase Returns - Debit Note")) {
				//removed -1 * from -1 * accountPayablesModel.getTotalAmountPaid()
				generalLedgerModel.setDebit(accountPayablesModel.getTotalAmountPaid());
				generalLedgerModel.setCredit(new Float(0.0));
			}else if(accountPayablesModel.getSourceType().equalsIgnoreCase("Invoice")) {
				generalLedgerModel.setDebit(accountPayablesModel.getTotalAmountPaid());
				generalLedgerModel.setCredit(new Float(0.0));
			}

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
			generalLedgerModel.setCreatedUser(accountRecievablesModel.getCreatedUser());
			generalLedgerModel.setLastUpdateUser(accountRecievablesModel.getLastUpdateUser());
			generalLedgerModel.setParty(pharmacyService.findPharmacyById(accountRecievablesModel.getPharmacyModel().getPharmacyId()).getPharmacyName());

			if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Debit Note")) {

				generalLedgerModel.setDebit(new Float(0.0));
				generalLedgerModel.setCredit(accountRecievablesModel.getAmountReceived());

			}else if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Credit Note")) {

				//-1 * accountRecievablesModel.getAmountReceived()
				generalLedgerModel.setDebit( accountRecievablesModel.getAmountReceived());
				generalLedgerModel.setCredit(new Float(0.0));

			}else if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Sales Returns - Credit Note")) {
				//-1 * accountRecievablesModel.getAmountReceived()
				generalLedgerModel.setDebit(accountRecievablesModel.getAmountReceived());
				generalLedgerModel.setCredit(new Float(0.0));
			}

			/* else if(accountRecievablesModel.getSourceType().equalsIgnoreCase("Sales Billing")) {
                if(accountRecievablesModel.getAmountReceived() > 0){
                               generalLedgerModel.setCredit(accountRecievablesModel.getAmountReceived());
                      generalLedgerModel.setDebit(new Float(0.0));
               }else {
                  generalLedgerModel.setDebit(accountRecievablesModel.getAmountReceived());
             generalLedgerModel.setCredit(new Float(0.0));
             }

            }*/

			generalLedgerModel.setBalance(0.0);
			generalLedgerModel.setPharmacyModel(accountRecievablesModel.getPharmacyModel());

			modelRes.add(generalLegderRepo.save(generalLedgerModel));


			log.info("GeneralLedger data saved succesfully");
			i++;
		}
		return modelRes;
	}



	@Override
	public GeneralLedgerModel saveGeneralLedgerData(AccountReceivablesModel generalLedgerModel) {
		GeneralLedgerModel ledgerModelObj=new GeneralLedgerModel();

		ledgerModelObj.setJournalId(uniqueCodeService.findByUniqueCodeName("GL"));
		ledgerModelObj.setJournalRef(generalLedgerModel.getReceiptNumber());
		ledgerModelObj.setEntryNo(generalLedgerModel.getSourceRef());
		ledgerModelObj.setEntryType(generalLedgerModel.getSourceType());
		ledgerModelObj.setCounterParty(generalLedgerModel.getCustomerName());
		ledgerModelObj.setParty(pharmacyService.findPharmacyById(generalLedgerModel.getPharmacyModel().getPharmacyId()).getPharmacyName());
		ledgerModelObj.setEntryDate(new Date());
		ledgerModelObj.setCreatedUser(generalLedgerModel.getCreatedUser());
		ledgerModelObj.setLastUpdateUser(generalLedgerModel.getLastUpdateUser());
		if(generalLedgerModel.getAmountReceived() > 0) {
			ledgerModelObj.setCredit(generalLedgerModel.getAmountReceived());
			ledgerModelObj.setDebit(new Float(0.0));
		} else {
			ledgerModelObj.setDebit(generalLedgerModel.getAmountReceived());
			ledgerModelObj.setCredit(new Float(0.0));
		}
		ledgerModelObj.setBalance(0.0);
		ledgerModelObj.setPharmacyModel(generalLedgerModel.getPharmacyModel());

		GeneralLedgerModel respose=generalLegderRepo.save(ledgerModelObj);


		return respose;
	}

}
