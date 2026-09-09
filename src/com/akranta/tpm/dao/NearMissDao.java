package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportdtl;
import com.akranta.tpm.model.GenTlNearmissreportmst;

public interface NearMissDao {
	public List<String[]> getAllNear(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getNear(String masterKeyid) throws Exception;
	
	public List<String[]> getNearMiss(String masterKeyid) throws Exception;

	public GenTlNearmissreportmst create(GenTlNearmissreportmst genTlNearmissreportmst) throws Exception;

	public GenTlNearmissreportmst update(GenTlNearmissreportmst genTlNearmissreportmst);
	public GenTlNearmissreportmst delete(GenTlNearmissreportmst genTlNearmissreportmst)throws Exception;

	public GenTlNearmissreportmst getGridDdatas(String keyId) throws NoDataFoundException, SQLException, Exception;
	
	public Workbook getNearMissExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1)throws Exception;

	public List<String[]> getSingleNearMissDetail(String keyId)	throws Exception;

	public List<String[]> getAllNeargrd() throws Exception;
	public List<String[]> getNearmissmonthrpt(CommonFilter commonFilter) throws Exception;

	public Workbook getNearMissMonthExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;

	public List<String[]> actnplnstatus(String keyid)throws Exception;

	public List<String[]> getfillnearmissdata(String keyid, String flid)throws Exception;

	public List<String[]> getfillunsafeconditiondata(String keyid, String flid)throws Exception;

	public List<String[]> getfillunsafeactdata(String keyid, String flid)throws Exception;

	public List<String[]> getNewNearmissRptReportgraph(
			CommonFilter chrtCommonFilter);
	
	
	public List<String[]> getNewNearmissmonthrpt(CommonFilter commonFilter) throws Exception;
   
	public List<String[]> getNearmisscountrpt(CommonFilter commonFilter) throws Exception;
    
	public List<String[]> getNearmissCountRptgraph(CommonFilter commonFilter,String rowId) throws Exception;
	
	public Workbook getNearMissCountToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
	public List<String[]> getNearmissTrendcountrpt(CommonFilter commonFilter)throws Exception;

	public List<String[]> getNearmissTrendcountrptgraph(CommonFilter commonFilter, String rowId)throws Exception ;	 
	public Workbook 	getNearMissTrendToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
	public List<String[]> getNearMissCumcount(CommonFilter commonFilter) throws Exception;
	public Workbook getNewNearCumCountExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
	public List<String[]> getNearMissCountList(CommonFilter commonFilter) throws Exception;
	public Workbook NearMissCountExportExcel(CommonFilter commonFilter1,JSONObject colmodel,
			String format) throws IOException, SQLException, Exception;
     public Workbook getNewNearMissMonthExportToExcel(JSONObject colmodel,String format, CommonFilter commonFilter1) throws  Exception;

	public List<String[]> getSafeactStratificationrpt(CommonFilter commonFilter)throws Exception;

	//public List<String[]> getUnSafeactStratificationrpt(CommonFilter commonFilter)throws Exception;

	public List<String[]> getpiegraph(CommonFilter commonFilter) throws Exception;

	public Workbook unsafeCond(CommonFilter commonFilter, JSONObject colmodel, String format)throws Exception;

	public List<String[]> getNewNearmissIdentified(CommonFilter commonFilter) throws Exception;
	public Workbook EmployeeNearMissExcel(CommonFilter commonFilter1,JSONObject colmodel,String format) throws
	IOException, SQLException, Exception;

}   
