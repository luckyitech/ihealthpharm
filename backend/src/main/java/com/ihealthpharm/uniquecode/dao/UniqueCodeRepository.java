package com.ihealthpharm.uniquecode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.uniquecode.model.UniqueCodeModel;

public interface UniqueCodeRepository extends JpaRepository<UniqueCodeModel, Integer>{

	UniqueCodeModel findByUniqueCodeName(String uniceCodeName);

}
