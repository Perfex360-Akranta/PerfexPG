package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface JhAuditRptDao {

	public List<String []> getJhAuditRpt(CommonFilter commonFilter) throws Exception ;
	
	
	
	public Map<Integer, List<String[]>> getAllJHAuditRptExl(String rowId, String format) throws Exception;
	
	
	public Workbook getJhAuditReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	
	
	List<String[]> getHseAccTrendRptgraph(CommonFilter commonFilter,String keyId) throws Exception;



	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;
	

	//graph
	//kaizen per person
	List<String[]> getAllAuditScoreGraph(CommonFilter commonFilter)throws Exception;
	Workbook jhAuditGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	
	public abstract void JhAuditRptDaoImplJwt(String jwtToken);
	
}
