package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchEmpAbsent;

public interface EntTlBatchEmpAbsentDao {

	public abstract EntTlBatchEmpAbsent create(EntTlBatchEmpAbsent entTlBatchEmpAbsent) throws Exception;
	public abstract EntTlBatchEmpAbsent update(EntTlBatchEmpAbsent entTlBatchEmpAbsent) throws Exception;
	public abstract EntTlBatchEmpAbsent delete(EntTlBatchEmpAbsent entTlBatchEmpAbsent) throws Exception;
	public List<String[]> getEmpList(CommonFilter commonFilter)throws Exception;
	public abstract String getProgram(String batch)throws Exception;
	public abstract List<String[]> getTrainingAttedSmry(CommonFilter commonFilter)throws Exception;
	public abstract Workbook attendanceSmryExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public abstract List<String[]> getFacultyTrainingHoursReport(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getEmployeeattandancefillgriddata(CommonFilter commonFilter)throws Exception;
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception ;
	
}

