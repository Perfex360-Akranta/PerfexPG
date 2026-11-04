package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_MTTRMTBFDao {

	List<String[]> getAllMTTRMTBF(CommonFilter commonFilter) throws Exception;

	public Workbook getMTTRMTBFExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat,String type)throws Exception;

	List<String[]> getAllMTBF(CommonFilter commonFilter) throws Exception;

	public List<String[]> getchart1(String filterString, String month)throws Exception;

	void getRefresh(CommonFilter commonFilter)throws Exception;
	
	public void BAL_MTTRMTBFDaoImplJwt(String jwttoken);
	

}
