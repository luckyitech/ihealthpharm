package com.ihealthpharm.finance.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dao.AccountPayablesRepository;
import com.ihealthpharm.finance.dao.ChequeItemsRepository;
import com.ihealthpharm.finance.dao.ChequeRepository;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.model.ChequeItemsModel;
import com.ihealthpharm.finance.model.ChequeModel;
import com.ihealthpharm.finance.service.ChequeService;
import com.ihealthpharm.masters.dto.EmployeeAccessPharmaDTO;

@Service
@Transactional
public class ChequeServiceImpl implements ChequeService {

	@Autowired
	private ChequeItemsRepository chequeItemsRepo;

	@Autowired
	private ChequeRepository chequeRepo;

	@Autowired
	private AccountPayablesRepository accRepo;

	@Override
	public ChequeModel saveCheque(ChequeModel chequeModel) {

		List<ChequeItemsModel> chequeItemModels = chequeModel.getChequeItems();

		ChequeModel chequeRes = chequeRepo.save(chequeModel);
		for (ChequeItemsModel it : chequeItemModels) {
			it.setCheque(chequeRes);
			chequeItemsRepo.save(it);
		}
		return chequeRes;
	}

	@Override
	public List<ChequeModel> getAllCheques() {
		List<ChequeModel> response=chequeRepo.getAllFirstLevelCheques();
		for(ChequeModel q : response) {
			q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
		}
		return response;
	}

	@Override
	public ChequeModel updateCheque(ChequeModel chequeModel) {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>");
		List<ChequeItemsModel> chequeItemModels = chequeModel.getChequeItems();
       System.out.println("<<<<<OOOOOOOOOOOOOOOOOOOOOOBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        
        ChequeModel cheq=chequeRepo.getOne(chequeModel.getChequeId());
        System.out.println("<WDSXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXS");
        cheq.setLastUpdateUser(chequeModel.getLastUpdateUser());
        cheq.setFirstLevelApproval(chequeModel.getFirstLevelApproval());
        cheq.setStatus(chequeModel.getStatus());
        cheq.setChequeApprovalStatus(chequeModel.getChequeApprovalStatus());
        cheq.setSecondLevelApproval(chequeModel.getSecondLevelApproval());
        
        
		ChequeModel chequeRes = chequeRepo.save(cheq);
		
		
		
		
		  System.out.println(chequeRes.getChequeDate());
		if(Objects.nonNull(chequeRes.getFirstLevelApproval()) && Objects.nonNull(chequeRes.getSecondLevelApproval())  ) {
			for(int i=0;i<chequeItemModels.size();i++) {
				AccountPayablesModel p=chequeItemModels.get(i).getAccountPayablesId();
				p.setSelectedStatus("Approved");
				p.setSelectedPaymentStatus("Paid");
				p.setTotalAmountPaid(p.getTotalAmountToBePaid());
				p.setTotalAmountToBePaid(0.00);
				p.setChequeAmount(chequeRes.getChequeAmt());
			//	LocalDate date=LocalDate.parse(chequeDateRes.getChequeDate())
				p.setChequeDate(chequeRes.getChequeDate());
				p.setChequeNumber(chequeRes.getChequeNumber());
				p.setLastUpdateUser(chequeRes.getLastUpdateUser());
				p.setPaymentType("Cheque");
				accRepo.save(p);
			}
		}

		return chequeRes;
	}

	@Override
	public List<ChequeModel> getApprovedCheques() {

		List<ChequeModel> response=chequeRepo.getApprovedCheques();
		for(ChequeModel q : response) {
			q.setApproverName(chequeRepo.getApproverPersonName(q.getChequeId()));
			q.setFirstLevelApproverName(chequeRepo.getFirstLevelApprover(q.getChequeId()));
			q.setSecondLevelApproverName(chequeRepo.getSecondLevelApprover(q.getChequeId()));
		}
		return response;
	}

	@Override
	public List<ChequeModel> getAllPendingCheques(String chequeNumber,Integer employeeId) {
		
		List<EmployeeAccessPharmaDTO> response=chequeRepo.getEmployeesHavingChequeAccess(employeeId);
		
		List<ChequeModel> res=null;
		int i=0;
		if(response.get(i).getActiveS().equals('Y') && response.get(i+1).getActiveS().equals('Y') ) {
			res=chequeRepo.getAllLevelsSearchedCheques(chequeNumber);
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
		}
		else if(response.get(i).getActiveS().equals('Y')) {
			System.out.println("in second if");
			res=chequeRepo.getAllFirstLevelSearchedCheques(chequeNumber);
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
			}
		else if(response.get(i+1).getActiveS().equals('Y')) {
			System.out.println("in third if");
			res=chequeRepo.getAllSecondLevelSearchedCheques(chequeNumber);
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
		}
		return res;
	}

	@Override
	public List<ChequeModel> getAllApprovedCheques(String chequeNumber) {
		
		List<ChequeModel> response=chequeRepo.getApprovedChequesBySearch(chequeNumber);
		for(ChequeModel q : response) {
			q.setApproverName(chequeRepo.getApproverPersonName(q.getChequeId()));
			q.setFirstLevelApproverName(chequeRepo.getFirstLevelApprover(q.getChequeId()));
			q.setSecondLevelApproverName(chequeRepo.getSecondLevelApprover(q.getChequeId()));
		}
		return response;
	}

	@Override
	public List<ChequeModel> getAllEmployeeForCheques(Integer employeeId) {
		List<EmployeeAccessPharmaDTO> response=chequeRepo.getEmployeesHavingChequeAccess(employeeId);

		List<ChequeModel> res=null;
		int i=0;
		if(response.get(i).getActiveS().equals('Y') && response.get(i+1).getActiveS().equals('Y') ) {
			res=chequeRepo.getAllLevelCheques();
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
		}
		else if(response.get(i).getActiveS().equals('Y')) {
			System.out.println("in second if");
			res=chequeRepo.getAllFirstLevelCheques();
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
			}
		else if(response.get(i+1).getActiveS().equals('Y')) {
			System.out.println("in third if");
			res=chequeRepo.getAllSecondLevelCheques();
			for(ChequeModel q : res) {
				q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
				q.setFirstLevelApproverName(chequeRepo.firstLevelApproverName(q.getChequeId()));
			}
		}
		return res;

	}

	@Override
	public Integer deleteAllChequeItems(Integer chequeId) {
		return chequeItemsRepo.deleteAllChequeItems(chequeId);
	}
	
	



}
