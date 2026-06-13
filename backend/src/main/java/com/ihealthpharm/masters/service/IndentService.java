package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.IndentModel;

public interface IndentService {
	
	 IndentModel saveIndentData(IndentModel indent);

	 IndentModel updateIndentData(IndentModel indent);
	
	 List<IndentModel> updateIndentsData(List<IndentModel> indents);
	
	 List<IndentModel> findAllIndents();
	
	 IndentModel findIndentById(Integer indentId);
	
	 void deleteIndentById(Integer indentIds);
	
	 void deleteIndentsById(Integer[] indentIds);

}
