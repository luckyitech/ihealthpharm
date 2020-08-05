package com.ihealthpharm.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dao.ChequeItemsRepository;
import com.ihealthpharm.finance.dao.ChequeRepository;
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

}
