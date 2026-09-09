package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface WhyComplianceRptService {

	List<String[]> getAllyydrill(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllyycelldrill(String colVal, CommonFilter commonFilter)throws Exception;

	List<String[]> getAllmonth(CommonFilter commonFilter)throws Exception;

	public Workbook whycompExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;

	public Workbook whydetailExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format)throws Exception;

	public Workbook whymonthExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;

	

}
