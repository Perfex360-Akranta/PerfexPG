package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.VisualConBean;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
import com.akranta.tpm.model.SopTlVisualchecklistmst;

public interface VisualControlCheckServices
{
	

	public List<String[]> getVisualControlGrid(CommonFilter commonFilter,String keyid) throws Exception;
	public List<String[]> getVisualControl(CommonFilter commonFilter) throws Exception;
	public GenTlVisualcontrolchecklist create(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,GenTlVisualcontrolchecklist existGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public GenTlVisualcontrolchecklist update(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,GenTlVisualcontrolchecklist existGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public GenTlVisualcontrolchecklist getSelect(String keyId) throws Exception;
	public GenTlVisualcontrolchecklist delete(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist) throws Exception;
	public Workbook getVisualExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;

	public SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst,VisualConBean visualBean) throws Exception;
	public SopTlVisualchecklistmst update(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst,VisualConBean visualBean) throws Exception;
	public SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst) throws Exception, ValidationExceptions;
	public SopTlVisualchecklistmst update(
			SopTlVisualchecklistmst newSopTlVisualchecklistmst,
			SopTlVisualchecklistmst existSopTlVisualchecklistmst) throws Exception;
	public SopTlVisualchecklistmst getSelectvis(String keyId) throws Exception;
	public List<String[]> getSelectvisual(CommonFilter commonFilter) throws Exception;
	public SopTlVisualchecklistmst delete(
			SopTlVisualchecklistmst newSopTlVisualchecklistmst) throws Exception;
	public void Deletevis(String keyid) throws Exception;
	public Workbook getvisualWorkExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

	public Workbook getVisualExcelReport(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	public List<String[]> getVisualControlReport(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getRecordedbyComboList( ComboFilter comboFilter,String flid) throws Exception;
	public List<String[]> getVisualControlcheckpoints(CommonFilter commonFilter);
	public Workbook getVisualpointsExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws IOException, SQLException, Exception;

	public Workbook getVisualRptExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception ;
	public List<String[]> selecttitle(CommonFilter commonFilter);

	//graph
	
	List<String[]> getVisualWPScoreGraph(CommonFilter commonFilter)throws Exception;
	Workbook visualWPGraphExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	
	
	public void VisualControlCheckServicesImplJwt(String JwtToken);

}

