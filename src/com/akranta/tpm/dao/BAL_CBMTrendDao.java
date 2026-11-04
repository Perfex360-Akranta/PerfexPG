package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface BAL_CBMTrendDao {

	public List<String []> getAllCbmTrend(CommonFilter commonFilter) throws Exception;
	
	
	public void BAL_CBMTrendDaoImplJwt(String JwtToken);
	
	public Workbook CBMExcelReport(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
}
