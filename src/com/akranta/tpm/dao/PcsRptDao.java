package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface PcsRptDao {
	public List<String[]> getPlanVsRejData(CommonFilter commonFilter)throws Exception;
	List<String[]> getpcsRpt(CommonFilter commonFilter) throws Exception;
	List<String[]> getcellRpt(CommonFilter commonFilter) throws Exception;
	List<String[]> getPlanVsActual(CommonFilter commonFilter)  throws  Exception;

	List<String[]> getAllPcsRptExl(String rowId, CommonFilter commonFilter) throws Exception;



	//List<String[]> getAllPcsRptExl1(String rowId, String date)throws Exception;



//	List<String[]> getAllPcsRptExl1(String rowId, String date,CommonFilter commonFilter) throws Exception;



	List<String[]> getAllPcsRptExl1(String rowId, String date, String sectId,
			String prlmid,String shift,CommonFilter commonFilter, String locn) throws Exception;
	
	
	public Workbook getCellEfficiencyExcel(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;

	public Workbook getPcsRptExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;



	List<String[]> getAllPcsRptdtl(String rowId, String date, String sectId,String prlmid, String shiftId, CommonFilter commonFilter)throws Exception;



	public Map<Integer, List<String[]>> getAllPcsRptdtlExl(String rowId, String date, String sectId,
			String prlmid,String shiftId, String format, String path, String imagePath ) throws Exception;
	List<String[]> getlossBreakup(CommonFilter commonFilter)throws Exception;
	Workbook getlossBreakupExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	List<String[]> getSectAndMechWiseDefect(CommonFilter commonFilter)throws Exception;
	Workbook getSectAndMechWiseDefectExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	List<String[]> getLossTimeBreakupSmry(CommonFilter commonFilter) throws Exception;
	Workbook getLossTimeBreakupSmryExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	Workbook pcsPlanVsActualExport(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws  Exception;

	public Workbook PlanVsRejExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception;
}
