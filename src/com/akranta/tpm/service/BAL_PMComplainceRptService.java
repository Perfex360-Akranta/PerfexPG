package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PMComplainceRptService {

	List<String[]> getAllPMCompliance(CommonFilter commonFilter)throws Exception;

	Workbook PMComplainceReportExportExcel(CommonFilter commonFilter,JSONObject comName, String rptFormat) throws Exception;

	List<String[]> getAllPMComplianceW(CommonFilter commonFilter)throws Exception;
	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter) throws Exception;

	Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format);

	List<String[]> getAllPMComplianceNew(CommonFilter commonFilter) throws Exception;

	Workbook newPMComplainceReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;
	public void BAL_PMComplianceServiceImpJwt(String JwtToken);

}
