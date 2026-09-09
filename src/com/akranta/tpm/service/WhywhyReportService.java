package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface WhywhyReportService {
	
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAllwhywhyQtyStd(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getYY(String condSql,ComboFilter comboFilter) throws Exception;
	public List<String[]> getAllStudent(String rowId) throws Exception;
	public List<String[]> getAllexlyy(CommonFilter commonFilter) throws Exception;
	public List<GenTlAllmoduleimgfile> getAllwhywhyexcel(String fileName,String filePath, String rowId)throws NoDataFoundException, Exception;
	public List<String[]> getAllwhywhyStdExl(String rowId) throws Exception;
	
	public Workbook getAllwhywhyQtyExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public Workbook yyExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public Workbook getAllwhywhyQtyRptExl(String rowId, String format,	String path, String imagePath)throws Exception;
	public Workbook getAllwhywhyRptExl(String rowId, String format,	String path, String imagePath)throws Exception;
	public List<String[]> getanalysis()throws Exception;
	public List<String[]> getProposed(CommonFilter commonFilter) throws Exception;
	public List<String[]> getMainGrid() throws Exception;
	public List<String[]> getYYEffectiveness(CommonFilter commonFilter) throws Exception;
	public List<String[]> getanalysis(String masdetkeyid) throws Exception;
	public List<String[]> FillControlData(String cellId, String keyid, String formName)throws Exception;
	public Workbook getwhywhyExlView(String rowId, String format, String path,String imagePath)throws Exception;
	public Workbook WhyWhyEffectivenessExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format)throws Exception;
	public List<String[]> getyyDoneby(String masterkeyid) throws Exception;
	public List<String[]> getProbAttby(String masterkeyid)throws Exception;
	public void WhywhyReportServiceImplJwt(String JwtToken);
}
