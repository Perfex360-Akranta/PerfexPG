package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GenTlConditionalappraisalBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;

public interface ConditionalAppraisalService {
	public abstract GenTlConditionalappraisal create(GenTlConditionalappraisal genTlConditionalappraisal,GenTlConditionalappraisal existGenTlConditionalappraisal,GenTlConditionalappraisalBean genTlConditionalappraisalBean) throws Exception;
	public abstract GenTlConditionalappraisal update(GenTlConditionalappraisal genTlConditionalappraisal,GenTlConditionalappraisal existGenTlConditionalappraisal,GenTlConditionalappraisalBean genTlConditionalappraisalBean) throws Exception;
	public abstract GenTlConditionalappraisal delete(GenTlConditionalappraisal genTlConditionalappraisal) throws Exception;
	
	public List<String[]>getConditionalAppraisalGrid(CommonFilter commonFilter) throws Exception;
	
	public abstract PlmTlConditionalappraisalmst getAllFillControl(String keyId)throws Exception;
	public abstract List<String[]> getfillgriddata()throws Exception;
	public abstract PlmTlConditionalappraisal create(
			PlmTlConditionalappraisal plmTlConditionalappraisal,
			PlmTlConditionalappraisal existPlmTlConditionalappraisal) throws Exception;
	public abstract PlmTlConditionalappraisal update(
			PlmTlConditionalappraisal plmTlConditionalappraisal,
			PlmTlConditionalappraisal existPlmTlConditionalappraisal) throws Exception;
	public abstract PlmTlConditionalappraisal delete(
			PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception;
	public abstract List<String[]> recallData(String keyid) throws Exception;
	public abstract Workbook getCondAppExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getConditionalAppMainGrid(
			CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getCondApReport(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getExcelColmodel(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getViewExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public abstract PlmTlConditionalappraisalmst delete( PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception ;
	public abstract PlmTlConditionalappraisalmst create(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst, PlmTlConditionalappraisalmst existPlmTlConditionalappraisalmst) throws Exception ;
	public abstract PlmTlConditionalappraisalmst update(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst, PlmTlConditionalappraisalmst existPlmTlConditionalappraisalmst) throws Exception ;
	public abstract List<ComboBox> getSpareComboList(CommonFilter commonFilter)throws Exception;
	public abstract String addnewcomponent(String compname, String mechid) throws Exception;
	public abstract List<ComboBox> getcheckingtool(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public abstract PlmTlConditionalappraisalmstentry createMstEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst)throws Exception;
	public abstract PlmTlConditionalappraisalmstentry updateEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst)throws Exception;
	public abstract PlmTlConditionalappraisalmstentry updateMstEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst)throws Exception;
	public abstract String checkdata(PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst, String ForGrid)throws Exception;
	public abstract List<String[]> getConditionalAppraisalEntryGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> recallentryData(String keyid)throws Exception;
	public List<String[]> getUserRoleDetails(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;
	
	public void ConditionalAppraisalServiceImplJwt(String JwtToken);
}
