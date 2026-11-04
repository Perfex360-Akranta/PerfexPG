package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.model.CommonFilter;



public interface BAL_MaintActivityDao {
	
	public List<String[]> getAllMaintActivity(CommonFilter commonFilter) throws Exception;

	public Workbook activityWiseRptExportExcel(CommonFilter commonFilter,JSONObject tablemodel,String format)throws Exception;

	public Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,JSONObject tableModel ,String format) throws Exception;
	//public Workbook ActivityWiseExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter) throws Exception;

	public Map<Integer, List<String[]>> getEmployeeWiseActivityExlView(
			String empKeyID, String format) throws Exception;

	public List<String[]> getActivityWiserpt(CommonFilter commonFilter) throws Exception;
	
	public void BAL_MaintActivityDaoImplJwt(String JwtToken);


}
