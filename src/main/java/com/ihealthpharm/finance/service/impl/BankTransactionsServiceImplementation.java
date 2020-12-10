package com.ihealthpharm.finance.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.BankTransactionsHistoryRepository;
import com.ihealthpharm.finance.dao.BankTransactionsRepository;
import com.ihealthpharm.finance.dao.ChartOfAccountRepository;
import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.helper.BankTransactionsHelper;
import com.ihealthpharm.finance.model.BankTransactionHistoryModel;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.BankTransactionsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BankTransactionsServiceImplementation implements BankTransactionsService {

	@Autowired
	BankTransactionsRepository bankTransRepo;
	
	@Autowired
	ChartOfAccountRepository chartOfAccRepo;

	@Autowired
	BankTransactionsHelper bankHelper;
	
	@Autowired
	BankTransactionsHistoryRepository bankTxnHisRepo;

	@PersistenceContext
	private EntityManager em;


	public List<BankTransactionsModel> findAllBankTransactions() {
		return bankTransRepo.findAll();
	}

	@Override
	public BankTransactionsModel saveTransaction(BankTransactionsModel bankTransactionsModel) {
		BankTransactionsModel res = bankTransRepo.save(bankTransactionsModel);
		log.info("GeneralLedger data" +res.getBankTransactionId() + "saved successfully");
		return res;
	}

	//	@Override
	//	public String checkByTransactionId(String transactionId) {

	//		if(res==null) {
	//			return "false";
	//		}
	//		if(res.getTransactionId().equals(transactionId)) {
	//		return "true";
	//		}
	//		return "";
	//	}

	@Override
	public List<BankTransactionDTO> findAllTransactionId(String transactionId) {

		return bankTransRepo.getAllTransactionId(transactionId);
	}

	@Override
	public List<String> getBySearchPartyDetails(String searchTerm) {
		return bankTransRepo.findPartyAccountDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllPartyDetails() {
		return bankTransRepo.findAllPartyAccountDetails();
	}

	@Override
	public List<String> getBySearchCounterPartyDetails(String searchTerm) {
		// TODO Auto-generated method stub
		return bankTransRepo.findCounterPartyAccountDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllCounterPartyDetails() {
		// TODO Auto-generated method stub
		return bankTransRepo.findAllCounterPartyAccountDetails();
	}

	@Override
	public List<BankTransactionsModel> findAllBankTransactionsByRefNo(String referenceNo) {

		return bankTransRepo.getAllTransactionsByRefNoSearch(referenceNo);
	}

	@Override
	public Integer findAllBankTransactionsBySearchCount(String refNo, 
			String fromDate, String toDate,String party,String counterParty) {
		log.info(refNo);
		log.info(fromDate);
		log.info(toDate);

		List<BankTransactionsModel> result = null;
		String query="select bt from bank_transactions bt where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" bt.transactionRef like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.transactionDate >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.transactionDate <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.party.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.counterParty.accountId = "+Integer.parseInt(counterParty);
		}

		query+=clause+" order by bt.lastUpdateTimestamp desc";
		System.out.println("query is "+query);
		result=em.createQuery(query, BankTransactionsModel.class).getResultList();
		return result.size();
	}

	@Override
	public BankTransactionsModel findBankTxnDetailsById(Integer bankTransactionId) {
		
		return bankTransRepo.getBankTxnDetailsById(bankTransactionId);
	}

	@Override
	public HashMap<String, ChartOfAccountsModel> updateChartOfAccountBal(String party, 
			String counterParty, String amount,String selectedParty,String selectedCounterParty,
			BankTransactionHistoryModel bankTxnHisModel) {
		
		Double partyBalance=chartOfAccRepo.findBalanceRepo(Integer.parseInt(party));
		Double counterPartyBalance=chartOfAccRepo.findBalanceRepo(Integer.parseInt(counterParty));
		
		partyBalance=partyBalance+Double.parseDouble(amount);
		counterPartyBalance=counterPartyBalance-Double.parseDouble(amount);
		
		bankTxnHisModel.setBalance(partyBalance);
		bankTxnHisModel.setCounterPartyBalance(counterPartyBalance);
		
		bankTxnHisRepo.save(bankTxnHisModel);
		
		chartOfAccRepo.updatePartyBalance(Integer.parseInt(party),partyBalance);
		
		chartOfAccRepo.updateCounterPartyBalance(Integer.parseInt(counterParty),counterPartyBalance);
		
		HashMap<String,ChartOfAccountsModel> coaList=new HashMap<>();
		
		coaList.put("partyAccount",chartOfAccRepo.getChartOfAccountById(Integer.parseInt(selectedParty)));
		coaList.put("counterPartyAccount",chartOfAccRepo.getChartOfAccountById(Integer.parseInt(selectedCounterParty)));
	
		return coaList;
	
	}

	@Override
	public Integer getAllBankTxnsCount() {
		
		return bankTransRepo.findAll().size();
	}

	@Override
	public List<BankTransactionsModel> getAllBankTxnByPagination(Integer pageNumber, Integer limit) {
		
		Pageable pagination=new PageRequest(pageNumber, limit);
		
		return bankTransRepo.getAllTxnsByPagination(pagination);
	}

	@Override
	public List<BankTransactionsModel> findAllBankTransactionsBySearch(String refNo, String fromDate, String toDate,
			String party, String counterParty, Integer startOfRecords, Integer limit) {
		List<BankTransactionsModel> result = null;
		String query="select bt from bank_transactions bt where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" bt.transactionRef like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.transactionDate >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.transactionDate <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.party.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" bt.counterParty.accountId = "+Integer.parseInt(counterParty);
		}

		query+=clause+" order by bt.lastUpdateTimestamp desc";
		System.out.println("query is "+query);
		result=em.createQuery(query, BankTransactionsModel.class).
				setFirstResult(startOfRecords).setMaxResults(limit).getResultList();
		return result;
	}

	@Override
	public List<String> getAllCOAAccountDetails() {
		
		return bankTransRepo.findAllCOAAccountDetails();
	}

	@Override
	public List<String> getBySearchCOAAccountDetails(String searchTerm) {
		
		return bankTransRepo.findCOAAccountDetailsBySearch(searchTerm);
	}




}
