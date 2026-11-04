package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PMActivityWorkOrderService {

	public	List<String[]> getAllWoActivity(CommonFilter commonFilter) throws Exception;

	public	Workbook getAllWoActivityExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;

	public List<String[]> getPendingActivity(CommonFilter commonFilter)throws Exception;

	public Workbook getPendingActivityExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	
	public void BAL_PMActivityWorkOrderServiceImplJwt(String JwtToken);

}
