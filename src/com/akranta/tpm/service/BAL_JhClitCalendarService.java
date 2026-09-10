package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_JhclitCalendarBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;

public interface BAL_JhClitCalendarService {


	public List<String[]> getAlljhnfngetshiftwise(CommonFilter commonFilter) throws Exception;
    public List<String[]> getAlljhnfnGetScheduledArray(CommonFilter commonFilter) throws Exception;

	public BAL_JhclitCalendarModel update(List<BAL_JhclitCalendarModel> existjhncalendar, String from_month, String user_createdBy,BAL_JhclitCalendarBean jhclitCalendarBean ) throws Exception;
	public BAL_JhclitCalendarModel delete(String flag);
	public Workbook JHCLITExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getConfiglink()throws Exception;
	public Workbook getJhnCalenderExportExcel(CommonFilter commonFilter, String format, String path, String imagePath)throws Exception;
	public List<GenTlToolsimg> getCLTIImage(String tpmTempimgDir,String imagePath, String keyid)throws Exception;

}

