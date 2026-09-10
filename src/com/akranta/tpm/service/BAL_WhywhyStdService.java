package com.akranta.tpm.service;

import java.util.List;


import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.WhywhyStdModel;

public interface BAL_WhywhyStdService {
	
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception;

}
