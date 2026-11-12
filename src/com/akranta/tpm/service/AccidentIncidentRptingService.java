package com.akranta.tpm.service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.SheAccIncBean;
import com.akranta.tpm.bean.SheActionPlanBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;



public interface AccidentIncidentRptingService {
	
	public SheTlIncidentmst create(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst,  SheAccIncBean sheAccIncBean ) throws ValidationExceptions, Exception;
	public SheTlIncidentmst update(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst,  SheAccIncBean sheAccIncBean)  throws Exception;
	public SheTlIncidentmst delete(SheTlIncidentmst bdmTlMst) throws Exception;
	public SheTlIncidentmst select(String keyid) throws Exception;
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getInjuryComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getTypeComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getBodyPartComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public SheTlIncidentEmployee create(SheTlIncidentEmployee newsheTlIncidentEmployee,SheTlIncidentEmployee existSheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean)throws BusinessApplicationExceptions,Exception;
	public abstract SheTlIncidentEmployee delete(SheTlIncidentEmployee sheTlIncidentEmployee) throws Exception;
	public SheTlActionplan create(SheTlActionplan newSheTlActionplan,SheTlActionplan existSheTlActionplan,SheActionPlanBean sheActionPlanBean)throws Exception;
	public SheTlActionplan update(SheTlActionplan newSheTlActionplan,SheTlActionplan existSheTlActionplan,SheActionPlanBean sheActionPlanBean)throws Exception;
	public List<String[]> getIncidentActionPlan(SheTlActionplan sheTlActionplan)throws Exception;
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployee sheTlIncidentEmployee)throws Exception;
	public SheTlActionplan deleteActionPlan(SheTlActionplan sheTlActionplan)throws Exception;
	public SheTlIncidentEmployee recallEmployee(String keyId)throws Exception;
	public SheTlActionplan recallAction(String keyId) throws Exception;
	public SheTlIncidentEmployee update(SheTlIncidentEmployee newsheTlIncidentEmployee,SheTlIncidentEmployee existSheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean)throws Exception;
	public Workbook accidentIncidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	List<String[]> getAccidentInvest(CommonFilter commonFilter) throws Exception;
	public Workbook getAccInvestExportExcel(String format, String path,	String imagePath, CommonFilter commonFilter,String insId) throws Exception;
	public Workbook getAccidentInvestExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public List<GenTlAllmoduleimgfile> saveImg(SheTlIncidentmst existSheTlIncidentmst)throws Exception;
	public SheAccIncBean getImage(String fileName, String filePath,SheAccIncBean sheAccIncBean,SheTlIncidentmst sheTlIncidentmst)throws Exception;
	public List<GenTlAllmoduleimgfile> getImageExcel(String tpmTempimgDir,String path, String insKeyid)throws Exception;
	public List<String[]> getAccidentInvRpt(CommonFilter commonFilter) throws Exception;
	public Workbook AccInvRptExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, IOException, Exception;
}
