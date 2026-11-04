package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_MTTRMTBFService {

	List<String[]> getAllMTTRMTBF(CommonFilter commonFilter)throws Exception;

	public Workbook mttrExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String format, String type)throws Exception;

	List<String[]> getAllMTBF(CommonFilter commonFilter)throws Exception;

	public void getRefresh(CommonFilter commonFilter)throws Exception;

	List<String[]> getchart1(String filterString, String month) throws Exception;
	
	public void BAL_MTTRMTBFServiceImplJwt(String jwtToken);

	

}
