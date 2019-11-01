package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.DebitNoteModel;

@Repository
public interface DebitNoteRepository
extends JpaRepository<DebitNoteModel,Integer>
{
	
}