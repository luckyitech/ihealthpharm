package com.ihealthpharm.finance.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.ChartOfAccountRepository;
import com.ihealthpharm.finance.dao.PettyCashRepository;
import com.ihealthpharm.finance.helper.PettyCashHelper;
import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.model.ChartOfAccountsModel;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.PettyCashService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PettyCashServiceImpl implements PettyCashService{

	@Autowired
	PettyCashRepository pettyCashRepo;

	@Autowired
	PettyCashHelper pettyCashHelper;
	
	@Autowired
	ChartOfAccountRepository chartOfAccRepo;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PettyCashModel> findAllPettyCash() {
		return pettyCashRepo.findAll();
	}

	@Override
	public PettyCashModel savepettyCashData(PettyCashModel pettyCashDetails) {
		PettyCashModel pettyCashRes = pettyCashRepo.save(pettyCashDetails);
		return pettyCashRes;
	}

	@Override
	public PettyCashModel findPettyCashById(Integer pettyCashId) {
		PettyCashModel pettyCashRes = getValidPettyCash(pettyCashId);

		if(!Objects.nonNull(pettyCashRes)) {
			throw new IHealthPharmException(pettyCashHelper.getNotFoundpettyCashMessage(),HttpStatus.NOT_FOUND);
		}
		return pettyCashRes;
	}

	private PettyCashModel getValidPettyCash(Integer pettyCashId) {

		PettyCashModel pettyCashRes= null;
		try {
			pettyCashRes = pettyCashRepo.findById(pettyCashId).get();
			return pettyCashRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(pettyCashHelper.getNotFoundpettyCashMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<String> getAllCounterPartyDetails() {
		
		return pettyCashRepo.findAllCounterPartyAccountDetails();
	}

	@Override
	public List<String> getBySearchCounterPartyDetails(String searchTerm) {
		
		return pettyCashRepo.findCounterPartyAccountDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllPartyDetails() {
		
		return pettyCashRepo.findAllPartyAccountDetails();
	}

	@Override
	public List<String> getBySearchPartyDetails(String searchTerm) {
		
		return pettyCashRepo.findPartyAccountDetailsBySearch(searchTerm);
	}

	@Override
	public List<PettyCashModel> findAllPettyCashTransactionsBySearch(String refNo, String fromDate, String toDate,
			String party, String counterParty,Integer startPosition,Integer limit) {
		
		log.info(refNo);
		log.info(fromDate);
		log.info(toDate);

		List<PettyCashModel> result = null;
		String query="select pc from PETTY_CASH pc where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" pc.pettyCashRef like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.date >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.date <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.partyNo.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.counterPartyNo.accountId = "+Integer.parseInt(counterParty);
		}

		query+=clause;
		System.out.println("query is "+query);
		result=em.createQuery(query, PettyCashModel.class).setFirstResult(startPosition).
				setMaxResults(limit).getResultList();
		return result;
	}

	@Override
	public HashMap<String, ChartOfAccountsModel> updateChartOfAccountBalFromPettyScreen(String party,
			String counterParty, String amount, String selectedParty, String selectedCounterParty) {
		
		Double partyBalance=chartOfAccRepo.findBalanceRepo(Integer.parseInt(party));
		Double counterPartyBalance=chartOfAccRepo.findBalanceRepo(Integer.parseInt(counterParty));
		
		partyBalance=partyBalance+Double.parseDouble(amount);
		counterPartyBalance=counterPartyBalance-Double.parseDouble(amount);
		chartOfAccRepo.updatePartyBalance(Integer.parseInt(party),partyBalance);
		
		chartOfAccRepo.updateCounterPartyBalance(Integer.parseInt(counterParty),counterPartyBalance);
		
		HashMap<String,ChartOfAccountsModel> coaList=new HashMap<>();
		
		coaList.put("partyAccount",chartOfAccRepo.getChartOfAccountById(Integer.parseInt(selectedParty)));
		coaList.put("counterPartyAccount",chartOfAccRepo.getChartOfAccountById(Integer.parseInt(selectedCounterParty)));
	
		return coaList;
	
	}

	@Override
	public List<PettyCashModel> getAllPettyCashTxnByPagination(Integer pageNumber, Integer limit) {
		
		Pageable pagination = new PageRequest(pageNumber, limit);
		
		return pettyCashRepo.findPettyCashByPagination(pagination);
	}

	@Override
	public Integer getAllPettyCashCount() {
	
		return pettyCashRepo.findAll().size();
	}

	@Override
	public Integer findAllPettyCashTransactionsBySearchCount(String refNo, String fromDate, String toDate, String party,
			String counterParty) {
		List<PettyCashModel> result = null;
		String query="select pc from PETTY_CASH pc where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" pc.pettyCashRef like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.date >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.date <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.partyNo.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" pc.counterPartyNo.accountId = "+Integer.parseInt(counterParty);
		}

		query+=clause;
		System.out.println("query is "+query);
		result=em.createQuery(query, PettyCashModel.class).getResultList();
		return result.size();
	}

	
	
}
