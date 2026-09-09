package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportdtl;
import com.akranta.tpm.model.GenTlNearmissreportmst;

public interface NearMissService {
	
	public List<String[]> getAllNear(CommonFilter commonFilter) throws Exception;
	public List<String[]> getNear(String masterKeyid) throws Exception;
	public List<String[]> getNearMiss(String masterKeyid) throws Exception;
	public GenTlNearmissreportmst create(GenTlNearmissreportmst genTlNearmissreportmst,GenTlNearmissreportmst existGenTlNearmissreportmst)throws Exception;
	public GenTlNearmissreportmst update(GenTlNearmissreportmst existGenTlNearmissreportmst,GenTlNearmissreportmst existGenTlNearmissreportmst2)throws Exception;
	public GenTlNearmissreportmst delete(GenTlNearmissreportmst oldGenTlNearmissreportmst)throws Exception;

	public Workbook getNearMissExportToExcel(JSONObject colmodel,String format,
			CommonFilter commonFilter1)throws Exception;

	public GenTlNearmissreportmst getGridData(String keyId) throws NoDataFoundException, SQLException, Exception;
	//for detail
	public List<String[]> getSingleNearMissDetail(String keyId)	throws Exception;
	public List<String[]> getAllNeargrd() throws Exception;
	public List<String[]> getNearmissmonthrpt(CommonFilter commonFilter) throws Exception;
	public Workbook getNearMissMonthExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
	public List<String[]> actnplnstatus(String keyid)throws Exception;
	public Workbook nearmissExcelView(String keyid, String flid, String path)throws Exception;
	public List<String[]> getNearmisscountrpt(CommonFilter commonFilter) throws Exception;
	public List<String[]> getNearmissCountRptgraph(
			CommonFilter chrtCommonFilter,String rowId)throws Exception;
	public Workbook getNearMissCountToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
	public List<String[]> getNearmissTrendcountrpt(CommonFilter commonFilter) throws Exception;
	public List<String[]> getNearmissTrendcountrptgraph(CommonFilter chartCommonFilter, String rowId) throws Exception;
	public Workbook getNearMissTrendToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception;
}
   