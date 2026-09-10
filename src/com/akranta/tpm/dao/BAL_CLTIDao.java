package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_CLTIDao {
	public List<String[]> getCLTIReport(CommonFilter commonFilter) throws Exception;


}
