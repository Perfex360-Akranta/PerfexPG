package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface KaizenReportService {

	public List<String[]> getAllKaizenReport() throws Exception;

	public List<String[]> getJHKaizenMonthwise(CommonFilter commonFilter, String deptWise, String rptName)throws Exception;

	public Workbook jhKaizenMonwiseExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format, String deptWise, String rptName)throws Exception;


	//Kaizen per persons
	List<String[]> getAllKaizenPerPerson(CommonFilter commonFilter)throws Exception;
	
	List<String[]> getAllKaizenNosPerPerson(CommonFilter commonFilter)throws Exception;
	Workbook perPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;

	public List<String[]> getKaizenSummaryGridData(CommonFilter commonFilter) throws Exception;
	Workbook getKaizenSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	
	public List<String[]> getKaizenSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception;	
	Workbook getKaizenSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	
	public List<String[]> getKaizenSynopsisData(CommonFilter commonFilter) throws Exception;
	Workbook getKaizenSynopsisDataExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	public List<String[]> getSuggVsKaizenData(CommonFilter commonFilter) throws Exception;
	Workbook getSuggestionAccKaizenExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	public List<String[]> getKaizenBenefitData(CommonFilter commonFilter) throws Exception;
	public List<String[]> KaizenBenefitListGraph(CommonFilter commonFilter,String rowid) throws Exception;
	Workbook getKaizenBenefitExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	

	public void KaizenReportServiceImplJwt(String JwtToken);
	
}
