package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.PlannedJobDescBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;
import com.akranta.tpm.model.GenTlJhactivitychartmst;

public interface JhactivitychartService {

	List<String[]> getjhActDtl(CommonFilter commonFilter) throws Exception;

	GenTlJhactivitychartmst delete(
			GenTlJhactivitychartmst genTlJhactivitychartmst) throws Exception;

	GenTlJhactivitychartmst create(
			GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst)throws Exception;

	GenTlJhactivitychartmst update(
			GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst)throws Exception;

	GenTlJhactivitychartmst selectMstData(String mstKeyid) throws Exception;

	List<String[]> getjhAct(CommonFilter commonFilter) throws Exception;

	GenTlJhactivitychartdtl delete(List<GenTlJhactivitychartdtl> jhActDtlList) throws Exception;

	Workbook getjhActGridExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception;

}
