package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AbnormalityDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlActionplandtlDao;
import com.akranta.tpm.dao.GenTlActionplanmstDao;
import com.akranta.tpm.dao.GenTlActplnNonemployeeDao;
import com.akranta.tpm.dao.GenTlResponsibilitylinkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlActionplandtlDaoImpl;
import com.akranta.tpm.dao.impl.GenTlActionplanmstDaoImpl;
import com.akranta.tpm.dao.impl.GenTlActplnNonemployeeDaoImpl;
import com.akranta.tpm.dao.impl.GenTlResponsibilitylinkDaoImpl;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActplnNonemployee;
import com.akranta.tpm.model.GenTlResponsibilitylink;
import com.akranta.tpm.service.ActionPlanRptService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.ActionPlanServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


public class ActionPlanRptServiceImpl implements ActionPlanRptService{
	private Validations validations ;
	private GenTlActionplanmstDao genTlActionplanmstDao;
	private GenTlActionplandtlDao genTlActionplandtlDao;
	private AbnormalityDao abndao;
	private GenTlResponsibilitylinkDao genTlResponsibilitylinkDao;
	private GenTlActplnNonemployeeDao  enTlActplnNonemployeeDao;
	private CommonFilterDao commonFilterDao ;
	private ActionPlanServiceApi  actionplanServiceApi;
	
