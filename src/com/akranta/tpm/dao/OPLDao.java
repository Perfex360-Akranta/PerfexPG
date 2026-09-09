package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface OPLDao {
	public List<String[]> getOplReport(CommonFilter commonFilter)
	throws Exception;
	public List<String[]> getOplexlReport(CommonFilter commonFilter)throws Exception;
	public List<String[]> getoplcountmodify(CommonFilter commonFilter)throws Exception;
	public List<GenTlAllmoduleimgfile> getoplImage(List<GenTlAllmoduleimgfile> oplImgList) throws NoDataFoundException, Exception;
	public List<String[]> getAllStudent(String oplId)throws Exception;
	public List<String[]> getOplExcelReport(String oPLId, String format,CommonFilter commonFilter) throws Exception;
	public Map<Integer, List<String[]>> OplExcelReport(String oPLId,String format, CommonFilter commonFilter) throws Exception;
	public Workbook getAlloplxl(CommonFilter commonFilter, JSONObject colmodel,	String rptFormat)throws Exception;
	public List<String[]> getImprovementProjSheetkaiexlReportFunctllocn(String flid)throws Exception;
	
	public List<String[]> getOPLSummaryGridData(CommonFilter commonFilter) throws Exception;
	Workbook getOplSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
   
	public abstract void OplDaoImplJwt(String jwtToken);
}
