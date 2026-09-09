package com.akranta.tpm.dao;

import java.util.List;



import com.akranta.tpm.model.CommonFilter;

public interface BreakdownDao {

	public List<String[]> getBreakdown(CommonFilter commonFilter)
	throws Exception;

	
}