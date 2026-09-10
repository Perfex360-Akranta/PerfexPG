package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_GeneralmaintService {

	List<String[]> getAllgeneralmanit(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllGMDrillDnchrt(CommonFilter commonFilter);
	public List<String[]> getSapInfoList(CommonFilter commFilter)throws Exception;
	//gepublic List<String[]> getSapInfoList()throws Exception;

	public Workbook genMaintRptExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	public void BAL_GeneralmaintServiceImplJwt(String JwtToken);

}
