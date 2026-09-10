package com.akranta.tpm.dao;

import java.sql.ResultSet;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;

public interface BAL_JhClitCalendarDao {

	public List<String[]> getjhnfnGetScheduledArray(CommonFilter commonFilter, String shiftId)	throws Exception;
	public List<String[]> getjhnfnGetScheduledArray(CommonFilter commonFilter)	throws Exception;
    public List<String[]> getJHNFNGETSCHARRSHIFTWISE(CommonFilter commonFilter) throws Exception;
    public BAL_JhclitCalendarModel update(List<BAL_JhclitCalendarModel> existjhncalendar );
	public BAL_JhclitCalendarModel update_del( List<BAL_JhclitCalendarModel> newJhclitCalendarModel, String flag);
	public Workbook JHCLITExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public List<String[]> getConfiglink() throws Exception;
	public List<GenTlToolsimg> getCLTIImage(List<GenTlToolsimg> jhnImgList)throws Exception;
	public String save(List<String> actlst, String cellId,String mchineId, String shiftId,String createdBy,String status,String observations,String tagClass)throws Exception;
	public String getShift(ShiftBean shiftBean) throws Exception;
	public List<String[]> jhComplience(CommonFilter commonFilter)throws Exception;
	public Workbook jhComplienceExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;
	public List<String[]> getEmployeeMailList(String sectionId)throws Exception;
	public Workbook JhExportExcel(String refId,JSONObject colmodel, String format)throws Exception;
	public ResultSet JhExportData(String refId)throws Exception;
	


}
