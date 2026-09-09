package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlRiskassessmentmst;

public interface SheTlRiskassessmentmstDao {
	public abstract SheTlRiskassessmentmst create(SheTlRiskassessmentmst sheTlRiskassessmentmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlRiskassessmentmst update(SheTlRiskassessmentmst sheTlRiskassessmentmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlRiskassessmentmst delete(SheTlRiskassessmentmst sheTlRiskassessmentmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlRiskassessmentmst select(String keyid)throws Exception;
	public abstract Workbook getRiskExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public abstract List<String[]> getRiskList(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getRiskDtlsList(CommonFilter commonFilter)throws Exception;
	public abstract String getRiskLevel(String riskVal)throws Exception;
	public abstract List<String[]> getDeRiskList(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getDeRiskDtlsList(CommonFilter commonFilter)throws Exception;
	public abstract SheTlDeriskmst create(SheTlDeriskmst newSheTlDeriskmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlDeriskmst update(SheTlDeriskmst newSheTlDeriskmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlDeriskmst delete(SheTlDeriskmst newSheTlDeriskmst)	throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract SheTlDeriskmst selectDeRisk(String dramkeyid)throws Exception;
	public abstract List<String[]> getDeRiskDtlsGridList(CommonFilter commonFilter)throws Exception;
	public abstract Workbook DeRiskExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public abstract String getProbablityVal(String prob)throws Exception;
	public abstract String getSeviorityVal(String sev)throws Exception;
}

