package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlActplnNonemployee;
import com.akranta.tpm.model.GenTlResponsibilitylink;

public interface ActionPlanRptService {
	
	GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl) throws BusinessApplicationExceptions, Exception;

	GenTlActionplanmst update(GenTlActionplanmst existGenTlActionplanmst,GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws BusinessApplicationExceptions, Exception;

	GenTlActionplanmst delete(GenTlActionplanmst genTlActionplanmst)throws Exception;
	
	public List<String[]> getActionPlanDetail(CommonFilter commonFilter) throws Exception;
	public GenTlActionplanmst getActionPlanDetailAdd(String keyId) throws Exception;
	public List<String[]> getActionPlanReport(CommonFilter commonFilter) throws Exception;

	GenTlActionplanmst getAllFillControl(GenTlActionplanmst genTlActionplanmst)throws Exception;
	public Workbook actionPlanExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	List<String[]> getMultiSelectEmp(CommonFilter commonFilter)throws Exception;

	GenTlActionplandtl delete(GenTlActionplandtl oldGenTlActionplandtl) throws Exception;

	Workbook actionPlanDetailExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception;

	GenTlResponsibilitylink create(GenTlResponsibilitylink genTlResponsibilitylink,GenTlResponsibilitylink existGenTlResponsibilitylink)throws Exception,ValidationExceptions,BusinessApplicationExceptions;

	List<String[]> getOthers(CommonFilter commonFilter)throws Exception;

	GenTlActplnNonemployee create(GenTlActplnNonemployee actplnNonemployee,GenTlActplnNonemployee existactplnNonemployee)throws Exception;
	
	List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams) throws NoDataFoundException, Exception ;
	//List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams,String flid,String Teamchk) throws NoDataFoundException, Exception ;
	void saveActionPlanCompletion(List<GenTlActionplandtl> genActionPlanList ) throws Exception;
	Workbook employeWiseActionPlanExportExcel(ActionPlanParams actionPlanParams,JSONObject colmodel,String format) throws NoDataFoundException, Exception;

	List<ComboBox> getmomgroupbasedemployee(ComboFilter empComboFilter,
			String string)throws Exception;

	//List<ComboBox> getmompillargroupbasedemployee(ComboFilter empComboFilter,
		//	String string, String pillarid, String type, String flid, String refid)throws Exception;
	
	List<ComboBox> getmompillargroupbasedemployee(ComboFilter empComboFilter,
			String string)throws Exception;

	List<ComboBox> getmomjhmemberemployee(ComboFilter empComboFilter,String string)throws Exception;

	List<String[]> getActionPlanresponsibilitygrid(CommonFilter commonFilter)throws Exception;

	public GenTlActionplanmst getAllFillRemainerControl(String actPlanKeyId)throws Exception;
	  public List<String[]> getElementId(String loginflid, String loginlevel,
			  String loginElementid, String empId) throws Exception;

	  public void ActionPlanRptServiceImplJwt(String JwtToken);




}
