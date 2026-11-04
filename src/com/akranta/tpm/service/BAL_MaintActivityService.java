package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.BAL_MaintActivity;

public interface BAL_MaintActivityService {
	
	public List<String[]> getAllMaintActivity(CommonFilter commonFilter) throws Exception;
	public Workbook activityWiseRptExportExcel(CommonFilter commonFilter ,JSONObject tableModel ,String format) throws Exception;
	public Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,JSONObject tableModel ,String format) throws Exception;
	//public Workbook ActivityWiseExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter) throws Exception;
	public Workbook getEmployeeWiseActivityExlView(String empKeyId, String format,
			String path, String imagePath) throws Exception;
	public List<String[]> getActivityWiserpt(CommonFilter commonFilter) throws Exception;
	
	public void BAL_PmStandardServiceImplJwt(String JwtToken);



}
