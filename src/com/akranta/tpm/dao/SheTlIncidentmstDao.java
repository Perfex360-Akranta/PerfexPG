package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentmst;

public interface SheTlIncidentmstDao {

	public abstract SheTlIncidentmst create(SheTlIncidentmst sheTlIncidentmst) throws Exception;
	public abstract SheTlIncidentmst update(SheTlIncidentmst sheTlIncidentmst) throws Exception;
	public abstract SheTlIncidentmst delete(SheTlIncidentmst sheTlIncidentmst) throws Exception;
	public SheTlIncidentmst select(String keyid) throws Exception;
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception;
	public abstract SheTlIncidentEmployee recallEmployee(String keyId)throws Exception;
	public abstract SheTlActionplan recallAction(String keyId)throws Exception;
	public abstract Workbook accidentIncidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public abstract List<String[]> getAccidentInvest(CommonFilter commonFilter)throws Exception;
	public abstract Map<Integer, List<String[]>> getAccInvestExportExcel(String format,CommonFilter commonFilter,String insId) throws Exception;
	public abstract Workbook getAccidentInvestExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> saveOplImg(SheTlIncidentmst sheTlIncidentmst)throws Exception;
	public abstract List<GenTlAllmoduleimgfile> getImage(List<GenTlAllmoduleimgfile> insImgList) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> getImageExcel(List<GenTlAllmoduleimgfile> insImgList)throws Exception;
	public abstract List<String[]> getAccidentInvRpt(CommonFilter commonFilter) throws Exception;
	public abstract Workbook AccInvRptExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, IOException, Exception;
	public List<String[]> getIncidentCount(CommonFilter commonFilter) throws Exception;
	public List<String[]> incidentCumList(CommonFilter commonFilter) throws Exception;
	public List<String[]> hseIncidentStr(CommonFilter commonFilter) throws Exception;
	public List<String[]> incidentStrgraph(CommonFilter commonFilter,String keyid) throws Exception;
	public List<String[]> getIncidentCat(CommonFilter commonFilter) throws Exception;
	public List<String[]> incidentCategorygraph(CommonFilter commonFilter,String keyid) throws Exception;
	public Workbook IncidentCategyExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public List<String[]> safetyTrendRptList(CommonFilter commonFilter)throws Exception;
	public Workbook SafetyTrendRptExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public Workbook IncidentCountExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public Workbook IncidentCumCountExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
    public List<String[]> getNewIncidentCount(CommonFilter commonFilter) throws Exception;

}

