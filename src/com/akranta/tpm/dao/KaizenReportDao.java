package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;



import com.akranta.tpm.model.CommonFilter;

public interface KaizenReportDao {

	public List<String[]> getKaizenReport()	throws Exception;

	public List<String[]> getJHKaizenMonthwise(CommonFilter commonFilter, String deptWise, String rptNmae)throws Exception;

	public Workbook jhKaizenMonwiseExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format, String deptWise, String rptName)throws Exception;

	//kaizen per person
	List<String[]> getAllKaizenPerPerson(CommonFilter commonFilter)throws Exception;
	List<String[]> getAllKaizenNosPerPerson(CommonFilter commonFilter)throws Exception;
	Workbook perPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;

	public List<String[]> getKaizenSummaryGridData(CommonFilter commonFilter) throws Exception;	
	Workbook getKaizenSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;

	public List<String[]> getKaizenSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception;
	public Workbook getKaizenSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception;
	
	public List<String[]> getKaizenSynopsisData(CommonFilter commonFilter) throws Exception;
	
	public Workbook getKaizenSynopsisDataExportExcel(CommonFilter commonFilter,	JSONObject tableModel, String format) throws Exception;
	
	public List<String[]> getSuggVsKaizenData(CommonFilter commonFilter) throws Exception;	
	public Workbook getSuggestionAccKaizenExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception;
	List<String[]> 	getKaizenBenefitData(CommonFilter commonFilter)throws Exception;
	List<String[]> 	KaizenBenefitListGraph(CommonFilter commonFilter,String rowid)throws Exception;
	Workbook getKaizenBenefitExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	
	
	
	public void KaizenReportDaoImplJwt(String JwtToken);
}
