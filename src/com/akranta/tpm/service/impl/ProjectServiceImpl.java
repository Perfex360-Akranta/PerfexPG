package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.taglibs.standard.lang.jpath.expression.ValidationException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KznTlProjectChecklistLinkDao;
import com.akranta.tpm.dao.ProjectDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KznTlProjectChecklistLinkDaoImpl;
import com.akranta.tpm.dao.impl.ProjectDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDmcfipworkflow;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlDmcfipcreationmst;
import com.akranta.tpm.model.KznTlProjectChecklistLink;
import com.akranta.tpm.model.KznTlProjectKaizenLink;
import com.akranta.tpm.model.KznTlProjectKpiLink;
import com.akranta.tpm.model.KznTlProjectResourceLink;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.KznTlProjectdmaicstatus;
import com.akranta.tpm.model.KznTlProjectmaicMileMst;
import com.akranta.tpm.service.ProjectService;
import com.akranta.tpm.service.api.ActionPlanServiceApi;
import com.akranta.tpm.service.api.ProjectServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao;
	private Validations validation;
	private CommonFilterDao commonFilterDao ;
	private KznTlProjectChecklistLinkDao kznTlProjectChecklistLinkDao;   
	private ProjectServiceApi projectServiceApi;
	public ProjectServiceImpl(DBActionTemplate dbActionTemplate) {
		projectDao = new ProjectDaoImpl(dbActionTemplate);
		
		validation = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		kznTlProjectChecklistLinkDao = new KznTlProjectChecklistLinkDaoImpl(dbActionTemplate);
	}
	
	 public void ProjectServiceImplJwt(String JwtToken){
	    	try{
	    		projectDao.ProjectDaoImplJwt(JwtToken);
	    		projectServiceApi = new ProjectServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
	@Override
	public List<String[]> getResources(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getResources(commonFilter);
	}

	@Override
	public List<String[]> getMilesStone(String keyid)
			throws Exception {
		// TODO Auto-generated method stub
		//return this.projectDao.getMilesStone(keyid);
		return projectServiceApi.getProjectMileStones(keyid);
	}

	@Override
	public List<String[]> getKaizen(String masterId, String flid) throws Exception {
		// TODO Auto-generated method stub
		//return this.projectDao.getKaizen(masterId,flid);
		return projectServiceApi.getProjectKaizen(flid, masterId);
	}

	@Override
	public List<String[]> getDmaic(String projectId) throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getDmaic(projectId);
	}

	
	public List<String[]> getProject(CommonFilter commonFilter) throws Exception {
		return this.projectDao.getProject(commonFilter);
	}
	
	public List<String[]> getProjectview(CommonFilter commonFilter) throws Exception {
		return this.projectDao.getProjectview(commonFilter);
	}
	/**/
	public List<String[]> getChangeprolead(CommonFilter commonFilter) throws Exception {
		return this.projectDao.getChangeprolead(commonFilter);
	}
	
	
	public List<String[]> getprojectchamp(String Keyid) throws Exception
	{
		return this.projectDao.getgetprojectchampD(Keyid);
	}

	public Workbook getChangeproleadExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("service");
		return this.projectDao.getChangeproleadExcel(colmodel,format,commonFilter);
	}
	
	public String singleResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return   projectDao.singleResponsiblty(Projectkeyid,responsevalue,oldemployeeid);
	}
	
	/**/
	public List<String[]> getChangeproleader(CommonFilter commonFilter) throws Exception {
		return this.projectDao.getChangeproleader(commonFilter);
	}
	
	
	public List<String[]> getprojectleader(String Keyid) throws Exception
	{
		return this.projectDao.getgetprojectleadD(Keyid);
	}

	public Workbook getChangeproleaderExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("service");
		return this.projectDao.getChangeproleaderExcel(colmodel,format,commonFilter);
	}
	
	public String singleleadResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return   projectDao.singleleadResponsiblty(Projectkeyid,responsevalue,oldemployeeid);
	}
	/**/
	@Override
	public List<String[]> getAuthorization(String type) throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getAuthorization(type);
	}
	@Override
	public List<String[]> getApproval(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getApproval(commonFilter);
	}
	@Override
	public List<String[]> getKpi(String keyId) throws Exception {
		// TODO Auto-generated method stub
		//return this.projectDao.getKpi(keyId);
		return projectServiceApi.getProjectKpi(keyId);
	}
	@Override
	public KznTlProjectcreationmst create(KznTlProjectcreationmst newKznTlProjectcreationmst,KznTlProjectcreationmst existKznTlProjectcreationmst)throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		validation.validate(newKznTlProjectcreationmst, "ProjectCreationValidation","create");
		
		CommonMessage.debugMsg(" In side the createa method");
		fillValues(newKznTlProjectcreationmst,existKznTlProjectcreationmst);
		CommonMessage.debugMsg(" In side the create after filling method");
		//return projectDao.create(newKznTlProjectcreationmst);	
		return projectServiceApi.insertRecord(newKznTlProjectcreationmst);
	}
	@Override
	public KznTlProjectcreationmst update(KznTlProjectcreationmst newKznTlProjectcreationmst,KznTlProjectcreationmst existKznTlProjectcreationmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		 
		/* if(newKznTlProjectcreationmst.getKzpmDefinestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmMeasurestage()!=null
			|| newKznTlProjectcreationmst.getKzpmAnalysestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmImprovestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmControlstage()!=null 
			|| newKznTlProjectcreationmst.getKzpmClosurestage()!=null 
			){
		*/
		 if("modify".equalsIgnoreCase(newKznTlProjectcreationmst.getMode()) || "define".equalsIgnoreCase(newKznTlProjectcreationmst.getMode())){
			/// newKznTlProjectcreationmst.setMode("define");
			 validation.validate(newKznTlProjectcreationmst, "ProjectCreationValidation","define");
			
		 }
		 else if("finance".equalsIgnoreCase(newKznTlProjectcreationmst.getMode()) ){
			 validation.validate(newKznTlProjectcreationmst, "ProjectCreationValidation","finance");
		 }
		else	
			validation.validate(newKznTlProjectcreationmst, "ProjectCreationValidation","update");
		
		fillValues(newKznTlProjectcreationmst,existKznTlProjectcreationmst);
		//return projectDao.update(newKznTlProjectcreationmst);
		return projectServiceApi.updateRecord(newKznTlProjectcreationmst);
	}
	
	@Override
	public KznTlProjectcreationmst updateMAICStatus(KznTlProjectcreationmst kznTlProjectcreationmst,String stage)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		 
		
		//return projectDao.updateMAICStatus(kznTlProjectcreationmst,stage);
		return projectServiceApi.updateMAICStatus(kznTlProjectcreationmst, stage);
	}
	@Override
	public KznTlDmcfipcreationmst createdmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,KznTlDmcfipcreationmst existKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow)throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		CommonMessage.debugMsg("In service Impl");
		//validation.validate(newKznTlDmcfipcreationmst, "dmcProjectCreationValidation","create");
		validation.validate(newGenTlDmcfipworkflow, "dmcProjectCreationValidation","create");
		CommonMessage.debugMsg("In service Impl1");
		fillValues(newKznTlDmcfipcreationmst,existKznTlDmcfipcreationmst);
		CommonMessage.debugMsg("In service Impl2");
		fillValues(newGenTlDmcfipworkflow);
		CommonMessage.debugMsg("In service Impl3");
		return projectDao.createdmc(newKznTlDmcfipcreationmst,newGenTlDmcfipworkflow);		
	}
	@Override
	public KznTlDmcfipcreationmst updatedmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,KznTlDmcfipcreationmst existKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow,GenTlDmcfipworkflow existGenTlDmcfipworkflow)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		 
		/* if(newKznTlProjectcreationmst.getKzpmDefinestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmMeasurestage()!=null
			|| newKznTlProjectcreationmst.getKzpmAnalysestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmImprovestage()!=null 
			|| newKznTlProjectcreationmst.getKzpmControlstage()!=null 
			|| newKznTlProjectcreationmst.getKzpmClosurestage()!=null 
			){
		*/
		 if("modify".equalsIgnoreCase(newKznTlDmcfipcreationmst.getMode()) || "define".equalsIgnoreCase(newKznTlDmcfipcreationmst.getMode())){
			/// newKznTlProjectcreationmst.setMode("define");
			 //validation.validate(newKznTlDmcfipcreationmst, "dmcProjectCreationValidation","define");
			
		 }
		 else if("finance".equalsIgnoreCase(newKznTlDmcfipcreationmst.getMode()) ){
			 //validation.validate(newKznTlDmcfipcreationmst, "dmcProjectCreationValidation","finance");
		 }
		else	
			//validation.validate(newKznTlDmcfipcreationmst, "dmcProjectCreationValidation","update");
		    validation.validate(newGenTlDmcfipworkflow, "dmcProjectCreationValidation","update");
		
		fillValues(newKznTlDmcfipcreationmst,newKznTlDmcfipcreationmst);
		fillValues(newGenTlDmcfipworkflow);
		return projectDao.updatedmc(newKznTlDmcfipcreationmst,newGenTlDmcfipworkflow);
	}
	private KznTlProjectcreationmst fillValues(KznTlProjectcreationmst newKznTlProjectcreationmst,KznTlProjectcreationmst existKznTlProjectcreationmst) throws Exception
	{
		CommonMessage.debugMsg("Inside fill Values");
		newKznTlProjectcreationmst.setKzpmActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if( newKznTlProjectcreationmst.getKzpmBenefits() == null )
			newKznTlProjectcreationmst.setKzpmBenefits("{}");
		if( newKznTlProjectcreationmst.getKzpmArea() == null )
			newKznTlProjectcreationmst.setKzpmArea("{}");
		if( newKznTlProjectcreationmst.getKzpmBusinesscase() == null )
			newKznTlProjectcreationmst.setKzpmBusinesscase("{}");		
		if( newKznTlProjectcreationmst.getKzpmEnddate() == null ){
			newKznTlProjectcreationmst.setKzpmEnddate(dateTime);
		}else {
			newKznTlProjectcreationmst.setKzpmEnddate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmEnddate()));
		}
		
		if( newKznTlProjectcreationmst.getKzpmStartdate() == null ) {
			newKznTlProjectcreationmst.setKzpmStartdate(dateTime);
		}else {
			newKznTlProjectcreationmst.setKzpmStartdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmStartdate()));
		}
			
		if( newKznTlProjectcreationmst.getKzpmFlid() == null )
			newKznTlProjectcreationmst.setKzpmFlid("{}");
		if( newKznTlProjectcreationmst.getKzpmGoalobj() == null )
			newKznTlProjectcreationmst.setKzpmGoalobj("{}");
		if( newKznTlProjectcreationmst.getKzpmScopeconst() == null )
			newKznTlProjectcreationmst.setKzpmScopeconst("{}");
		if( newKznTlProjectcreationmst.getKzpmClosurestage()== null )
			newKznTlProjectcreationmst.setKzpmClosurestage("-");
		if( newKznTlProjectcreationmst.getKzpmImprCategory() == null )
			newKznTlProjectcreationmst.setKzpmImprCategory("-");
		if( newKznTlProjectcreationmst.getKzpmIstangible() == null )
			newKznTlProjectcreationmst.setKzpmIstangible("N");
		if( newKznTlProjectcreationmst.getKzpmIsintangible() == null )
			newKznTlProjectcreationmst.setKzpmIsintangible("N");
		
		if( newKznTlProjectcreationmst.getKzpmVerifiedamnt() == null )
			newKznTlProjectcreationmst.setKzpmVerifiedamnt("-1");
		
		if( newKznTlProjectcreationmst.getKzpmFinalamnt() == null )
			newKznTlProjectcreationmst.setKzpmFinalamnt("-1");
		
		if( newKznTlProjectcreationmst.getKzpmAmtverifyremarks() == null )
			newKznTlProjectcreationmst.setKzpmAmtverifyremarks("-");
		
		if( !UIUtils.isValidKeyId(newKznTlProjectcreationmst.getKzpmWave()) )
			newKznTlProjectcreationmst.setKzpmWave("0");
		
		if( newKznTlProjectcreationmst.getKzpmoldresponsibility() == null )
			newKznTlProjectcreationmst.setKzpmoldresponsibility("-");
		if( newKznTlProjectcreationmst.getKzpmBelt() == null )
			newKznTlProjectcreationmst.setKzpmBelt("-");
		if( newKznTlProjectcreationmst.getKzpmTempfield4() == null )
			newKznTlProjectcreationmst.setKzpmTempfield4("-");
		if( newKznTlProjectcreationmst.getKzpmSavings() == null )
			newKznTlProjectcreationmst.setKzpmSavings("{}");
		if( newKznTlProjectcreationmst.getKzpmProblemstatement() == null )
			newKznTlProjectcreationmst.setKzpmProblemstatement("{}");
		if( newKznTlProjectcreationmst.getKzpmProjectchamp() == null )
			newKznTlProjectcreationmst.setKzpmProjectchamp("{}");
		if( newKznTlProjectcreationmst.getKzpmProjectmetrics() == null )
			newKznTlProjectcreationmst.setKzpmProjectmetrics("{}");
		if( newKznTlProjectcreationmst.getKzpmProjectname() == null )
			newKznTlProjectcreationmst.setKzpmProjectname("{}");
		if( newKznTlProjectcreationmst.getKzpmProjectno() == null )
			newKznTlProjectcreationmst.setKzpmProjectno("{}");
		if( newKznTlProjectcreationmst.getKzpmCreatedby() == null )
			newKznTlProjectcreationmst.setKzpmCreatedby("{}");
		
		
		
		//if( newKznTlProjectcreationmst.getKzpmKeyid() != null ){
		if (existKznTlProjectcreationmst!=null){
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmCreatedon()))
				newKznTlProjectcreationmst.setKzpmCreatedon(CommonFunctions.pg_getPGTimeStampFromDateTime(existKznTlProjectcreationmst.getKzpmCreatedon()));
			else
				newKznTlProjectcreationmst.setKzpmCreatedon(dateTime);	
			
			/*if(! UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmCreatedby()))
				newKznTlProjectcreationmst.setKzpmCreatedby(existKznTlProjectcreationmst.getKzpmCreatedby());*/
			
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmDefinestage())){
				newKznTlProjectcreationmst.setKzpmDefinestage(existKznTlProjectcreationmst.getKzpmDefinestage());
				if( "define".equals( newKznTlProjectcreationmst.getMode() ) && "E".equals(existKznTlProjectcreationmst.getKzpmDefinestage()))
					newKznTlProjectcreationmst.setKzpmDefinestage("P");
			}	
			else{
				if( "define".equals( newKznTlProjectcreationmst.getMode()))
					newKznTlProjectcreationmst.setKzpmDefinestage("P");
				else
					newKznTlProjectcreationmst.setKzpmDefinestage("-");
			}	
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmMeasurestage()))
				newKznTlProjectcreationmst.setKzpmMeasurestage(existKznTlProjectcreationmst.getKzpmMeasurestage());
			else
				newKznTlProjectcreationmst.setKzpmMeasurestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmAnalysestage()))
				newKznTlProjectcreationmst.setKzpmAnalysestage(existKznTlProjectcreationmst.getKzpmAnalysestage());
			else
				newKznTlProjectcreationmst.setKzpmAnalysestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmImprovestage()))
				newKznTlProjectcreationmst.setKzpmImprovestage(existKznTlProjectcreationmst.getKzpmImprovestage());
			else
				newKznTlProjectcreationmst.setKzpmImprovestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmControlstage()))
				newKznTlProjectcreationmst.setKzpmControlstage(existKznTlProjectcreationmst.getKzpmControlstage());
			else
				newKznTlProjectcreationmst.setKzpmControlstage("-");
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmDefinecompleteddate()))
				newKznTlProjectcreationmst.setKzpmDefinecompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmDefinecompleteddate()));	
			else
			   newKznTlProjectcreationmst.setKzpmDefinecompleteddate(Constants.pgFutureNullDateTime);	
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmMeasurecompleteddate()))
				newKznTlProjectcreationmst.setKzpmMeasurecompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmMeasurecompleteddate()));
			else
				newKznTlProjectcreationmst.setKzpmMeasurecompleteddate(Constants.pgFutureNullDateTime);
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmAnalysecompleteddate()))
				newKznTlProjectcreationmst.setKzpmAnalysecompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmAnalysecompleteddate()));
			else
				newKznTlProjectcreationmst.setKzpmAnalysecompleteddate(Constants.pgFutureNullDateTime);
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmImprovecompleteddate()))
				newKznTlProjectcreationmst.setKzpmImprovecompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmImprovecompleteddate()));
			else
				newKznTlProjectcreationmst.setKzpmImprovecompleteddate(Constants.pgFutureNullDateTime);
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmControlcompleteddate()))
				newKznTlProjectcreationmst.setKzpmControlcompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmControlcompleteddate()));
			else
				newKznTlProjectcreationmst.setKzpmControlcompleteddate(Constants.pgFutureNullDateTime);
			
			if( UIUtils.isValidKeyId(existKznTlProjectcreationmst.getKzpmClosurecompleteddate()))
				newKznTlProjectcreationmst.setKzpmClosurecompleteddate(CommonFunctions.pg_getDateTimeFromDate(existKznTlProjectcreationmst.getKzpmClosurecompleteddate()));
			else
				newKznTlProjectcreationmst.setKzpmClosurecompleteddate(Constants.pgFutureNullDateTime);
		}
		else{
			if( newKznTlProjectcreationmst.getKzpmDefinecompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmDefinecompleteddate(Constants.pgFutureNullDateTime);
			if( newKznTlProjectcreationmst.getKzpmMeasurecompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmMeasurecompleteddate(Constants.pgFutureNullDateTime);
			if( newKznTlProjectcreationmst.getKzpmAnalysecompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmAnalysecompleteddate(Constants.pgFutureNullDateTime);
			if( newKznTlProjectcreationmst.getKzpmImprovecompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmImprovecompleteddate(Constants.pgFutureNullDateTime);
			if( newKznTlProjectcreationmst.getKzpmControlcompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmControlcompleteddate(Constants.pgFutureNullDateTime);
			if( newKznTlProjectcreationmst.getKzpmClosurecompleteddate() == null )
				newKznTlProjectcreationmst.setKzpmClosurecompleteddate(Constants.pgFutureNullDateTime);
			
			newKznTlProjectcreationmst.setKzpmCreatedon(dateTime);
			newKznTlProjectcreationmst.setKzpmDefinestage("-");
			newKznTlProjectcreationmst.setKzpmMeasurestage("-");
			newKznTlProjectcreationmst.setKzpmAnalysestage("-");
			newKznTlProjectcreationmst.setKzpmImprovestage("-");
			newKznTlProjectcreationmst.setKzpmControlstage("-");
			
			
		}
		
		
			newKznTlProjectcreationmst.setKzpmDefinetargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmDefinetargetdate()));
		
			newKznTlProjectcreationmst.setKzpmMeasuretargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmMeasuretargetdate()));
	
			newKznTlProjectcreationmst.setKzpmAnalysetargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmAnalysetargetdate()));
	
			newKznTlProjectcreationmst.setKzpmImprovetargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmImprovetargetdate()));
		
			newKznTlProjectcreationmst.setKzpmControltargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmControltargetdate()));
		
			newKznTlProjectcreationmst.setKzpmClosuretargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectcreationmst.getKzpmClosuretargetdate()));
		
			CommonMessage.debugMsg(" 1 ");
		
			newKznTlProjectcreationmst.setKzpmModifiedon(dateTime);
		
			CommonMessage.debugMsg(" 2 ");
		
		return newKznTlProjectcreationmst; 
		
	}
	private KznTlDmcfipcreationmst fillValues(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,KznTlDmcfipcreationmst existKznTlDmcfipcreationmst) throws Exception
	{
		CommonMessage.debugMsg("Inside fill Values");
		newKznTlDmcfipcreationmst.setDmcmActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if( newKznTlDmcfipcreationmst.getDmcmBenefits() == null )
			newKznTlDmcfipcreationmst.setDmcmBenefits("{}");
		if( newKznTlDmcfipcreationmst.getDmcmArea() == null )
			newKznTlDmcfipcreationmst.setDmcmArea("{}");
		if( newKznTlDmcfipcreationmst.getDmcmBusinesscase() == null )
			newKznTlDmcfipcreationmst.setDmcmBusinesscase("{}");		
		if( newKznTlDmcfipcreationmst.getDmcmEnddate() == null )
			newKznTlDmcfipcreationmst.setDmcmEnddate(dateTime);
		if( newKznTlDmcfipcreationmst.getDmcmStartdate() == null )
			newKznTlDmcfipcreationmst.setDmcmStartdate(dateTime);
		if( newKznTlDmcfipcreationmst.getDmcmFlid() == null )
			newKznTlDmcfipcreationmst.setDmcmFlid("{}");
		if( newKznTlDmcfipcreationmst.getDmcmGoalobj() == null )
			newKznTlDmcfipcreationmst.setDmcmGoalobj("{}");
		if( newKznTlDmcfipcreationmst.getDmcmScopeconst() == null )
			newKznTlDmcfipcreationmst.setDmcmScopeconst("{}");
		if( newKznTlDmcfipcreationmst.getDmcmClosurestage()== null )
			newKznTlDmcfipcreationmst.setDmcmClosurestage("-");
		if( newKznTlDmcfipcreationmst.getDmcmImprcategory() == null )
			newKznTlDmcfipcreationmst.setDmcmImprcategory("-");
		if( newKznTlDmcfipcreationmst.getDmcmIstangible() == null )
			newKznTlDmcfipcreationmst.setDmcmIstangible("N");
		if( newKznTlDmcfipcreationmst.getDmcmIsintangible() == null )
			newKznTlDmcfipcreationmst.setDmcmIsintangible("N");
		
		if( newKznTlDmcfipcreationmst.getDmcmVerifiedamnt() == null )
			newKznTlDmcfipcreationmst.setDmcmVerifiedamnt("0");
		
		if( newKznTlDmcfipcreationmst.getDmcmAmtverifyremarks() == null )
			newKznTlDmcfipcreationmst.setDmcmAmtverifyremarks("-");
		
		if( !UIUtils.isValidKeyId(newKznTlDmcfipcreationmst.getDmcmWave()) )
			newKznTlDmcfipcreationmst.setDmcmWave("0");
		
		if( newKznTlDmcfipcreationmst.getDmcmOldresponsibility() == null )
			newKznTlDmcfipcreationmst.setDmcmOldresponsibility("-");
		if( newKznTlDmcfipcreationmst.getDmcmBelt()==null)
			newKznTlDmcfipcreationmst.setDmcmBelt("-");
		if( newKznTlDmcfipcreationmst.getDmcmTempfield4() == null )
			newKznTlDmcfipcreationmst.setDmcmTempfield4("-");
		if( newKznTlDmcfipcreationmst.getDmcmSavings() == null )
			newKznTlDmcfipcreationmst.setDmcmSavings("{}");
		if( newKznTlDmcfipcreationmst.getDmcmProblemstatement() == null )
			newKznTlDmcfipcreationmst.setDmcmProblemstatement("{}");
		if( newKznTlDmcfipcreationmst.getDmcmProjectchamp() == null )
			newKznTlDmcfipcreationmst.setDmcmProjectchamp("{}");
		if( newKznTlDmcfipcreationmst.getDmcmProjectmetrics() == null )
			newKznTlDmcfipcreationmst.setDmcmProjectmetrics("{}");
		if( newKznTlDmcfipcreationmst.getDmcmProjectname() == null )
			newKznTlDmcfipcreationmst.setDmcmProjectname("{}");
		if( newKznTlDmcfipcreationmst.getDmcmProjectno() == null )
			newKznTlDmcfipcreationmst.setDmcmProjectno("{}");
		if( newKznTlDmcfipcreationmst.getDmcmCreatedby() == null )
			newKznTlDmcfipcreationmst.setDmcmCreatedby("{}");
		//if( newKznTlDmcfipcreationmst.getDmcmKeyid() != null ){
		if (existKznTlDmcfipcreationmst!=null){
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmCreatedon()))
				newKznTlDmcfipcreationmst.setDmcmCreatedon(existKznTlDmcfipcreationmst.getDmcmCreatedon());
			else
				newKznTlDmcfipcreationmst.setDmcmCreatedon(dateTime);	
			
			/*if(! UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmCreatedby()))
				newKznTlDmcfipcreationmst.setDmcmCreatedby(existKznTlDmcfipcreationmst.getDmcmCreatedby());*/
			
			
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmDefinestage())){
				newKznTlDmcfipcreationmst.setDmcmDefinestage(existKznTlDmcfipcreationmst.getDmcmDefinestage());
				if( "define".equals( newKznTlDmcfipcreationmst.getMode() ) && "E".equals(existKznTlDmcfipcreationmst.getDmcmDefinestage()))
					newKznTlDmcfipcreationmst.setDmcmDefinestage("P");
			}	
			else{
				if( "define".equals( newKznTlDmcfipcreationmst.getMode()))
					newKznTlDmcfipcreationmst.setDmcmDefinestage("P");
				else
					newKznTlDmcfipcreationmst.setDmcmDefinestage("-");
			}	
			
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmMeasurestage()))
				newKznTlDmcfipcreationmst.setDmcmMeasurestage(existKznTlDmcfipcreationmst.getDmcmMeasurestage());
			else
				newKznTlDmcfipcreationmst.setDmcmMeasurestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmAnalysestage()))
				newKznTlDmcfipcreationmst.setDmcmAnalysestage(existKznTlDmcfipcreationmst.getDmcmAnalysestage());
			else
				newKznTlDmcfipcreationmst.setDmcmAnalysestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmImprovestage()))
				newKznTlDmcfipcreationmst.setDmcmImprovestage(existKznTlDmcfipcreationmst.getDmcmImprovestage());
			else
				newKznTlDmcfipcreationmst.setDmcmImprovestage("-");
			
			if( UIUtils.isValidKeyId(existKznTlDmcfipcreationmst.getDmcmControlstage()))
				newKznTlDmcfipcreationmst.setDmcmControlstage(existKznTlDmcfipcreationmst.getDmcmControlstage());
			else
				newKznTlDmcfipcreationmst.setDmcmControlstage("-");
		}
		else{
			newKznTlDmcfipcreationmst.setDmcmCreatedon(dateTime);
			newKznTlDmcfipcreationmst.setDmcmDefinestage("-");
			newKznTlDmcfipcreationmst.setDmcmMeasurestage("-");
			newKznTlDmcfipcreationmst.setDmcmAnalysestage("-");
			newKznTlDmcfipcreationmst.setDmcmImprovestage("-");
			newKznTlDmcfipcreationmst.setDmcmControlstage("-");
		}
		
			CommonMessage.debugMsg(" 1 ");
		
		if( newKznTlDmcfipcreationmst.getDmcmModifiedon() == null )
			newKznTlDmcfipcreationmst.setDmcmModifiedon(dateTime);
		
			CommonMessage.debugMsg(" 2 ");
		
		return newKznTlDmcfipcreationmst; 
		
	}
	private GenTlDmcfipworkflow fillValues(GenTlDmcfipworkflow newGenTlDmcfipworkflow) throws Exception
	{
		CommonMessage.debugMsg("Inside fill Values");
		newGenTlDmcfipworkflow.setDfiwActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if( newGenTlDmcfipworkflow.getDfiwFipdate() == null )
			newGenTlDmcfipworkflow.setDfiwFipdate(dateTime);
		if( newGenTlDmcfipworkflow.getDfiwProjectleader() == null )
			newGenTlDmcfipworkflow.setDfiwProjectleader("{}");
		if( newGenTlDmcfipworkflow.getDfiwPbuhead() == null )
			newGenTlDmcfipworkflow.setDfiwPbuhead("{}");		
		if( newGenTlDmcfipworkflow.getDfiwKkchampion() == null )
			newGenTlDmcfipworkflow.setDfiwKkchampion("{}");
		if( newGenTlDmcfipworkflow.getDfiwFinancehead() == null )
			newGenTlDmcfipworkflow.setDfiwFinancehead("{}");
		if( newGenTlDmcfipworkflow.getDfiwFipstage() == null )
			newGenTlDmcfipworkflow.setDfiwFipstage("{}");
		if( newGenTlDmcfipworkflow.getDfiwApprovalstatus() == null )
			newGenTlDmcfipworkflow.setDfiwApprovalstatus("{}");
		if( newGenTlDmcfipworkflow.getDfiwStatusmessage() == null )
			newGenTlDmcfipworkflow.setDfiwStatusmessage("{}");
		if( newGenTlDmcfipworkflow.getDfiwTempfield1()== null )
			newGenTlDmcfipworkflow.setDfiwTempfield1("-");
		if( newGenTlDmcfipworkflow.getDfiwTempfield2() == null )
			newGenTlDmcfipworkflow.setDfiwTempfield2("-");
		if( newGenTlDmcfipworkflow.getDfiwTempfield3() == null )
			newGenTlDmcfipworkflow.setDfiwTempfield3("-");
		if( newGenTlDmcfipworkflow.getDfiwTempfield4() == null )
			newGenTlDmcfipworkflow.setDfiwTempfield4("-");
		if( newGenTlDmcfipworkflow.getDfiwCreatedby() == null )
			newGenTlDmcfipworkflow.setDfiwCreatedby("{}");		
		if( newGenTlDmcfipworkflow.getDfiwCreatedon() == null )
			newGenTlDmcfipworkflow.setDfiwCreatedon(dateTime);	
		if( newGenTlDmcfipworkflow.getDfiwModifiedby() == null )
			newGenTlDmcfipworkflow.setDfiwModifiedby("{}");	
		if( newGenTlDmcfipworkflow.getDfiwModifiedon() == null )
			newGenTlDmcfipworkflow.setDfiwModifiedon(dateTime);

		
		return newGenTlDmcfipworkflow; 
		
	}
	@Override
	public KznTlProjectcreationmst getRecall(String keyid) throws Exception {
		//return this.projectDao.getRecall(keyid);
		return projectServiceApi.getRecall(keyid);
	}
	@Override
	public KznTlDmcfipcreationmst getdmcRecall(String keyid) throws Exception {
		return this.projectDao.getdmcRecall(keyid);
	}
	@Override
	public GenTlDmcfipworkflow getwrkflpRecall(String DfiwkeyId) throws Exception {
		CommonMessage.debugMsg("keyid service"+DfiwkeyId);
		return this.projectDao.getwrkflpRecall(DfiwkeyId);
	}
	@Override
	public GenTlDmcfipworkflow getdmcwrkflpRecall(String keyId) throws Exception {
		CommonMessage.debugMsg("keyid service"+keyId);
		return this.projectDao.getdmcwrkflpRecall(keyId);
	}
	@Override
	public KznTlProjectcreationmst delete(KznTlProjectcreationmst newKznTlProjectcreationmst)throws Exception {
		return projectDao.delete(newKznTlProjectcreationmst);
	}
	@Override
	public KznTlDmcfipcreationmst dmcdelete(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflo)throws Exception {
		return projectDao.dmcdelete(newKznTlDmcfipcreationmst,newGenTlDmcfipworkflo);
	}
	@Override
	public List<String[]> getListOfIndicators(CommonFilter commonFilter) throws Exception {
		return projectDao.getListOfIndicators(commonFilter);
	}
	@Override
	public KznTlProjectKpiLink create(KznTlProjectKpiLink newKznTlProjectKpiLink,KznTlProjectKpiLink existKznTlProjectKpiLink) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.create(newKznTlProjectKpiLink);
	}
	@Override
	public KznTlProjectResourceLink create(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink) throws Exception {
		validation.validate(newKznTlProjectResourceLink, "ProjectResourceValidation","create");
		fillValues(newKznTlProjectResourceLink,existKznTlProjectResourceLink);
		return projectDao.create(newKznTlProjectResourceLink);
	}
	@Override
	public KznTlProjectResourceLink delete(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink)throws Exception {
		
		return projectDao.delete(newKznTlProjectResourceLink);
	}
	@Override
	public List<String[]> getAllMilestones(CommonFilter commonFilter)throws Exception {
		//return projectDao.getAllMilestones(commonFilter);
		return projectServiceApi.getAllMileStones(commonFilter.getKey(), commonFilter.getStatus());
	}
	@Override
	public KznTlProjectmaicMileMst create(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst,KznTlProjectmaicMileMst existKznTlProjectmaicMileMst)throws Exception {
		//madhan
		//validation.validate(newKznTlProjectmaicMileMst, "MileStoneValidation","create");
		if(newKznTlProjectmaicMileMst.getMilestonedetail()==null|| newKznTlProjectmaicMileMst.getMilestonedetail().size()<=0){
			throw new ValidationException("milestonedetail-required,");
		}
		fillValues(newKznTlProjectmaicMileMst,existKznTlProjectmaicMileMst);
		//return projectDao.create(newKznTlProjectmaicMileMst);
		return projectServiceApi.saveMilestones(newKznTlProjectmaicMileMst);
	}
	private KznTlProjectmaicMileMst fillValues(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst,KznTlProjectmaicMileMst existKznTlProjectmaicMileMst) throws Exception{
		String dateTime = CommonFunctions.pg_dateTimeNow();
		newKznTlProjectmaicMileMst.setKmmmActive("Y");
		if(newKznTlProjectmaicMileMst.getKmmmTempfield1()==null){
			newKznTlProjectmaicMileMst.setKmmmTempfield1("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmTempfield2()==null){
			newKznTlProjectmaicMileMst.setKmmmTempfield2("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmTempfield3()==null){
			newKznTlProjectmaicMileMst.setKmmmTempfield3("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmTempfield4()==null){
			newKznTlProjectmaicMileMst.setKmmmTempfield4("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmTempfield5()==null){
			newKznTlProjectmaicMileMst.setKmmmTempfield5("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmKeyid()!=null){
			newKznTlProjectmaicMileMst.setKmmmCreatedon(existKznTlProjectmaicMileMst.getKmmmCreatedon());
		}else{
			newKznTlProjectmaicMileMst.setKmmmCreatedon(dateTime);
		}
		if(newKznTlProjectmaicMileMst.getKmmmModifiedon()==null){
			newKznTlProjectmaicMileMst.setKmmmModifiedon(dateTime);
		}
		if(newKznTlProjectmaicMileMst.getKmmmEmpmKeyid()==null){
			newKznTlProjectmaicMileMst.setKmmmEmpmKeyid("{}");
		}
		if(newKznTlProjectmaicMileMst.getKmmmFlid()==null){
			newKznTlProjectmaicMileMst.setKmmmFlid("{}");
		}
		if(newKznTlProjectmaicMileMst.getKmmmFromdate()==null){
			newKznTlProjectmaicMileMst.setKmmmFromdate(dateTime);
		}else {
			newKznTlProjectmaicMileMst.setKmmmFromdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectmaicMileMst.getKmmmFromdate()));
		}
		if(newKznTlProjectmaicMileMst.getKmmmTodate()==null){
			newKznTlProjectmaicMileMst.setKmmmTodate(dateTime);
		}else {
			newKznTlProjectmaicMileMst.setKmmmTodate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectmaicMileMst.getKmmmTodate()));
		}
		if(newKznTlProjectmaicMileMst.getKmmmStages()==null){
			newKznTlProjectmaicMileMst.setKmmmStages("-");
		}
		if(newKznTlProjectmaicMileMst.getKmmmStatus()==null){
			newKznTlProjectmaicMileMst.setKmmmStatus("-");
		}
		for(int i=0;i<newKznTlProjectmaicMileMst.getMilestonedetail().size();i++){
		
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTempfield1()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTempfield1("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTempfield2()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTempfield2("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTempfield3()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTempfield3("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTempfield4()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTempfield4("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTempfield5()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTempfield5("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdEmpmKeyid()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdEmpmKeyid("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdDescription()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdDescription("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdMilestone()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdMilestone("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdStatus()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdStatus("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdRemarks()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdRemarks("-");
		}
		if(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTargetdate()==null){
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTargetdate(dateTime);
		}else {
			newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdTargetdate(CommonFunctions.pg_getDateTimeFromDate(newKznTlProjectmaicMileMst.getMilestonedetail().get(i).getKmmdTargetdate()));
		}
		newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdActive(newKznTlProjectmaicMileMst.getKmmmActive());
		newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdModifiedon(newKznTlProjectmaicMileMst.getKmmmModifiedon());
		newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdCreatedby(newKznTlProjectmaicMileMst.getKmmmCreatedby());
		newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdCreatedon(newKznTlProjectmaicMileMst.getKmmmCreatedon());
		newKznTlProjectmaicMileMst.getMilestonedetail().get(i).setKmmdKmmmKeyid(newKznTlProjectmaicMileMst.getKmmmKeyid());
		
		}
		
		return newKznTlProjectmaicMileMst;
		
	}
	@Override
	public KznTlProjectmaicMileMst getRecallMile(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return projectDao.getRecallMile(keyid);
		return projectServiceApi.getRecallMilestone(keyid);
	}
	@Override
	public KznTlProjectmaicMileMst delete(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) throws Exception {
		//return projectDao.delete(newKznTlProjectmaicMileMst);
		return projectServiceApi.deleteMilestone(newKznTlProjectmaicMileMst);
	}
	@Override
	public void deleteMilestone(String keyid) throws Exception {
//		this.projectDao.deleteMilestone(keyid);
		projectServiceApi.deleteMilestoneDetail(keyid);
	}
	@Override
	public List<String[]> getListOfKaizen(CommonFilter commonFilter) throws Exception {
		return this.projectDao.getListOfKaizen(commonFilter);
	}
	@Override
	public KznTlProjectKaizenLink create(KznTlProjectKaizenLink newKznTlProjectKaizenLink,KznTlProjectKaizenLink existKznTlProjectKaizenLink) throws Exception{
		// TODO Auto-generated method stub
		//return projectDao.create(newKznTlProjectKaizenLink);
		return projectServiceApi.saveKaizen(newKznTlProjectKaizenLink);
	}
	@Override
	public KznTlProjectResourceLink update(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink)throws Exception {
		validation.validate(newKznTlProjectResourceLink, "ProjectResourceValidation","update");
		fillValues(newKznTlProjectResourceLink,existKznTlProjectResourceLink);
		return projectDao.update(newKznTlProjectResourceLink);
	}
	private KznTlProjectResourceLink fillValues(KznTlProjectResourceLink kznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink) {
		String dateTime = CommonFunctions.dateTimeNow();
		if(kznTlProjectResourceLink.getKprlActive()==null){
			kznTlProjectResourceLink.setKprlActive("Y");
		}
		if(kznTlProjectResourceLink.getKprlKeyid()==null){
			kznTlProjectResourceLink.setKprlCreatedon(dateTime);
		}else{
			kznTlProjectResourceLink.setKprlCreatedon(existKznTlProjectResourceLink.getKprlCreatedon());
		}
		if(kznTlProjectResourceLink.getKprlModifiedon()==null){
			kznTlProjectResourceLink.setKprlModifiedon(dateTime);
		}
		if(kznTlProjectResourceLink.getKprlTempfield1()==null){
			kznTlProjectResourceLink.setKprlTempfield1("-");
		}
		if(kznTlProjectResourceLink.getKprlTempfield2()==null){
			kznTlProjectResourceLink.setKprlTempfield2("-");
		}
		if(kznTlProjectResourceLink.getKprlTempfield3()==null){
			kznTlProjectResourceLink.setKprlTempfield3("-");
		}
		if(kznTlProjectResourceLink.getKprlTempfield4()==null){
			kznTlProjectResourceLink.setKprlTempfield4("-");
		}
		if(kznTlProjectResourceLink.getKprlTempfield5()==null){
			kznTlProjectResourceLink.setKprlTempfield5("-");
		}
		if(kznTlProjectResourceLink.getKprlHrsestimate()==null){
			kznTlProjectResourceLink.setKprlHrsestimate("1");
		}
		return kznTlProjectResourceLink;
		
	}
	@Override
	public KznTlProjectResourceLink getRecallResource(String keyid)throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getRecallResource(keyid);
	}
	@Override
	public Workbook getProjectCreationExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getProjectcreationExcel(colmodel,format,commonFilter);
	}
	
	public Workbook getdmcProjectviewExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getdmcProjectviewExcel(colmodel,format,commonFilter);
	}
	public List<String[]> getAllHistory(String dtlId) throws Exception
	{
		return projectDao.getAllHistory(dtlId);
	}
	@Override
	public List<KznTlProjectdmaicstatus> create(List<KznTlProjectdmaicstatus> kznTlProjectdmaicstatusList,List<KznTlProjectdmaicstatus> existKznTlProjectdmaicstatusLst)
			throws Exception {
		// TODO Auto-generated method stub
		validation.validate(kznTlProjectdmaicstatusList, "ProjectCreationValidation","create");
		fillValues(kznTlProjectdmaicstatusList,existKznTlProjectdmaicstatusLst);
		return projectDao.create(kznTlProjectdmaicstatusList,existKznTlProjectdmaicstatusLst);
	}
	
	private void fillValues(
			List<KznTlProjectdmaicstatus> kznTlProjectdmaicstatusList,
			List<KznTlProjectdmaicstatus> existKznTlProjectdmaicstatusLst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		if (kznTlProjectdmaicstatusList.size()>0){
			for(int i=0;i<kznTlProjectdmaicstatusList.size();i++){				
				kznTlProjectdmaicstatusList.get(i).setKpdsActive("Y");				
				kznTlProjectdmaicstatusList.get(i).setKpdsCreatedon(dateTime);
				kznTlProjectdmaicstatusList.get(i).setKpdsModifiedon(dateTime);
				if(kznTlProjectdmaicstatusList.get(i).getKpdsKzpmKeyid()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsKzpmKeyid("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsRemarks()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsRemarks("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsStage()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsStage("-");
				}
				else{
					kznTlProjectdmaicstatusList.get(i).setKpdsStage(kznTlProjectdmaicstatusList.get(i).getKpdsStage().toString().substring(0, 1));
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsStatus()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsStatus("P");
				}
				else{
					kznTlProjectdmaicstatusList.get(i).setKpdsStatus("C");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsVerifiedby()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsVerifiedby("{}");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsVerifieddate()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsVerifieddate(Constants.futureNullDate);
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield1()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield1("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield1()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield1("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield2()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield2("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield3()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield3("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield4()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield4("-");
				}
				if(kznTlProjectdmaicstatusList.get(i).getKpdsTempfield5()==null){
					kznTlProjectdmaicstatusList.get(i).setKpdsTempfield5("-");
				}
			}

		}
	}
	@Override
	public KznTlProjectdmaicstatus delete(
			KznTlProjectdmaicstatus newKznTlProjectdmaicstatus)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception {
		// TODO Auto-generated method stub
		return projectDao.delete(newKznTlProjectdmaicstatus);
	}
	@Override
	public String getMileStoneStages(String kznKeyId) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getMileStoneStages(kznKeyId);
	}
	@Override
	public String getDefineStage(String kzpmKeyid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getDefineStage(kzpmKeyid);
	}
	@Override
	public String getStage(String kzpmKeyid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getStage(kzpmKeyid);
	}
	@Override
	public String getWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus) throws Exception {
		// TODO Auto-generated method stub
		//return projectDao.getWorkFlowStaus(kznKeyId,refType,transCode,isUpdate,wfStatus);
		return projectServiceApi.updateFipWorkflowStatus(kznKeyId, refType, transCode, isUpdate, wfStatus);
	}
	@Override
	public String getdmcWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcWorkFlowStaus(kznKeyId,refType,transCode,isUpdate,wfStatus);
	}
	@Override
	public String getMaicStage(String kznKeyId,	String stage) throws Exception{
		// TODO Auto-generated method stub
		return projectDao.getMaicStage(kznKeyId,stage);
	}
	@Override
	public String[] getdmcAllStageStatus(String kznKeyId) throws Exception{
		// TODO Auto-generated method stub
		return projectDao.getdmcAllStageStatus(kznKeyId);
	}
	@Override
	public KznTlProjectcreationmst  getAllStageStatus(String kznKeyId) throws Exception{
		// TODO Auto-generated method stub
		return projectServiceApi.getRecall(kznKeyId);
	}
	@Override
	public KznTlProjectResourceLink create(List<KznTlProjectResourceLink> kznTlProjectResourceLinkList)
			throws ValidationExceptions, BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		//validation.validate(kznTlProjectResourceLinkList, "ProjectResourceValidation","create");
		fillValues(kznTlProjectResourceLinkList);
		//return projectDao.create(kznTlProjectResourceLinkList);
		return projectServiceApi.saveResourse(kznTlProjectResourceLinkList);
		
	}
	
	@Override
	public KznTlProjectKpiLink createKpi(List<KznTlProjectKpiLink> kznTlProjectKpiLinkList)throws ValidationExceptions,BusinessApplicationExceptions,Exception{
		// TODO Auto-generated method stub
		//validation.validate(kznTlProjectResourceLinkList, "ProjectKpiLink","create");
		//fillValues(kznTlProjectResourceLinkList);
		try{
			fillKPIValues(kznTlProjectKpiLinkList);
			//return projectDao.createKpi(kznTlProjectKpiLinkList);
			return projectServiceApi.saveKpi(kznTlProjectKpiLinkList);
		}
		catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());			
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	private void fillKPIValues(List<KznTlProjectKpiLink> kznTlProjectKpiLinkList) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if (kznTlProjectKpiLinkList!=null){
			for(int i=0;i<kznTlProjectKpiLinkList.size();i++){
				if(kznTlProjectKpiLinkList.get(i).getKpklActive()==null){
					kznTlProjectKpiLinkList.get(i).setKpklActive("Y");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklCreatedon()==null){
					kznTlProjectKpiLinkList.get(i).setKpklCreatedon(dateTime);
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklModifiedon()==null){
					kznTlProjectKpiLinkList.get(i).setKpklModifiedon(dateTime);
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTempfield1()==null){
					kznTlProjectKpiLinkList.get(i).setKpklTempfield1("-");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTempfield2()==null){
					kznTlProjectKpiLinkList.get(i).setKpklTempfield2("-");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTempfield3()==null){
					kznTlProjectKpiLinkList.get(i).setKpklTempfield3("-");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTempfield4()==null){
					kznTlProjectKpiLinkList.get(i).setKpklTempfield4("-");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTempfield5()==null){
					kznTlProjectKpiLinkList.get(i).setKpklTempfield5("-");
				}
				if(kznTlProjectKpiLinkList.get(i).getIsDelete()==null){
					kznTlProjectKpiLinkList.get(i).setIsDelete("N");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklTargetval().equals("undefined") ){
					kznTlProjectKpiLinkList.get(i).setKpklTargetval("0");
				}
				if(kznTlProjectKpiLinkList.get(i).getKpklBaseval().equals("undefined") ){
					kznTlProjectKpiLinkList.get(i).setKpklBaseval("0");
				}
			}
		}		
	}
	private void fillValues(List<KznTlProjectResourceLink> kznTlProjectResourceLinkList) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if (kznTlProjectResourceLinkList!=null){
			for(int i=0;i<kznTlProjectResourceLinkList.size();i++){	
				kznTlProjectResourceLinkList.get(i).setKprlCreatedon(dateTime);
				kznTlProjectResourceLinkList.get(i).setKprlModifiedon(dateTime);
				if(kznTlProjectResourceLinkList.get(i).getKprlActive()==null){
					kznTlProjectResourceLinkList.get(i).setKprlActive("Y");
				}				
				if(kznTlProjectResourceLinkList.get(i).getKprlTempfield1()==null){
					kznTlProjectResourceLinkList.get(i).setKprlTempfield1("-");
				}
				if(kznTlProjectResourceLinkList.get(i).getKprlTempfield2()==null){
					kznTlProjectResourceLinkList.get(i).setKprlTempfield2("-");
				}
				if(kznTlProjectResourceLinkList.get(i).getKprlTempfield3()==null){
					kznTlProjectResourceLinkList.get(i).setKprlTempfield3("-");
				}
				if(kznTlProjectResourceLinkList.get(i).getKprlTempfield4()==null){
					kznTlProjectResourceLinkList.get(i).setKprlTempfield4("-");
				}
				if(kznTlProjectResourceLinkList.get(i).getKprlTempfield5()==null){
					kznTlProjectResourceLinkList.get(i).setKprlTempfield5("-");
				}
				if(kznTlProjectResourceLinkList.get(i).getKprlHrsestimate()==null){
					kznTlProjectResourceLinkList.get(i).setKprlHrsestimate("1");
				}
			}
		}
	}
	@Override
	public List<String[]> getListNewResources(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getListNewResources(commonFilter);
	}
	@Override
	public String getDMTLeader(
			KznTlProjectcreationmst newKznTlProjectcreationmst)
			throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getDMTLeader(newKznTlProjectcreationmst);
	}
	@Override
	public String getdmcDMTLeader(
			KznTlDmcfipcreationmst newKznTlDmcfipcreationmst)
			throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcDMTLeader(newKznTlDmcfipcreationmst);
	}
	@Override
	public List<String[]> getProjectCreationList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getProjectCreationList(commonFilter);
	}
	@Override
	public Workbook getprojectlistExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getprojectlistExcel(colmodel,format,commonFilter);
	}
	@Override
	public List<String[]> getdmcProjectCreationList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcProjectCreationList(commonFilter);
	}
	@Override
	public Workbook getdmcprojectlistExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcprojectlistExcel(colmodel,format,commonFilter);
	}
	
	public List<ComboBox> getProjectMetricsKpiIndicator(ComboFilter comboFilter,String pillarCode,String flid) throws Exception {
		comboFilter.setCodeField("INDICATOR_PATH");
		//comboFilter.setNameField("ABNM_NAME");
		comboFilter.setIdField("KINK_KEYID");
		
		StringBuilder condSql = new StringBuilder();
		if ( UIUtils.isValidKeyId(flid) ){
			condSql.append( " AND KINK_KEYID IN ( SELECT KINK_KEYID FROM KPI_TL_INDICATOR, KPI_TL_INDICATOR_DEPT_LINK,GEN_MV_FLIDHIERARCHY ");
			condSql.append( " WHERE KINK_KEYID = KIDL_INDICATORID AND FLID = KIDL_DEPTID  AND POSITION ( '");//MADHAN
			condSql.append( flid );
			condSql.append("\' IN (PARENTFLIDS||'-'||FLID)) > 0 AND KINK_TYPE = 'KPI' ) ");
			
			
		}
		if ( UIUtils.isValidKeyId(pillarCode) ) 
			condSql.append(" AND TPMP_CODE = '").append(pillarCode).append('\'');
		
		if( condSql.length() > 0)
			comboFilter.setCondSql(condSql.toString());
		
		comboFilter.setTableName("KPI_VW_INDICATOR_PATH");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getdmcemployeecombo(ComboFilter comboFilter,String flid) throws Exception {
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setCodeField("EMPM_CODE");
		//comboFilter.setNameField("ABNM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		
		StringBuilder condSql = new StringBuilder();
		if ( UIUtils.isValidKeyId(flid) ){
			//condSql.append( " AND empm_employeetype in('M','R') and empm_location=(SELECT LOCN_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID = '"+flid+"') ");
			
			condSql.append( " AND FRT_EMPM_KEYID=empm_keyid  and FRT_FNLN_KEYID=FNLN_KEYID and DISPLAYCODE in('LOCN','SBU','PBU','SECT') and frt_active='Y' and empm_active='Y' and FRT_ROLE_KEYID= ROLE_KEYID and ROLE_KEYID NOT IN ('AROL0068') and empm_location=(SELECT LOCN_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID = '"+flid+"') ");
						
		}
		else{
			//condSql.append( " AND empm_employeetype in('M','R') ");//changes done by vijay
			condSql.append( " AND FRT_EMPM_KEYID=empm_keyid  and FRT_FNLN_KEYID=FNLN_KEYID and DISPLAYCODE in('LOCN','SBU','PBU','SECT') and frt_active='Y' and empm_active='Y'  and FRT_ROLE_KEYID= ROLE_KEYID and ROLE_KEYID NOT IN ('AROL0068') ");
		}
		
		if( condSql.length() > 0)
			comboFilter.setCondSql(condSql.toString());
		
		comboFilter.setTableName("GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_VW_FNLN,ADM_TL_ROLEMST");
		//comboFilter.setTableName("GEN_TL_EMPLOYEEMST");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<String[]> getProjectCheckList(String stage,String projectId) throws Exception {
		//return projectDao.getProjectCheckList(stage,projectId);
		return projectServiceApi.getProjectCheclist(stage, projectId);
	}
	@Override
	public List<ComboBox> getcombowave(String condSql,ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		
		comboFilter.setNameField("KZPM_WAVE");
		comboFilter.setIdField("KZPM_WAVE");
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.KZN_TL_PROJECTCREATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public void createCheckList(List<KznTlProjectChecklistLink> kznTlProjectChecklistLinkList,String createdBy) 	throws Exception {
		
		fillCheckListValues(kznTlProjectChecklistLinkList,createdBy);
		//kznTlProjectChecklistLinkDao.create(kznTlProjectChecklistLinkList);
		projectServiceApi.saveCheckList(kznTlProjectChecklistLinkList);
	}
	private void fillCheckListValues(List<KznTlProjectChecklistLink> kznTlProjectChecklistLinkList ,String createdBy){
		
		String time = CommonFunctions.pg_dateTimeNow();
		for(int i =0; i<kznTlProjectChecklistLinkList.size();i++  ){
			kznTlProjectChecklistLinkList.get(i).setPcllTempfield1("-");
			kznTlProjectChecklistLinkList.get(i).setPcllTempfield2("-");
			kznTlProjectChecklistLinkList.get(i).setPcllTempfield3("-");
			kznTlProjectChecklistLinkList.get(i).setPcllTempfield4("-");
			kznTlProjectChecklistLinkList.get(i).setPcllTempfield5("-");
			kznTlProjectChecklistLinkList.get(i).setPcllActive("Y");
//			kznTlProjectChecklistLinkList.get(i).setPcllInclude("Y");
			kznTlProjectChecklistLinkList.get(i).setPcllCreatedby(createdBy);
			kznTlProjectChecklistLinkList.get(i).setPcllCreatedon(time);
			kznTlProjectChecklistLinkList.get(i).setPcllModifiedon(time);
		}
	}
	@Override
	public List<String[]> getDmaiccunt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getDmaiccunt(commonFilter);
	}
	@Override
	public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getpiechart(chrtCommonFilter);
	}
	@Override
	public Workbook getdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmaicExportToExcel(colmodel,format,commonFilter1);
	}
	@Override
	public List<String[]> getdmcDmaiccunt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcDmaiccunt(commonFilter);
	}
	@Override
	public List<String[]> getdmcpiechart(CommonFilter chrtCommonFilter) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcpiechart(chrtCommonFilter);
	}
	@Override
	public Workbook getdmcdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcdmaicExportToExcel(colmodel,format,commonFilter1);
	}
	@Override
	public List<String[]> getJhmemberaddresources(CommonFilter commonFilter1,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getJhmemberaddresources(commonFilter1,flid);
	}
	@Override
	public List<String[]> getdmcJhmemberaddresources(CommonFilter commonFilter1,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getdmcJhmemberaddresources(commonFilter1,flid);
	}
	@Override
	public void DeleteJhMemberRecord(String keyid) throws Exception {
		// TODO Auto-generated method stub
//		this.projectDao.DeleteJhMemberRecord(keyid);
		projectServiceApi.deleteResourse(keyid);
	}
	@Override
	public List<String[]> setInsertQuery(String rolename, String refId,
			String nxtrole, String trnscode, String lstlvl, String flId,
			String roleid,String status) throws Exception {
		// TODO Auto-generated method stub
//		return projectDao.setInsertQuery(rolename,refId,nxtrole,trnscode,lstlvl,flId,roleid,status);
		return projectServiceApi.updateFipApprovals(rolename, refId, nxtrole, trnscode, lstlvl, flId, roleid, status);
	}
	@Override
	public String InsertPbuHead(String kzpmKeyid,String mode, String flid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.InsertPbuHead(kzpmKeyid,mode,flid);
	}
	@Override
	public String InsertPbuHeadClosure(String kzpmKeyid,String mode, String flid) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.InsertPbuHeadClosure(kzpmKeyid,mode,flid);
	}
	@Override
	public Workbook getDMCdmaicExportToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getProjectnewview(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.projectDao.getProjectnewview(commonFilter);
	}
	@Override
	public List<String[]> getFIpCount(CommonFilter commonFilter) throws Exception{
		 return this.projectDao.getFIpCount(commonFilter);
	}
	public Workbook getFIpWaveCountExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception{
	    return this.projectDao.getFIpWaveCountExportToExcel(colmodel, format, commonFilter1);
	}
	@Override
	  public List<String[]> getFIpcountGraph(CommonFilter commonFilter,String rowId)throws Exception{
		  return this.projectDao.getFIpcountGraph(commonFilter, rowId);
	  }
	  @Override
	  public List<String[]> getFIpWaveBenefitCount(CommonFilter commonFilter) throws Exception{
		 return this.projectDao.getFIpWaveBenefitCount(commonFilter);
	  }
	   public Workbook getFIpWaveCountExportToExcel(CommonFilter commonFilter1,JSONObject colmodel, String format) throws Exception{
		   
		   return this.projectDao.getFIpWaveCountExportToExcel(commonFilter1,colmodel, format);
	   }
public List<ComboBox> getJHKaizenBeltComboList(ComboFilter combofilter)throws Exception{
	combofilter.setIdField("KBEL_KEYID");
	combofilter.setNameField("KBEL_NAME");
    combofilter.setTableName(TableNames.TBL_KZN_TL_BELTDETAILSMST);
	return commonFilterDao.fillComboValues(combofilter);
}
@Override
public GenTlWorkflowInfo autoapproveDMAIC(GenTlWorkflowInfo genTlWorkflowInfo, GenTlWorkflowInfo existGenTlWorkflowInfo,
		String Projectleader, String KKChampion, String FIPNO) throws Exception {
	// TODO Auto-generated method stub
	return this.projectDao.autoapproveDMAIC(genTlWorkflowInfo,existGenTlWorkflowInfo,Projectleader,KKChampion,FIPNO);
}
}
