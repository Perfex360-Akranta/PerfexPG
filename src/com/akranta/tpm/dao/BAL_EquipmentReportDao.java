package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_EquipmentReportDao {

	public List<String[]> getEquipmentQuery(CommonFilter commonFilter)throws Exception;
	public List<String[]> getEquipmentControlPanelDao (CommonFilter commonFilter) throws Exception;
	public List<String[]> getEqmpthistry(CommonFilter commonFilter)throws Exception;
	public Workbook equipmentQueryExportExcel(CommonFilter commonFilter, JSONObject colModel,String rptFormat) throws Exception;
	public Workbook equipmentHistoryExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public List<String[]> getAllMouldHistory(CommonFilter commonFilter)throws Exception;
	public Workbook getAllMouldHistoryExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	
	public void BAL_EquipmentReportDaoImplJwt(String jwtToken);
}
