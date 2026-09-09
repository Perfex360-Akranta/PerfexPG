package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlSparesmst;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;

public interface PlmTlConditionalappraisalDao {

	public abstract PlmTlConditionalappraisal create(PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception;
	public abstract PlmTlConditionalappraisal update(PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception;
	public abstract PlmTlConditionalappraisal delete(PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception;
	public abstract List<String[]> recallData(String keyid) throws Exception;
	public abstract PlmTlConditionalappraisalmst getAllFillControl(String keyId) throws NoDataFoundException, SQLException, Exception;
	public abstract Workbook getCondAppExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getConditionalAppraisalGrid(
			CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getConditionalAppMainGrid(
			CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getCondApReport(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getExcelColmodel(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getViewExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public abstract PlmTlConditionalappraisalmst delete( PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception;
	public abstract PlmTlConditionalappraisalmst create(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception;
	public abstract PlmTlConditionalappraisalmst update(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception;
	public abstract String addnewcomponent(GenTlSparesmst genTlSparesmst, GenTlFunctionallocn genTlFunctionallocn,String machid) throws Exception;
	public abstract PlmTlConditionalappraisalmstentry createEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst)throws Exception;
	public abstract String checkupdate(PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst, String forGrid)throws Exception;
	public abstract List<String[]> getConditionalAppraisalEntryGrid(CommonFilter commonFilter)throws Exception;
	public abstract PlmTlConditionalappraisalmstentry updateEntry(PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst)throws Exception;
	public abstract List<String[]> recallEntryData(String keyid)throws Exception;
	public List<String[]> getUserRoleDetails(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;
	
	public abstract void PlmTlConditionalappraisalDaoImplJwt(String jwtToken);
}

