package com.akranta.tpm.service;

import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.model.CommonFilter;

public interface BAL_PpMatrixRptService {
	
	public List<String[]> getPPMatrixMonthRpt(CommonFilter commonFilter) throws Exception;
	public List<String[]> getPPMatrixMachineRpt(CommonFilter commonFilter) throws Exception;
	public Workbook getPPMatrixMonthRptExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getPPMatrixMachineRptExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;

}
