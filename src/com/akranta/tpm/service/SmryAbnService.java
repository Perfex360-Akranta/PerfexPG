package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface SmryAbnService {
	
	public List<String []> getAllAbnormalities(CommonFilter commonFilter,String checkField) throws Exception;

	public Workbook smryAbnormalityReportExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format,String checkField) throws Exception;

	public List<String[]> getAllAbnEmp(CommonFilter commonFilter) throws Exception;

	public Workbook AbnsmryEmpReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> getpiechart(CommonFilter piechrtCommonFilter, String flag) throws Exception;

	public List<String[]> getAllManager(CommonFilter commonFilter) throws Exception;

	public Workbook ManagerExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public void SmryAbnServiceImplJwt(String string);
}
