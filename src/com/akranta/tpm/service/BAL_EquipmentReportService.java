package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.impl.BAL_EquipmentReportServiceImpl;

public interface BAL_EquipmentReportService {
	
	public List<String[]> getAllEquipmentQuery(CommonFilter commonFilter) throws Exception;
	public  List<String[]> getAllEquipmentControlPanelService(CommonFilter commonFilter) throws Exception;	
	public List<String[]> getAllEqmpthistry(CommonFilter commonFilter)throws Exception;
	//public List<String[]> getAllEquipmentQuery(CommonFilter commonFilter) throws Exception;
	
	public Workbook equipmentQueryExportExcel(CommonFilter commonFilter ,JSONObject colModel,String rptFormat) throws Exception;
	public Workbook equipmentHistoryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAllMouldHistory(CommonFilter commonFilter)throws Exception;
	public Workbook getAllMouldHistoryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	public void BAL_EquipmentReportServiceImplJwt(String jwtToken);
	
	
}