	public ActionPlanRptServiceImpl(DBActionTemplate dbActionTemplate) {
		genTlActionplanmstDao=new GenTlActionplanmstDaoImpl(dbActionTemplate);
		genTlActionplandtlDao=new GenTlActionplandtlDaoImpl(dbActionTemplate);
		genTlResponsibilitylinkDao=new GenTlResponsibilitylinkDaoImpl(dbActionTemplate);
		enTlActplnNonemployeeDao =new GenTlActplnNonemployeeDaoImpl(dbActionTemplate);
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);	
	}
	
	
	 public void ActionPlanRptServiceImplJwt(String JwtToken){
	    	try{
	    	genTlActionplanmstDao.GenTlActionplanmstDaoImplJwt(JwtToken);
	    	actionplanServiceApi = new ActionPlanServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
	 
	@Override
	public List<String[]> getActionPlanDetail(CommonFilter commonFilter)
			throws Exception {
		
		return this.genTlActionplanmstDao.getActionPlanDetail(commonFilter);
		
	}

	@Override
	public List<String[]> getActionPlanresponsibilitygrid(
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Chk 44 ");
		return this.genTlActionplanmstDao.getActionPlanresponsibilitygrid(commonFilter);
	}

	@Override 
	public GenTlActionplanmst getActionPlanDetailAdd(String keyId)
			throws Exception {
		return this.genTlActionplanmstDao.getActionPlanDetailAdd(keyId);
	}

	@Override
	public List<ComboBox> getmomgroupbasedemployee(ComboFilter empComboFilter,
			String string) throws Exception {
		// TODO Auto-generated method stub
		empComboFilter.setIdField("EMPM_KEYID");	
		empComboFilter.setCodeField("EMPM_CODE");
		empComboFilter.setNameField("EMPM_NAME");
			
		empComboFilter.setTableName("GEN_TL_EMPLOYEEMST,GEN_TL_MOMATTENDANCE");
		
		return commonFilterDao.fillComboValues(empComboFilter);
	}
	
	/*@Override
	public List<ComboBox> getmompillargroupbasedemployee(ComboFilter empComboFilter,
			String string, String pillarid, String type, String flid, String refid) throws Exception {
		// TODO Auto-generated method stub
		
		return genTlActionplanmstDao.getmompillargroupbasedemployee(pillarid, type, flid, empComboFilter, refid);
	}*/

	@Override
	public List<ComboBox> getmompillargroupbasedemployee(
			ComboFilter empComboFilter, String string) throws Exception {
		// TODO Auto-generated method stub
		empComboFilter.setIdField("EMPM_KEYID");	 
		empComboFilter.setCodeField("EMPM_CODE");
		empComboFilter.setNameField("EMPM_NAME");
		
		//empComboFilter.setTableName("GEN_TL_EMPLOYEEMST,ADM_tl_rolemst,gen_mv_flidhierarchy,GEN_TL_MOMATTENDANCE,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST");
		//empComboFilter.setTableName("GEN_TL_EMPLOYEEMST JOIN GEN_TL_FNLNROLETEAM ON empm_keyid = FRT_EMPM_KEYID JOIN gen_mv_flidhierarchy ON FLID = FRT_FNLN_KEYID ");
		empComboFilter.setTableName("GEN_TL_EMPLOYEEMST   JOIN GEN_TL_FNLNROLETEAM ON empm_keyid = FRT_EMPM_KEYID JOIN ADM_tl_rolemst ON FRT_ROLE_KEYID = ROLE_KEYID JOIN gen_mv_flidhierarchy ON FLID = FRT_FNLN_KEYID ");
		
		return commonFilterDao.fillComboValues(empComboFilter);
	}
	
	
	@Override
	public List<ComboBox> getmomjhmemberemployee(ComboFilter empComboFilter,
			String string) throws Exception {
		// TODO Auto-generated method stub
		empComboFilter.setIdField("EMPM_KEYID");	 
		empComboFilter.setCodeField("EMPM_CODE");
		empComboFilter.setNameField("EMPM_NAME");
		
		empComboFilter.setTableName("GEN_TL_EMPLOYEEMST,ADM_tl_rolemst,gen_mv_flidhierarchy,GEN_TL_FNLNROLETEAM");
		
		return commonFilterDao.fillComboValues(empComboFilter);
	}
	
	@Override
	public GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions, Exception 
	{
		try 
		{	CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml="ActionPlanRpt";
			validations.validate(genTlActionplandtl,xml,validationsFor);
			fillValues(genTlActionplanmst,genTlActionplandtl);
			CommonMessage.debugMsg("After Fill Values");
			CommonMessage.debugMsg("After Fill genTlActionplandtl.getApldStatus:"+genTlActionplandtl.getApldStatus());
//			return genTlActionplanmstDao.create(genTlActionplanmst,genTlActionplandtl);
			return actionplanServiceApi.save(genTlActionplanmst, genTlActionplandtl);
		}
		catch (ValidationExceptions e)
		{
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public GenTlActionplanmst update(GenTlActionplanmst existGenTlActionplanmst,GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)
	    throws BusinessApplicationExceptions, Exception {
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "update";
		String xml="ActionPlanRpt";
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xml);
		validations.validate(newGenTlActionplandtl,xml,validationsFor);
		fillValues(genTlActionplanmst,newGenTlActionplandtl);
		//return genTlActionplanmstDao.update(existGenTlActionplanmst,genTlActionplanmst,newGenTlActionplandtl);
		return actionplanServiceApi.save(genTlActionplanmst, newGenTlActionplandtl);
	}

	@Override
	public GenTlActionplanmst delete(GenTlActionplanmst oldgenTlActionplanmst)
			throws Exception {
		
		return genTlActionplanmstDao.delete(oldgenTlActionplanmst);
	}
	private void fillValues(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws Exception 
	{
		CommonMessage.debugMsg("In Side fill values::::::;");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		genTlActionplanmst.setAplmActive("Y");	
		genTlActionplanmst.setAplmCreatedon(dateTime);
		genTlActionplanmst.setAplmModifiedon(dateTime);
		
		CommonMessage.debugMsg(" Master update servlet:::: In ServiceImpl :: "+ genTlActionplanmst.getAplmKeyid());
		CommonMessage.debugMsg(" MasterRefid :: In ServiceImpl :: "+ genTlActionplanmst.getAplmMasterrefid());
		CommonMessage.debugMsg(" DetailRefid :: In ServiceImpl :: "+ genTlActionplanmst.getAplmDetailrefid());
		CommonMessage.debugMsg(" Refdoctype() :: In ServiceImpl :: "+ genTlActionplanmst.getAplmRefdoctype());
		
		if(genTlActionplanmst.getAplmPillarid()==null)
			genTlActionplanmst.setAplmPillarid("{}");		
		if(genTlActionplanmst.getAplmMasterrefid()==null)
			genTlActionplanmst.setAplmMasterrefid("{}");
		if(genTlActionplanmst.getAplmDetailrefid()==null)
			genTlActionplanmst.setAplmDetailrefid("{}");
		if(genTlActionplanmst.getAplmMaintask()==null)
			genTlActionplanmst.setAplmMaintask("{}");
		if(genTlActionplanmst.getAplmRefdoctype()==null)
			genTlActionplanmst.setAplmRefdoctype("{}");
		if(genTlActionplanmst.getAplmStatus()==null)
			genTlActionplanmst.setAplmStatus("P");
		if(genTlActionplanmst.getAplmRemarks()==null)
			genTlActionplanmst.setAplmRemarks("{}");

		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate())) {
			genTlActionplanmst.setAplmPlandate(dateTime);
		}else {
			genTlActionplanmst.setAplmPlandate(CommonFunctions.pg_getDateTimeFromDate(genTlActionplanmst.getAplmPlandate()));
		}
			
		if(genTlActionplanmst.getAplmTempfiled2()==null)
			genTlActionplanmst.setAplmTempfiled2("-");
		if(genTlActionplanmst.getAplmTempfiled3()==null)
			genTlActionplanmst.setAplmTempfiled3("-");
		if(genTlActionplanmst.getAplmTempfiled4()==null)
			genTlActionplanmst.setAplmTempfiled4("-");
		if(genTlActionplanmst.getAplmTempfiled5()==null)
			genTlActionplanmst.setAplmTempfiled5("-");	
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		if(genTlActionplanmst.getAplmFlid()==null)
			genTlActionplanmst.setAplmFlid("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		
		fillActionplandtlValues(newGenTlActionplandtl,genTlActionplanmst);		
		genTlActionplanmst.setAplmRemarks(newGenTlActionplandtl.getApldRemarks());
		CommonMessage.debugMsg("End Of  Fill values");
	}

	private void fillActionplandtlValues(GenTlActionplandtl genTlActionplandtl,GenTlActionplanmst genTlActionplanmst) {
		
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldAplmKeyid()))
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedby()))
			genTlActionplandtl.setApldCreatedby(genTlActionplanmst.getAplmCreatedby());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActionplan()))
			genTlActionplandtl.setApldActionplan("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldHowtodo()))
			genTlActionplandtl.setApldHowtodo("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCountermeasure()))
			genTlActionplandtl.setApldCountermeasure("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldRemarks()))
			genTlActionplandtl.setApldRemarks("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldResponsibility()))
			genTlActionplandtl.setApldResponsibility("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldStatus()))
			genTlActionplandtl.setApldStatus("P");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTargetdate())) {
			genTlActionplandtl.setApldTargetdate(CommonFunctions.pg_dateTimeNow());
		}else {
			genTlActionplandtl.setApldTargetdate(CommonFunctions.pg_getDateTimeFromDate(genTlActionplandtl.getApldTargetdate()));
		}
			
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTradeid()))
			genTlActionplandtl.setApldTradeid("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompletedby()))
			genTlActionplandtl.setApldCompletedby("{}");
		
		if("C".equals(genTlActionplandtl.getApldStatus())) {
			genTlActionplandtl.setApldCompleatedon(CommonFunctions.pg_getDateTimeFromDate(genTlActionplandtl.getApldCompleatedon()));
		}else {
			genTlActionplandtl.setApldCompleatedon(Constants.pgFutureNullDateTime);
		}
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompleatedon()))
			genTlActionplandtl.setApldCompleatedon(Constants.pgFutureNullDateTime);
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldModifiedon()))
			genTlActionplandtl.setApldModifiedon(CommonFunctions.pg_dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedon()))
			genTlActionplandtl.setApldCreatedon(CommonFunctions.pg_dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldOthers()))
			genTlActionplandtl.setApldOthers("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled2()))
			genTlActionplandtl.setApldTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled3()))
			genTlActionplandtl.setApldTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled4()))
			genTlActionplandtl.setApldTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled5()))
			genTlActionplandtl.setApldTempfiled5("-");
		
		//return genTlActionplandtl;
	}


	@Override
	public List<String[]> getActionPlanReport(CommonFilter commonFilter)
			throws Exception {
		return  genTlActionplanmstDao.getActionPlanReport(commonFilter);
	}



	@Override
	public GenTlActionplanmst getAllFillControl(GenTlActionplanmst genTlActionplanmst) throws Exception {		
		return genTlActionplanmstDao.getAllFillControl(genTlActionplanmst);
	}

	@Override
	public GenTlActionplanmst getAllFillRemainerControl(String actPlanKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		return genTlActionplanmstDao.getAllFillRemainerControl(actPlanKeyId);
	}

	@Override
	public Workbook actionPlanExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		
		return genTlActionplanmstDao.actionPlanExportExcel(commonFilter, colModel, rptFormat);
	}



	@Override
	public List<String[]> getMultiSelectEmp(CommonFilter commonFilter)
			throws Exception {
		return genTlActionplanmstDao.getMultiSelectEmp(commonFilter);
		
	}



	@Override
	public GenTlActionplandtl delete(GenTlActionplandtl oldGenTlActionplandtl)
			throws Exception {
		// TODO Auto-generated method stub
		//return genTlActionplandtlDao.delete(oldGenTlActionplandtl);
		return actionplanServiceApi.deleteActionPlan(oldGenTlActionplandtl);
	}



	@Override
	public Workbook actionPlanDetailExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return genTlActionplanmstDao.actionPlanDetailExportExcel(commonFilter, colmodel, format);
	}



	@Override
	public GenTlResponsibilitylink create(	GenTlResponsibilitylink genTlResponsibilitylink,GenTlResponsibilitylink existGenTlResponsibilitylink)throws Exception,ValidationExceptions,BusinessApplicationExceptions {
		validations.validate(genTlResponsibilitylink,"MultiselectEmplyee","create");
		CommonMessage.debugMsg(genTlResponsibilitylink.getRsplTargetdate() + " service impl");
		return genTlResponsibilitylinkDao.create(genTlResponsibilitylink);
	}
	@Override
	public List<String[]> getOthers(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return enTlActplnNonemployeeDao.getOthers(commonFilter); 
	}



	@Override
	public GenTlActplnNonemployee create(GenTlActplnNonemployee actplnNonemployee,GenTlActplnNonemployee existactplnNonemployee) throws Exception {
		fillGenTlActplnNonemployeeValues(actplnNonemployee, existactplnNonemployee);
		return enTlActplnNonemployeeDao.create(actplnNonemployee);
	}
	private void fillGenTlActplnNonemployeeValues(GenTlActplnNonemployee genTlActplnNonemployee, GenTlActplnNonemployee existactplnNonemployee) {
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactActive()))
			genTlActplnNonemployee.setNactActive("Y");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedon()))
			genTlActplnNonemployee.setNactCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedby()))
			genTlActplnNonemployee.setNactCreatedby("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactApldKeyid()))
			genTlActplnNonemployee.setNactApldKeyid("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedon()))
			genTlActplnNonemployee.setNactCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactModifiedon()))
			genTlActplnNonemployee.setNactModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactRefdocid()))
			genTlActplnNonemployee.setNactRefdocid("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactRemarks()))
			genTlActplnNonemployee.setNactRemarks("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled1()))
			genTlActplnNonemployee.setNactTempfiled1("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled2()))
			genTlActplnNonemployee.setNactTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled3()))
			genTlActplnNonemployee.setNactTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled4()))
			genTlActplnNonemployee.setNactTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled5()))
			genTlActplnNonemployee.setNactTempfiled5("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled6()))
			genTlActplnNonemployee.setNactTempfiled6("-");
	}
	
	public List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams) throws NoDataFoundException, Exception {
		return genTlActionplanmstDao.getAllEmployeActionPlans( actionPlanParams);
	}
/* public List<String[]>  getAllEmployeActionPlans(ActionPlanParams actionPlanParams, String flid,String Teamchk) throws NoDataFoundException, Exception {
	return genTlActionplanmstDao.getAllEmployeActionPlans( actionPlanParams,flid,Teamchk);
 }*/
	
	public void saveActionPlanCompletion(List<GenTlActionplandtl> genActionPlanList ) throws Exception{
		//genTlActionplanmstDao.saveActionPlanCompletion(genActionPlanList);
		actionplanServiceApi.saveCompletion(genActionPlanList);
	}
	public Workbook employeWiseActionPlanExportExcel(ActionPlanParams actionPlanParams,JSONObject colmodel,String format) throws NoDataFoundException, Exception{
		return genTlActionplanmstDao.employeWiseActionPlanExportExcel(actionPlanParams, colmodel, format);
	}
	 public List<String[]> getElementId(String loginflid, String loginlevel,
			  String loginElementid, String empId) throws Exception{
		  return genTlActionplanmstDao.getElementId(loginflid, loginlevel, loginElementid, empId);
	 }
	

	
	}
	

