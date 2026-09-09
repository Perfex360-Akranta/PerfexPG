package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;

public interface QtmTlKnowwhymstDao {

	public abstract QtmTlKnowwhymst create(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
	public abstract QtmTlKnowwhymst update(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
	public abstract QtmTlKnowwhymst delete(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
	public abstract List<String[]> getKnow(CommonFilter commonFilter) throws Exception;
	public abstract QtmTlKnowwhymst getknwwhy(String keyId, String fileDir, String imagepath);
	public abstract List<String[]> getITCKnow(CommonFilter commonFilter) throws Exception;
	/*public abstract QtmTlKnowwhydtl createknowwhydtl(
			QtmTlKnowwhydtl qtmTlKnowwhydtl) throws Exception;
	public abstract QtmTlKnowwhydtl updateknowwhydtl(
			QtmTlKnowwhydtl qtmTlKnowwhydtl) throws Exception;*/
	public abstract QtmTlKnowwhydtl deleteknwwhydtl(
			QtmTlKnowwhydtl qtmTlKnowwhydtl, String dtlkeyid) throws Exception;
	public abstract Workbook getknowExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception, SQLException;
	public abstract List<String[]> getKnowrpt(CommonFilter commonFilter);
	public abstract List<String[]> KnowwhyExcelReport(
			String knowId, String format, CommonFilter commonFilter) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> getKnwImage(
			List<GenTlAllmoduleimgfile> knwwhyImgList) throws Exception;
	public abstract QtmTlKnowwhymst createdtl(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
	public abstract QtmTlKnowwhymst updatedtl(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
	public abstract int getStatusCount(String keyid) throws Exception;
    public String getapprovalkeyid(String appKeyid)throws Exception;
    //public String getknowwhycountlist(String knowwhykeyid)throws Exception;
    public abstract List<String[]> getKnwwhycnt(CommonFilter commonFilter) throws Exception;
    public Workbook KnowWhyCountExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
    public String restorefile(String keyId, String fileDir, String imagepath, QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception;
    
    public void KnowWhyDaoImplJwt(String JwtToken);
}







