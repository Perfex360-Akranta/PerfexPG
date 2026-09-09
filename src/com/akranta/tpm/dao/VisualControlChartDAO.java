package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
import com.akranta.tpm.model.SopTlVisualchecklistmst;

public interface VisualControlChartDAO 
{
	public abstract List<String[]> getVisualControlGrid(CommonFilter commonFilter,String keyid)throws Exception;
	public abstract List<String[]> getVisualControl(CommonFilter commonFilter)throws Exception;
	public abstract GenTlVisualcontrolchecklist create(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean) throws ValidationExceptions, BusinessApplicationExceptions, Exception;
	public abstract GenTlVisualcontrolchecklist getSelect(String keyId);
	public abstract GenTlVisualcontrolchecklist delete(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist) throws Exception;
	public abstract Workbook getVisualExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;

	public abstract SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst)throws Exception,  ValidationExceptions;
	public abstract SopTlVisualchecklistmst update(
			SopTlVisualchecklistmst newSopTlVisualchecklistmst) throws Exception;
	public abstract SopTlVisualchecklistmst getSelectvis(String keyId) throws Exception;
	//public abstract List<String[]> getSelectvisual() throws Exception;
	public abstract List<String[]> getSelectvisual(CommonFilter commonFilter) throws Exception ;
	public abstract SopTlVisualchecklistmst delete(
			SopTlVisualchecklistmst newSopTlVisualchecklistmst) throws Exception;
	public abstract void Deletevis(String keyid)throws Exception;
	public abstract Workbook getvisualWorkExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;

	public abstract Workbook getVisuaReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getVisualControlReport(CommonFilter commonFilter) throws Exception;
	public abstract List<ComboBox> fillComboValues(ComboFilter recordedby);
	public abstract List<String[]> getVisualControlcheckpoints(
			CommonFilter commonFilter);
	public abstract Workbook getVisualpointsExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws IOException, SQLException, Exception;
	public Workbook getVisualRptExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception ;
	public abstract List<String[]> selecttitle(CommonFilter commonFilter);

	//graph
	List<String[]> getVisualWPScoreGraph(CommonFilter commonFilter)throws Exception;
	Workbook visualWPGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	
	public abstract void VisualControlChecklistDaoImplJwt(String jwtToken);

	
}
