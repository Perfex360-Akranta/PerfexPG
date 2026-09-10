package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_BDFailureRptDao {

	public List<String []> getFrequency(CommonFilter commonFilter) throws Exception ;
	public List<String []> getBDSplit(CommonFilter commonFilter)  throws Exception;
	
	public Workbook getfailureRateExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public Workbook splitBreakupExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
}
