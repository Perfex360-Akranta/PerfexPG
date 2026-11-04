package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PMComplianceDao {

	List<String[]> getAllPMCompliance(CommonFilter commonFilter)throws Exception;
	List<String[]> getAllPMComplianceW(CommonFilter commonFilter)throws Exception;

	Workbook PMComplainceReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter)	throws Exception;
	Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format);
	Workbook newPMComplainceReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;
	List<String[]> getAllPMComplianceNew(CommonFilter commonFilter) throws Exception;
	
	
	public void BAL_PMComplianceDaoImplJwt(String JwtToken);

}
