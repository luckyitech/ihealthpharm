package com.ihealthpharm.uniquecode.service;

import com.ihealthpharm.uniquecode.model.UniqueCodeModel;

public interface UniqueCodeService {

	public UniqueCodeModel save(UniqueCodeModel uniqueCode);
	
	public String findByUniqueCodeName(String uniceCodeName);
	
	
}
