package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PpMatrixRptDao {
	
	List<String[]> getPPMatrixMonthRptDao(CommonFilter commonFilter) throws Exception;
	List<String[]> getPPMatrixMachineRptDao(CommonFilter commonFilter) throws Exception;
	public Workbook getPPMatrixMonthRptExcelDao(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	public Workbook getPPMatrixMachineRptExcelDao(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
}
