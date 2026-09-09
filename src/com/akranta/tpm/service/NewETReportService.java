package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalquad;

import net.sf.json.JSONObject;
public interface  NewETReportService {

	public List<String[]> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype) throws Exception;

	public List<String[]> getPlanVsCompReportgraph(CommonFilter chartCommonFilter, String flid, String custype) throws Exception;

	public Workbook etPlanVsCompReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String custmrtype) throws Exception;

	public List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter) throws Exception;

	public Workbook avgSkillGraphExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public List<String[]> getAllPerPerson(CommonFilter commonFilter) throws Exception;

	public Workbook perPersonKaizenExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public List<String[]> getAllNosPerPerson(CommonFilter commonFilter) throws Exception;

	public Workbook NosperPersonKaizenExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public List<String[]> getEmployeeLevel(CommonFilter commonFilter) throws Exception;

	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception;

	public List<String[]> getSkillGapReportGrid(CommonFilter commonFilter) throws Exception;

	public Workbook getSkillGapReportExcel(JSONObject colmodel, String format, CommonFilter commonFilter)throws Exception;

	public List<String[]> FillControlData(String keyid, String type)throws Exception;

	public EntTlTragcalquad createAssmLevel( String UpdateList,String userid) throws Exception;

	public Workbook getnomitnExportToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1)  throws Exception;

	public List<String[]> getnomitnrpt(CommonFilter commonFilter)  throws Exception;
   
	public Workbook assemeExcel(CommonFilter commonFilter1, JSONObject colmodel, String format) throws Exception;

	public List<String[]> getfourquadrant(CommonFilter commonFilter) throws Exception;

	public String getfunctionaldata(CommonFilter commonFilter) throws Exception;

	public Workbook FourExcelReport(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String imagepath)throws Exception;
	
	public List<String[]> getTrainingSummRpt(CommonFilter commonFilter)  throws Exception;
	public Workbook getTrainingSummaryExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
 
	
	public void NewETReportServiceImplJwt(String string);
	}
	
	


