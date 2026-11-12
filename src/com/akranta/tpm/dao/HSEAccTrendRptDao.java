package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface HSEAccTrendRptDao {

	public List<String []> getAccidentRpt(CommonFilter commonFilter) throws Exception ;
	
	public List<String []> getModeWiseRpt(CommonFilter commonFilter) throws Exception ;
	
	public List<String []> getBodyVsAccRpt(CommonFilter commonFilter) throws Exception ;
	
	public List<String []> getYYAnalysisRpt(CommonFilter commonFilter) throws Exception ;
	
	public Map<Integer, List<String[]>> getwhywhyQtyRptExl(String rowId, String format) throws Exception;
	
	
	public Workbook AccidentTrendExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	public Workbook ModeWiseAccExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	public Workbook BodyWiseAccExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	public Workbook YYAnalysisReportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	
	List<String[]> getHseAccTrendRptgraph(CommonFilter commonFilter,String keyId) throws Exception;
	
	List<String[]> getModeWiseAccRptgraph(CommonFilter commonFilter,String rowId) throws Exception;
	List<String[]> getBodyPartAccRptgraph(CommonFilter commonFilter,String keyId) throws Exception;

	public List<String[]> getAccidentMajor() throws Exception;

	public List<String[]> getInjuryVsAccRpt(CommonFilter commonFilter) throws Exception;

	public List<String[]> getInjurytypeAccRptgraph(
			CommonFilter chrtCommonFilter, String keyId) throws Exception;

	public Workbook injuryWiseAccExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	
	public List<String[]> getNewAccidentRpt(CommonFilter commonFilter) throws Exception;

	public Workbook NewAccidentTrendExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getNewHseAccTrendRptgraph(CommonFilter chrtCommonFilter, String keyId)throws Exception;
	public List<String[]> getNewBodyPartRpt(CommonFilter commonFilter) throws Exception;
	List<String[]> getNewBodyPartRptGraph(CommonFilter commonFilter,String keyId) throws Exception;
	public Workbook NewBodyPortExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
   public List<String []> getModeWiseReport(CommonFilter commonFilter) throws Exception ;
	public Workbook ModeWiseReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;	
	List<String[]> getModeWiseRptReportgraph(CommonFilter commonFilter,String rowId) throws Exception;
	public List<String[]> getEmployeeIncident(CommonFilter commonFilter) throws Exception;
	public Workbook EmpAccidentExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
	public void HSEAccTrendRptDaoImplJwt(String JwtToken);
}
