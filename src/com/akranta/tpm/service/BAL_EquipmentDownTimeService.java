package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_EquipmentDownTimeService {

	List<String[]> getAllEquipmentDownTimet(CommonFilter commonFilter)throws Exception;

	List<String[]> getAllcount(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSumOfEquipmentDownTimeForDrill(CommonFilter commonFilter) throws Exception;

	public Workbook downtimeExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	
	public void BAL_EquipmentDownTimeServiceImplJwt(String JwtToken);

}
