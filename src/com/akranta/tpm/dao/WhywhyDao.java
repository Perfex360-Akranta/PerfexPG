package com.akranta.tpm.dao;

import java.util.List;


import com.akranta.tpm.model.CommonFilter;

public interface WhywhyDao {
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter)	
	throws Exception;
	
	
}
