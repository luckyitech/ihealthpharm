package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.AccountReceivablesBillsModel;

@Repository
public interface AccountReceivablesBillsRepository extends JpaRepository<AccountReceivablesBillsModel,Integer>
{
	List<AccountReceivablesBillsModel> findAll();
	
	List<AccountReceivablesBillsModel> findAllByOrderByLastUpdateTimestampDesc();


}