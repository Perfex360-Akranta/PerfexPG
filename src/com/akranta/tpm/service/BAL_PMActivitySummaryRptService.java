package com.akranta.tpm.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PMActivitySummaryRptService {

	public List<String[]> getSummary(CommonFilter commonFilter);  

	public Workbook PMCompSummaryReportExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format,String jobType)throws Exception;
	
	public Workbook PMCompSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;

	public List<String[]> getPMComplianceSummary(CommonFilter commonFilter, String jobType) throws Exception;

	public String getReportName(CommonFilter commonFilter)throws Exception;

	public List<String[]> getActivityGraph(CommonFilter chrtCommonFilter, String jobType)throws Exception;

	public List<String[]> PMplanvsactualSummaryReport(CommonFilter commonFilter)throws Exception;

	public Workbook PMplanvsactualSummaryReportExportExcel(
			CommonFilter commonFilter, JSONObject tableModel, String format)throws Exception;

	public List<String[]> getPlanVsActualActivityGraph(CommonFilter commonFilter)throws Exception;

	public Workbook PMTimeSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;

	public List<String[]> getTimeSummary(CommonFilter commonFilter)throws Exception;
	
	
	public void BAL_PMActivitySummaryRptServiceImplJwt(String JwtToken);

}
