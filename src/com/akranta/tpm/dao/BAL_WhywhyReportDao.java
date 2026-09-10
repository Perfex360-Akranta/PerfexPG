package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface BAL_WhywhyReportDao {
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter)	
	throws Exception;
	
	public List<String[]> getWhywhyQtyStd(CommonFilter commonFilter)	
	throws Exception;

	public List<GenTlAllmoduleimgfile> getyyImage(List<GenTlAllmoduleimgfile> yyImgList)throws NoDataFoundException, Exception;

	public List<String[]> getWhywhy(CommonFilter commonFilter)throws Exception;

	public List<String[]> getAllStudent(String rowId) throws Exception;

	public List<String[]> getAllwhywhyStdExl(String rowId) throws Exception;
	
	public Workbook getwhywhyStdExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat)throws Exception;
	
	
	public Workbook getAllwhywhyQtyExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat)throws Exception;
	
	
	

	//public List<String[]> getwhywhyRptExl(String rowId,String format)throws Exception;
	public Map<Integer, List<String[]>> getwhywhyRptExl(String rowId, String format) throws Exception;
	
	public Map<Integer, List<String[]>> getwhywhyQtyRptExl(String rowId, String format) throws Exception;

	public List<String[]> getanalysis() throws Exception;

	public List<String[]> getProposed(CommonFilter commonFilter) throws Exception;

	public List<String[]> getYYEffectiveness(CommonFilter commonFilter) throws Exception;

	public List<String[]> getMainGrid() throws Exception;

	public List<String[]> getanalysis(String masdetkeyid) throws Exception;

	public List<String[]> FillControlData(String cellId, String keyid, String formName)throws Exception;
	
	public Map<Integer, List<String[]>> getwhywhyExlView(String rowId,String format)throws Exception;

	public Workbook WhyWhyEffectivenessExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format)throws Exception;

	public List<String[]> getyyDoneby(String masterkeyid)throws Exception; 
}
