package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PMActivityWorkOrderDao {

	public Workbook getAllWoActivityExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format) throws Exception;

	public List<String[]> getAllWoActivity(CommonFilter commonFilter)throws Exception;

	public List<String[]> getPendingActivity(CommonFilter commonFilter) throws Exception;

	public Workbook getPendingActivityExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;
	
	
	public void BAL_PMActivityWorkOrderDaoImplJwt(String JwtToken);

}
