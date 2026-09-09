package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface OplService {

	public List<String[]> getAllOPl(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAllexlOPl(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAlloplcountmodifyList(CommonFilter commonFilter)throws Exception;
	public List<GenTlAllmoduleimgfile> getoplImage(String fileName, String filePath, String oplId) throws NoDataFoundException, Exception;
	public List<String[]> getAllStudent(String oplId)throws Exception;
	public Workbook kaizenExportExcel(String oPLId, String format, String path,String flid,String type, String User,String imagePath, CommonFilter commonFilter) throws Exception;
	public Workbook getAllOPlxl(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	public List<String[]> getOPLSummaryGridData(CommonFilter commonFilter) throws Exception;
	Workbook getOplSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;

	public void OplServiceImplJwt(String string);
}
