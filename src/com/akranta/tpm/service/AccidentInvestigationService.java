package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;
import com.akranta.tpm.model.SheTlAccinvestigationmst;

public interface AccidentInvestigationService {

	List<String[]> getAccInvestDtl(CommonFilter commonFilter) throws Exception;

	SheTlAccinvestigationmst create(SheTlAccinvestigationmst sheTlAccinvestigationmst,SheTlAccinvestigationmst existSheTlAccinvestigationmst) throws Exception;

	SheTlAccinvestigationmst update(SheTlAccinvestigationmst sheTlAccinvestigationmst,SheTlAccinvestigationmst existSheTlAccinvestigationmst) throws Exception;

	List<String[]> getAccInvestMainGrid(CommonFilter commonFilter) throws Exception;

	SheTlAccinvestigationmst getAllData(String keyid) throws NoDataFoundException, SQLException, Exception;

	SheTlAccinvestigationmst delete( SheTlAccinvestigationmst sheTlAccinvestigationmst) throws Exception;

	List<SheTlAccinvestigationdtl> deleteDtl(List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl) throws Exception;

	Workbook getExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;

}
