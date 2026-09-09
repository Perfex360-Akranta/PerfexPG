package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;

public interface ETProgCalenderRptDao {

	public List<String []> getTrnPrgCal(CommonFilter commonFilter) throws Exception ;
	
	public Workbook getTrnPrgCalReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	public List<String []> getSkillAnalysis(CommonFilter commonFilter) throws Exception ;
	public List<String []> getBefAftSkill(CommonFilter commonFilter,String empId) throws Exception ;
	public List<String[]> getEmpBefAftTopicRatings(String empId,String date,String fromSpoke) throws NoDataFoundException, Exception;
	public Workbook SkillAnalysisReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public Workbook etBefAftReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	
	public List<String []> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype) throws Exception ;
	
	public Workbook PlanVsCompReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat, String custmrtype) throws Exception;
	
	
	List<String[]> PlanVsCompReportgraph(CommonFilter commonFilter,String keyId, String custype) throws Exception;
	
	List<String[]> ProgCalPlanVsActReportgraph(CommonFilter commonFilter,String keyId) throws Exception;
	
	public Workbook ProgCalPlanVsActReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	
	List<String[]> getProgCalPlanVsCompCal(CommonFilter commonFilter) throws Exception;
	
	public void ETProgCalenderRptDaoImplJwt(String JwtToken);
	
	
	
}
