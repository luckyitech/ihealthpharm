package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.IndentModel;

public interface IndentService {
	
	public IndentModel saveIndentData(IndentModel indent);

	public IndentModel updateIndentData(IndentModel indent);
	
	public List<IndentModel> updateIndentsData(List<IndentModel> indents);
	
	public List<IndentModel> findAllIndents();
	
	public IndentModel findIndentById(int indentId);
	
	public void deleteIndentById(int indentIds);
	
	public void deleteIndentsById(int[] indentIds);

}
