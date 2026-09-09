package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface SmryAbnDao {
	
	public List<String []> getAllSmryAbns(CommonFilter commonFilter,String checkField) throws Exception;

	public Workbook smryAbnormalityReportExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String checkField) throws Exception;

	public List<String[]> getAllSmryAbnEmp(CommonFilter commonFilter) throws Exception;

	public Workbook AbnsmryEmpReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

	public List<String[]> getpiechart(CommonFilter piechrtCommonFilter, String flag) throws Exception;

	public List<String[]> getAllManager(CommonFilter commonFilter) throws Exception;

	public Workbook ManagerExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

	public void SmryAbnDaoImplJwt(String jwtToken);
}
