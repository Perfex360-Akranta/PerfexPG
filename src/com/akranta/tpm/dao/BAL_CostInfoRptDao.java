package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_CostInfoRptDao {
	
	public List<String[]> getAllCostInfo(CommonFilter commonFilter ) throws Exception;
	
	public List<String[]> getCostInfoView(CommonFilter commonFilter) throws Exception;	
	public List<String[]> getGridSummaryQuery(String woId) throws Exception;	
	
	public List<String[]> getGridEstQuery(String formName, String woId) throws Exception;
	public List<String[]> getGridActQuery(String formName, String woId ) throws Exception;
	
	public List<String[]> getPageTotal(String formName, String formType,  String woId) throws Exception;
	
	
	public void BAL_CostInfoRptDaoImplJwt(String JwtToken);
	
		

}

