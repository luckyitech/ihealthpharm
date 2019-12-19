package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.CreditNoteModel;

@Repository
public interface CreditNoteRepository extends JpaRepository<CreditNoteModel,Integer>
{
	
	@Query("select new com.ihealthpharm.finance.dto.CreditCustomerDTO(cn.customerModel.customerId, CONCAT (c.customerName,' ', c.lastName )) from CREDIT_NOTE cn  inner join customer c on cn.customerModel.customerId=c.customerId")
	List<CreditCustomerDTO> getCustomersFromCreditNote();
	
}