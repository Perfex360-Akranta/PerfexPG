package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlAccinvestigationmst;

public interface SheTlAccinvestigationmstDao {

	public abstract SheTlAccinvestigationmst create(SheTlAccinvestigationmst sheTlAccinvestigationmst) throws Exception;
	public abstract SheTlAccinvestigationmst update(SheTlAccinvestigationmst sheTlAccinvestigationmst) throws Exception;
	public abstract SheTlAccinvestigationmst delete(SheTlAccinvestigationmst sheTlAccinvestigationmst) throws Exception;
	public abstract List<String[]> getAccInvestDtl(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getAccInvestMainGrid(CommonFilter commonFilter) throws Exception;
	public abstract SheTlAccinvestigationmst getAllData(String keyid) throws NoDataFoundException, SQLException, Exception;
	public abstract Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws  Exception;
	public abstract String getElementID(String ainmFlid) throws Exception;;

}

