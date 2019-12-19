package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.DebitNoteModel;

@Repository
public interface DebitNoteRepository
extends JpaRepository<DebitNoteModel,Integer>
{

	@Query("select  new com.ihealthpharm.finance.dto.CreditCustomerDTO(dn.customerModel.customerId, CONCAT(c.customerName,' ', c.lastName )) from DEBIT_NOTE dn  inner join customer c on  dn.customerModel.customerId=c.customerId")
	List<CreditCustomerDTO> getAllCustomersMappedWithDebit();
	
}