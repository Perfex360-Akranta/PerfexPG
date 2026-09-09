package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchEmpAbsent;

public interface ENTEmpAttendanceService {
	
	public List<String[]> getEmpList(CommonFilter commonFilter) throws Exception;
	public EntTlBatchEmpAbsent create(EntTlBatchEmpAbsent entTlBatchEmpAbsent)throws ValidationExceptions, Exception;
	List<ComboBox> getMonthCombo(String condsql, String progKey, ComboFilter comboFilter) throws Exception;;
	public String getProgram(String batch) throws Exception;
	public List<String[]> getTrainingAttedSmry(CommonFilter commonFilter)throws Exception;
	public Workbook attendanceSmryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getFacultyTrainingHoursReport(CommonFilter commonFilter)throws Exception;
	public List<String[]> getEmployeeattandancefillgriddata(CommonFilter commonFilter)throws Exception;
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception;

	public List<ComboBox> getProgkeyidCombo(String flid, ComboFilter comboFilter)throws Exception;
}
