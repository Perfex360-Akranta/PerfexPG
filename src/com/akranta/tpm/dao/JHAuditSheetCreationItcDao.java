package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;


public interface JHAuditSheetCreationItcDao {
	List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter) throws Exception;
	public Workbook jhAuditSheetExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public abstract void JhaTlAuditmstItcDaoImplJwt(String jwtToken);
}
