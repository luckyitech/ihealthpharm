package com.ihealthpharm.finance.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
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
		List<ChequeModel> response=chequeRepo.getAll();
		for(ChequeModel q : response) {
			q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
		}
		return response;
	}

	@Override
	public ChequeModel updateCheque(ChequeModel chequeModel) {
		
		List<ChequeItemsModel> chequeItemModels = chequeModel.getChequeItems();
		chequeModel.setStatus("Approved");
		chequeModel.setChequeApprovalStatus("Paid");
		ChequeModel chequeRes = chequeRepo.save(chequeModel);
		
		for(int i=0;i<chequeItemModels.size();i++) {
			AccountPayablesModel p=chequeItemModels.get(i).getAccountPayablesId();
			p.setSelectedStatus("Approved");
			p.setSelectedPaymentStatus("Paid");
			p.setTotalAmountPaid(p.getTotalAmountToBePaid());
			p.setTotalAmountToBePaid(0.00);
			p.setChequeAmount(chequeRes.getChequeAmt());
			p.setChequeDate(chequeRes.getChequeDate());
			p.setChequeNumber(chequeRes.getChequeNumber());
			p.setLastUpdateUser(chequeRes.getLastUpdateUser());
			System.out.println(p);
			accRepo.save(p);
		}
		
		return chequeRes;
	}

	@Override
	public List<ChequeModel> getApprovedCheques() {
		
		List<ChequeModel> response=chequeRepo.getApprovedCheques();
		for(ChequeModel q : response) {
			q.setApproverName(chequeRepo.getApproverPersonName(q.getChequeId()));
		}
		return response;
	}

	@Override
	public List<ChequeModel> getAllPendingCheques(String chequeNumber) {
		List<ChequeModel> response=chequeRepo.getAllPendingCheques(chequeNumber);
		for(ChequeModel q : response) {
			q.setRequestedName(chequeRepo.getChequeRequestedName(q.getChequeId()));
		}
		return response;
	}

	@Override
	public List<ChequeModel> getAllApprovedCheques(String chequeNumber) {
		List<ChequeModel> response=chequeRepo.getApprovedChequesBySearch(chequeNumber);
		for(ChequeModel q : response) {
			q.setApproverName(chequeRepo.getApproverPersonName(q.getChequeId()));
		}
		return response;
	}
	
	

}
