package com.akranta.tpm.dao;

import java.util.List;


import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.WhywhyStdModel;

public interface BAL_WhywhyDao {
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter)	
	throws Exception;
	
	
}
