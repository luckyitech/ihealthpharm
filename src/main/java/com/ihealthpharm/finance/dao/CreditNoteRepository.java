package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.CreditNoteModel;

@Repository
public interface CreditNoteRepository
extends JpaRepository<CreditNoteModel,Integer>
{
	List<CreditNoteModel> findAll();
}