package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_BDFailureRptService {
	public List<String []> getFrequency(CommonFilter commonFilter)  throws Exception;
	public List<String []> getBDSplit(CommonFilter commonFilter)  throws Exception;
	
	public Workbook failureRateExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public Workbook splitBreakupExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	

}
