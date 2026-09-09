package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;

public interface EntempAttendenceRptDao {

	List<String[]> getEmpAttendence(CommonFilter commonFilter, GridParams gridParams) throws Exception;

	Workbook getEmployeeAttendceExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;

}
