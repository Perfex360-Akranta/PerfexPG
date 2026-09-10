package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_BreakdownRptService {

	List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception;

}
