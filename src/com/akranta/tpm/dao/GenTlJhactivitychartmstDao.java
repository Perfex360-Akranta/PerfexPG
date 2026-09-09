package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartmst;

public interface GenTlJhactivitychartmstDao {

	public abstract GenTlJhactivitychartmst create(GenTlJhactivitychartmst genTlJhactivitychartmst) throws Exception;
	public abstract GenTlJhactivitychartmst update(GenTlJhactivitychartmst genTlJhactivitychartmst) throws Exception;
	public abstract GenTlJhactivitychartmst delete(GenTlJhactivitychartmst genTlJhactivitychartmst) throws Exception;
	public abstract List<String[]> getjhActDtl(CommonFilter commonFilter) throws Exception;
	public abstract GenTlJhactivitychartmst selectMstData(String mstKeyid)throws Exception;
	public abstract List<String[]> getjhAct(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getjhActGridExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception;

}

