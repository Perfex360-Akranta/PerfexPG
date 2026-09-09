package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;



import com.akranta.tpm.model.CommonFilter;

public interface AbnormalityReportDao {

	public List<String[]> getAbnormalityGeneral(CommonFilter commonFilter)	throws Exception;

	public Workbook abnormalityGeneralReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

	public List<String[]> getUnsafeDrillDn(CommonFilter commonFilter,String parentId) throws Exception;

	public List<String[]> getUnsafeMonth(CommonFilter commonFilter) throws Exception;

	
}
