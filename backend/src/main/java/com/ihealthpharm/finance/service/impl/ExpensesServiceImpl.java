package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.ExpensesRepository;
import com.ihealthpharm.finance.dto.expensesDTO;
import com.ihealthpharm.finance.model.ExpensesModel;
import com.ihealthpharm.finance.model.PettyCashModel;
import com.ihealthpharm.finance.service.ExpensesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ExpensesServiceImpl implements ExpensesService{

	@Autowired
	ExpensesRepository expensesRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ExpensesModel saveExpenses(ExpensesModel expensesModel) {
		return expensesRepo.save(expensesModel);
	}

	@Override
	public Integer updatePettyBalance(Integer pettyCashId, Double balance) {
		
		return expensesRepo.updateBalanceInPettyCash(pettyCashId,balance);
	}

	@Override
	public List<String> getAllPartiesBySearch(String searchTerm) {
	
		return expensesRepo.findPartyDetailsBySearch(searchTerm);
	}

	@Override
	public List<String> getAllParties() {
		
		return expensesRepo.findAllPartyDetails();
	}

	@Override
	public List<expensesDTO> findAllTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		return expensesRepo.getAllTransactionId(transactionId);
	}

	@Override
	public List<String> getAllCounterParties() {
		
		return expensesRepo.findAllCounterPartyDetails();
	}

	@Override
	public List<String> getAllCounterPartiesBySearch(String searchTerm) {
		
		return expensesRepo.findExpenseCounterPartyBySearch(searchTerm);
	}

	@Override
	public Integer getExpecncesCount() {
		
		return expensesRepo.getExpecncesCount();
	}

	@Override
	public List<ExpensesModel> getAllExpecncesByPagination(Integer pageNumber, Integer limit) {
		Pageable pagination = new PageRequest(pageNumber, limit);
		
		return expensesRepo.getAllExpencesByPagination(pagination);
	}

	@Override
	public Integer findAllExpensesTransactionsCountBySearch(String refNo, String fromDate, String toDate, String party,
			String counterParty) {
		
		log.info(refNo);
		log.info(fromDate);
		log.info(toDate);

		List<ExpensesModel> result = null;
		String query="select ex from expenses ex where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" ex.expenseNo like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.date >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.date <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.partyNo.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.counterPartyNo.accountId = "+Integer.parseInt(counterParty);
		}

		query+=clause+" order by ex.lastUpdateTimestamp desc";
		System.out.println("query is "+query);
		result=em.createQuery(query, ExpensesModel.class).getResultList();
		return result.size();
	}

	@Override
	public List<ExpensesModel> findAllExpensesTransactionsBySearch(String refNo, String fromDate, String toDate,
			String party, String counterParty,Integer pageNumber,Integer limit) {
		
		
		Pageable pagination = new PageRequest(pageNumber, limit);
		
		List<ExpensesModel> result = null;
		String query="select ex from expenses ex where ";

		String clause = " ";

		if(refNo!=null && !refNo.equals("undefined")) {
			clause+=" ex.expenseNo like "+"'"+refNo+"%"+"'";
		}

		if(fromDate!=null && !fromDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.date >="+"'"+fromDate+"'";
		}

		if(toDate!=null && !toDate.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.date <= "+"'"+toDate+"'";
		}
		
		if(party!=null && !party.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.partyNo.accountId = "+Integer.parseInt(party);
		}
		
		if(counterParty!=null && !counterParty.equals("undefined")) {
			if(!clause.equals(" ")) {
				clause+=" and ";
			}
			clause+=" ex.counterPartyNo.accountId = "+Integer.parseInt(counterParty);
		}

		
		query+=clause+" order by ex.lastUpdateTimestamp desc";
		System.out.println("query is "+query);
		
		result=em.createQuery(query, ExpensesModel.class).setFirstResult(pageNumber).setMaxResults(limit).getResultList();
		return result;
	}
	

	
}
