package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_GeneralmaintDao {

	List<String[]> getAllgeneralmanit(CommonFilter commonFilter) throws Exception;
	public List<String[]> getSapInfoList(CommonFilter commFilter) throws Exception;
	
	public  Workbook getGenMaintRpt(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	public void BAL_GeneralmaintDaoImplJwt(String JwtToken); 
}
