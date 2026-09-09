package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalquad;

import net.sf.json.JSONObject;

public interface NewETReportDao {

	public abstract List<String[]> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype) throws Exception;

	public abstract List<String[]> PlanVsCompReportgraph(CommonFilter commonFilter, String keyId, String custype) throws Exception;

	public abstract Workbook PlanVsCompReportExportExcel(CommonFilter commonFilter, JSONObject colmodel,
			String rptFormat, String custmrtype) throws Exception;

	public abstract List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter) throws Exception;

	public abstract Workbook avgSkillGraphExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;

	public abstract List<String[]> getAllPerPerson(CommonFilter commonFilter) throws Exception;

	public abstract Workbook perPersonKaizenExportExcel(CommonFilter commonFilter, JSONObject colModel,
			String rptFormat) throws Exception;

	

	public abstract List<String[]> getAllNosPerPerson(CommonFilter commonFilter) throws Exception;

	public abstract Workbook NosperPersonKaizenExportExcel(CommonFilter commonFilter, JSONObject colModel,
			String rptFormat) throws Exception;

	public abstract List<String[]> getEmployeeLevel(CommonFilter commonFilter) throws Exception;

	public abstract List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception;

	public abstract List<String[]> getSkillGapReportGrid(CommonFilter commonFilter)throws Exception;

	public abstract Workbook getSkillGapReportExcel(JSONObject colmodel, String format, CommonFilter commonFilter)throws Exception;

	public abstract List<String[]> FillControlData(String keyid, String type)throws Exception;

	public abstract EntTlTragcalquad createAssmLevel( String UpdateList,String userid) throws Exception;

	public abstract Workbook getnomitnExportToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1) throws Exception;

	public abstract List<String[]> getnomitnrpt(CommonFilter commonFilter) throws Exception;

	public abstract Workbook assemeExcel(JSONObject colmodel, String format, CommonFilter commonFilter1) throws Exception;

	public abstract List<String[]> getfourquadrant(CommonFilter commonFilter) throws Exception;

	public abstract String getfunctionaldata(CommonFilter commonFilter)throws Exception;

	public abstract Workbook FourExcelReport(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String imagepath)throws Exception;
	public List<String[]> getTrainingSummRpt(CommonFilter commonFilter)  throws Exception;
	public Workbook getTrainingSummaryExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
 
	
	public abstract void NewETReportDaoImplJwt(String jwtToken);
}
