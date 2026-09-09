package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;

public interface GenTlActionplanmstDao {
	public GenTlActionplanmst getActionPlanDetailAdd(String keyId)throws Exception ;
	public abstract GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions, Exception;
	public abstract GenTlActionplanmst update(GenTlActionplanmst existGenTlActionplanmst,GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions, Exception;
	public abstract GenTlActionplanmst delete(GenTlActionplanmst genTlActionplanmst) throws Exception;
	public abstract List<String[]> getActionPlanReport(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getActionPlanDetail(CommonFilter commonFilter)throws Exception;
	public abstract GenTlActionplanmst getAllFillControl(GenTlActionplanmst genTlActionplanmst)throws Exception;
	public abstract List<String[]> getMultiSelectEmp(CommonFilter commonFilter)throws Exception;
	public Workbook actionPlanExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public Workbook actionPlanDetailExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception; 
	List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams) throws NoDataFoundException, Exception ;
	//List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams,String flid,String Teamchk) throws NoDataFoundException, Exception ;
	void saveActionPlanCompletion(List<GenTlActionplandtl> genActionPlanList ) throws Exception;
	Workbook employeWiseActionPlanExportExcel(ActionPlanParams actionPlanParams,JSONObject colmodel,String format) throws NoDataFoundException, Exception;
	//public List<ComboBox> getmompillargroupbasedemployee(String pillarid,String type, String flid, ComboFilter empComboFilter, String refid)throws Exception;
	public List<String[]> getActionPlanresponsibilitygrid(
			CommonFilter commonFilter)throws Exception;
	public GenTlActionplanmst getAllFillRemainerControl(String actPlanKeyId)throws Exception;
	 public List<String[]> getElementId(String loginflid, String loginlevel,
			  String loginElementid, String empId) throws Exception;
	 
	 
	 public abstract void GenTlActionplanmstDaoImplJwt(String jwtToken);
	
}

