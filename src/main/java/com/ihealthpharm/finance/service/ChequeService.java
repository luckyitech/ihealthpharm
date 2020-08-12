package com.ihealthpharm.finance.service;


import java.util.List;
import com.ihealthpharm.finance.model.ChequeModel;
import com.ihealthpharm.masters.dto.EmployeeAccessPharmaDTO;

public interface ChequeService {

	ChequeModel saveCheque(ChequeModel chequeModel);

	List<ChequeModel> getAllCheques();

	ChequeModel updateCheque(ChequeModel chequeModel);

	List<ChequeModel> getApprovedCheques();

	List<ChequeModel> getAllPendingCheques(String chequeNumber,Integer employeeId);

	List<ChequeModel> getAllApprovedCheques(String chequeNumber);
	
	List<ChequeModel> getAllEmployeeForCheques(Integer employeeId);
}
