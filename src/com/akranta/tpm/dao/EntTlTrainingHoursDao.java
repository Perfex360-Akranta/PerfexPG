package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;


public interface EntTlTrainingHoursDao {
	List<String[]> getTrainingHoursPgm(CommonFilter commonFilter) throws Exception;
	List<String[]> getTrainingHoursPgmGraph(CommonFilter commonFilter) throws Exception;
	public Workbook getTrainingHoursExportExcel(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
}
