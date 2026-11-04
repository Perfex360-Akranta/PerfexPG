package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface EquipmentDownTimeDao {

	List<String[]> getAllEquipmentDownTime(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllcount(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSumOfEquipmentDownTimeForDrill(CommonFilter commonFilter) throws Exception;

	public Workbook getdownTimeReport(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	
	public void BAL_EquipmentDownTimeDaoImplJwt(String jwtToken);
	
}
