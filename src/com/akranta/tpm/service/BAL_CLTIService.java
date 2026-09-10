package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_CLTIService {
	public List<String[]> getCLTIReport(CommonFilter commonFilter) throws Exception;


}
