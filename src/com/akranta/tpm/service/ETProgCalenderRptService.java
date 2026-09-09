package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;

public interface ETProgCalenderRptService {
	public List<String []> getTrnPrgCal(CommonFilter commonFilter)  throws Exception;	
	public Workbook etTrnPrgCalReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
	public List<String []> getSkillAnalysis(CommonFilter commonFilter)  throws Exception;
	public List<String []> getBefAftSkill(CommonFilter commonFilter,String empId)  throws Exception;
	public List<String[]> getEmpBefAftTopicRatings(String empId,String date,String fromSpoke) throws NoDataFoundException, Exception;
	public Workbook etSkillAnalysisReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public Workbook etBefAftReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
	public List<String []> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype)  throws Exception;
	
	public Workbook etPlanVsCompReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat, String custmrtype) throws Exception;
	
	public List<String[]> getPlanVsCompReportgraph(
			CommonFilter chrtCommonFilter, String keyId, String custype)throws Exception;
	
	public List<String[]> progCalPlanVsCompReportgraph(
			CommonFilter chrtCommonFilter, String keyId)throws Exception;
	
	public Workbook ProgCalPlanVsActReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
	public List<String []> getProgCalPlanVsCompCal(CommonFilter commonFilter)  throws Exception;
	
	public void ETProgCalenderRptServiceImplJwt(String JwtToken);
	
	

}
