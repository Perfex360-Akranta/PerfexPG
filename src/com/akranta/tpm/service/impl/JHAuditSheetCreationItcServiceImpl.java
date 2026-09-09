package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.JHAuditSheetCreationItcDao;
import com.akranta.tpm.dao.JhaTlAuditdtlItcDao;
import com.akranta.tpm.dao.JhaTlAuditmstItcDao;
import com.akranta.tpm.dao.JhaTlAuditparameterItcDao;
import com.akranta.tpm.dao.JhaTlTemplatemchlinkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.JHAuditSheetCreationItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditdtlItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditmstItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditparameterItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlTemplatemchlinkDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlAudittemplate;
import com.akranta.tpm.model.JhaTlTemplategradelink;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.model.JhaTlTemplatemchlink;
import com.akranta.tpm.model.JhaTlTemplatesteplink;
import com.akranta.tpm.service.JHAuditSheetCreationItcService;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
public  class JHAuditSheetCreationItcServiceImpl implements  JHAuditSheetCreationItcService {
	
private  JHAuditSheetCreationItcServiApi jhauditSheetCreationItcServiApi;

private JHAuditSheetCreationItcDao jHAuditSheetItcCreationDao;
private JhaTlAuditmstItcDao jhaTlAuditmstItcDao;
private JhaTlAuditdtlItcDao jhaTlAuditdtlItcDao;
private JhaTlTemplatemchlinkDao jhaTlTemplatemchlinkDao;
private JhaTlAuditparameterItcDao jhaTlAuditparameterItcDao;
private CommonFilterDao commonFilterDao;
private Validations validations ;
	
	public JHAuditSheetCreationItcServiceImpl(DBActionTemplate dbActionTemplate)
	{
		jHAuditSheetItcCreationDao =  new JHAuditSheetCreationItcDaoImpl(dbActionTemplate);
		jhaTlAuditmstItcDao =  new JhaTlAuditmstItcDaoImpl(dbActionTemplate);
		jhaTlAuditdtlItcDao =  new JhaTlAuditdtlItcDaoImpl(dbActionTemplate);
		jhaTlTemplatemchlinkDao =  new JhaTlTemplatemchlinkDaoImpl(dbActionTemplate);
		jhaTlAuditparameterItcDao =  new JhaTlAuditparameterItcDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	
	
	//03-01-2026
	public void JHAuditSheetCreationItcServiceImplJwt(String JwtToken){
    	try{
    		jhaTlAuditmstItcDao.JhaTlAuditmstItcDaoImplJwt(JwtToken);
    		jhauditSheetCreationItcServiApi = new JHAuditSheetCreationItcServiApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("getTrainingHoursPgm");
		return this.jhaTlAuditmstItcDao.getjhAuditSheetfillGrid(commonFilter);
	}
	@Override
	public List<String[]> getjhAuditGridSql(JhaTlAuditmst jhaTlAuditmst,String machineId,String jhamKeyID,String auditType,String jhstepid)throws Exception
	{
		return this.jhauditSheetCreationItcServiApi.getAuditTemplateGrid(machineId, jhamKeyID);
		//return this.jhaTlAuditmstItcDao.getjhAuditGridSql(jhaTlAuditmst,machineId,jhamKeyID,auditType,jhstepid);
	}
	@Override
	public List<String[]> getgradeid(String point,String keyId)throws Exception
	{
		return this.jhaTlAuditmstItcDao.getgradeid(point,keyId);
	}
	@Override
	public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception
	{
		//return this.jhaTlAuditmstItcDao.getMinPoints(parameter,auditTeam);
		Integer minPoints = jhauditSheetCreationItcServiApi.getMinimumPoints(parameter, auditTeam);
		
	    List<String[]> result = new ArrayList<>();
	    String[] row = new String[] { minPoints.toString() };
	    result.add(row);
	    
	    return result;
	}
	
//	public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception {
//		// TODO Auto-generated method stub
//		return this.jhaTlAuditmstItcDao.select(jhaTlAuditmst);
//	}
	
	//07-jan
	public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception {
	    return this.jhauditSheetCreationItcServiApi.getAuditByKeyid(jhaTlAuditmst);
		//return this.jhaTlAuditmstItcDao.select(jhaTlAuditmst);
	}
	public JhaTlAuditmst create(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {

		try {
			String validationsFor = "create";
			validations.validate(newjhaTlAuditmst,"JhAuditCreation",validationsFor);
			fillValues(newjhaTlAuditmst,oldjhaTlAuditmst,jhAuditCreationBean);
			
			// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
			
			return jhauditSheetCreationItcServiApi.insertRecord(newjhaTlAuditmst);
			//return jhaTlAuditmstItcDao.create(newjhaTlAuditmst);
			
			
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage()"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public JhaTlAuditdtl create(JhaTlAuditdtl newJhaTlAuditdtl,JhaTlAuditdtl oldJhaTlAuditdtl,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor = "create";
			validations.validate(newJhaTlAuditdtl,"JhAuditCreation",validationsFor);
			
			fillValues(newJhaTlAuditdtl,oldJhaTlAuditdtl,jhAuditCreationBean);
			
			//return jhauditSheetCreationItcServiApi.__insertRecord__(newJhaTlAuditdtl);
			return jhaTlAuditdtlItcDao.create(newJhaTlAuditdtl);
			
			
		}catch (ValidationExceptions e){
			//CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public JhaTlAuditdtl update(JhaTlAuditdtl newJhaTlAuditdtl,JhaTlAuditdtl oldJhaTlAuditdtl,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {
		//return null;
	
		String validationsFor;
		validationsFor = "update";
		validations.validate(newJhaTlAuditdtl,"JhAuditCreation",validationsFor);
		fillValues(newJhaTlAuditdtl, oldJhaTlAuditdtl, jhAuditCreationBean);
			
		return jhaTlAuditdtlItcDao.update(newJhaTlAuditdtl);
	}
	public JhaTlAuditmst update(JhaTlAuditmst newJhaTlAuditmst,JhaTlAuditmst oldJhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {
		//return null;
	
		String validationsFor;
		validationsFor = "update";
		validations.validate(newJhaTlAuditmst,"JhAuditCreation",validationsFor);
		fillValues(newJhaTlAuditmst, oldJhaTlAuditmst, jhAuditCreationBean);
		return jhauditSheetCreationItcServiApi.insertRecord(newJhaTlAuditmst);
		//return jhaTlAuditmstItcDao.update(newJhaTlAuditmst);
	}
	public JhaTlAuditmst delete(JhaTlAuditmst jhaTlAuditmst)throws Exception 
	{
		//return jhaTlAuditmstItcDao.delete(jhaTlAuditmst);
		return jhauditSheetCreationItcServiApi.delete(jhaTlAuditmst);
	}
	public Workbook jhAuditSheetExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.jhaTlAuditmstItcDao.jhAuditSheetExportExcel(commonFilter,colmodel,rptFormat);
	}
	public List<ComboBox> getJhStepComboList(CommonFilter commonFilter, ComboFilter jhStep) throws Exception {
		//ComboFilter jhStep =  new ComboFilter();
		String condSql="";
		jhStep.setIdField("JHSM_KEYID");
		//jhStep.setCodeField("JHSM_CODE");
		jhStep.setNameField("JHSM_NAME");
		jhStep.setTableName(TableNames.TBL_GEN_TL_JHSTEPMST);
		condSql=" AND JHSM_ACTIVE='Y'";
		//String flid=commonFilter.getFlid();
		//if(CommonFunctions.isValidKeyId(flid))
		//jhStep.setCondSql(" and JHSM_KEYID not in (SELECT MCHM_JHSTEP FROM GEN_TL_MACHINEMST where MCHM_KEYID ='"+flid+"'");
		jhStep.setCondSql(condSql);
		return commonFilterDao.fillComboValues(jhStep);	
	}
	public String getjhStepkeyId(String flId) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getjhStepid(flId);
	}
	public JhaTlTemplatelevellink getAuditLevel(String templateId,String flId, String jhStepId) throws Exception {
		// TODO Auto-generated method stub
		//return jhaTlAuditmstItcDao.getAuditLevel(templateId,flId, jhStepId);
		CommonMessage.debugMsg("yyyyyyyy");
//		
//		CommonFilter commonFilter = new CommonFilter();
//		String flId1 = commonFilter.getFlid();
//		String templateId1= commonFilter.getJhTemplateId();
//		ComboFilter jhStepId = commonFilter.getJhStep();
//	    
		//return jhaTlAuditmstItcDao.getAuditLevel(templateId, flId, jhStepId);
		return  jhauditSheetCreationItcServiApi.getAuditLevels(templateId, flId, jhStepId);
	}
	public JhaTlAuditmst getExistingjhmKeyid(String templateId,String flId, String date, String auditType, String stepId) throws Exception {
		// TODO Auto-generated method stub
		//return jhaTlAuditmstItcDao.getExistingjhmKeyid(templateId,flId, date, auditType, stepId);
		CommonMessage.debugMsg("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		return jhauditSheetCreationItcServiApi.getExistingAudit(templateId,flId, date, auditType, stepId);
	}
	
	public String getMinMarks(String auditLevel,String auditTemplate) throws Exception {
		// TODO Auto-generated method stub
		//return jhaTlAuditmstItcDao.getMinMarks(auditLevel,auditTemplate);
		Integer minMarks = jhauditSheetCreationItcServiApi.getMinimumMarks(auditLevel, auditTemplate);
	    return minMarks.toString();
	}
	public String getjhStepIdforFlid(String flid) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getjhStepIdforFlid(flid);
	}
	public String getjhauditLevelCountforFlid(String flid) throws Exception {
		// TODO Auto-generated method stub
		//return jhaTlAuditmstItcDao.getjhauditLevelCountforFlid(flid);
		CommonMessage.debugMsg("count flid abadhjkadhjk");
		return jhauditSheetCreationItcServiApi.getUnassignedAuditTeamsCount(flid);
	}
	private JhaTlAuditmst fillValues(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) {
		
		newjhaTlAuditmst.setJhamActive("Y");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		newjhaTlAuditmst.setJhamAuditupload("N");
		
		if( ! UIUtils.isValidKeyId(newjhaTlAuditmst.getJhamKeyid())  )
		{
			newjhaTlAuditmst.setJhamCreatedon(dateTime);
			newjhaTlAuditmst.setJhamModifiedon(dateTime);
		}
		else{
			/*newjhaTlAuditmst.setJhamCreatedon(oldjhaTlAuditmst.getJhamCreatedon());
			newjhaTlAuditmst.setJhamModifiedon(oldjhaTlAuditmst.getJhamModifiedon());*/
			newjhaTlAuditmst.setJhamCreatedon(dateTime);
			newjhaTlAuditmst.setJhamModifiedon(dateTime);
		}  
		
		if( newjhaTlAuditmst.getJhamFlid() == null )
			newjhaTlAuditmst.setJhamFlid("{}");
		
		if( newjhaTlAuditmst.getJhamAudittype() == null )
			newjhaTlAuditmst.setJhamAudittype("{}");
		
		if( newjhaTlAuditmst.getJhamAuditpillar() == null )
			newjhaTlAuditmst.setJhamAuditpillar("{}");
		
		
		//05-jan
		String JhamAuditdate = newjhaTlAuditmst.getJhamAuditdate();
		newjhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromDate(newjhaTlAuditmst.getJhamAuditdate()));
		if( newjhaTlAuditmst.getJhamAuditdate() == null )
			newjhaTlAuditmst.setJhamAuditdate(Constants.pgPassNullDateTime);
		
		if( newjhaTlAuditmst.getJhamNextauditdate() == null )
			newjhaTlAuditmst.setJhamNextauditdate(Constants.pgFutureNullDateTime);
		
		if( newjhaTlAuditmst.getJhamTotalpoints() == null )
			newjhaTlAuditmst.setJhamTotalpoints("0");
		
		if( newjhaTlAuditmst.getJhamAuditorname() == null )
			newjhaTlAuditmst.setJhamAuditorname("{}");
		
		if( newjhaTlAuditmst.getJhamAuditortype() == null )
			newjhaTlAuditmst.setJhamAuditortype("{}");
		
		if( newjhaTlAuditmst.getJhamJhstepid() == null )
			newjhaTlAuditmst.setJhamJhstepid("{}");
		
		if( newjhaTlAuditmst.getJhamStatus() == null )
			newjhaTlAuditmst.setJhamStatus("P");
		
		if( newjhaTlAuditmst.getJhamAuditteamid() == null )
			newjhaTlAuditmst.setJhamAuditteamid("{}");
		
		if( newjhaTlAuditmst.getJhamLeadername() == null )
			newjhaTlAuditmst.setJhamLeadername("{}");
		
		if( newjhaTlAuditmst.getJhamNextauditteam() == null )
			newjhaTlAuditmst.setJhamNextauditteam("{}");
		
	
		if( newjhaTlAuditmst.getJhamTempfield2() == null )
			newjhaTlAuditmst.setJhamTempfield2("{}");
		
		if( newjhaTlAuditmst.getJhamTempfield3() == null )
			newjhaTlAuditmst.setJhamTempfield3("{}");
		
		if( newjhaTlAuditmst.getJhamTempfield4() == null )
			newjhaTlAuditmst.setJhamTempfield4("{}");
		
		if( newjhaTlAuditmst.getJhamTempfield5() == null )
			newjhaTlAuditmst.setJhamTempfield5("{}");
		
		newjhaTlAuditmst.setAuditDtl(fillValues(newjhaTlAuditmst,newjhaTlAuditmst));
		return newjhaTlAuditmst;		
	}
	
	private List<JhaTlAuditdtl> fillValues(JhaTlAuditmst newJhaTlAuditmst,JhaTlAuditmst oldJhaTlAuditmst) {
		CommonMessage.debugMsg("create service fill value");
		List<JhaTlAuditdtl> jhaTlAuditdtlList = newJhaTlAuditmst.getAuditDtl();
		CommonMessage.debugMsg("newJhaTlAuditmst.getAuditDtl():"+jhaTlAuditdtlList.size());		
		List<JhaTlAuditdtl> jhaTlAuditdtlListNew=new ArrayList<JhaTlAuditdtl>();
		for(int i=0;i<jhaTlAuditdtlList.size();i++){
			JhaTlAuditdtl newJhaTlAuditdtl=jhaTlAuditdtlList.get(i);
			newJhaTlAuditdtl.setJhadActive("Y");			
			String dateTime = CommonFunctions.pg_dateTimeNow();		
			CommonMessage.debugMsg(dateTime);
			CommonMessage.debugMsg("getJhadCreatedon:"+ 	newJhaTlAuditdtl.getJhadCreatedon());
			CommonMessage.debugMsg("getJhadKeyid:"+newJhaTlAuditdtl.getJhadKeyid());
			
			if( ! UIUtils.isValidKeyId(newJhaTlAuditdtl.getJhadKeyid())  )
			{
				newJhaTlAuditdtl.setJhadCreatedon(dateTime);
				newJhaTlAuditdtl.setJhadModifiedon(dateTime);
			}
			else{
				newJhaTlAuditdtl.setJhadCreatedon(dateTime);
				newJhaTlAuditdtl.setJhadModifiedon(dateTime);
			} 
					
			if( newJhaTlAuditdtl.getJhadJhauditmasterid() == null )
				newJhaTlAuditdtl.setJhadJhauditmasterid("{}");
			
			if( newJhaTlAuditdtl.getJhadMaximumpoints() == null )
				newJhaTlAuditdtl.setJhadMaximumpoints("{}");
			
			if( newJhaTlAuditdtl.getJhadParameterid() == null )
				newJhaTlAuditdtl.setJhadParameterid("{}");
			
			if( newJhaTlAuditdtl.getJhadPointsscored() == null )
				newJhaTlAuditdtl.setJhadPointsscored("0");
			
			if( newJhaTlAuditdtl.getJhadRemarks() == null )
				newJhaTlAuditdtl.setJhadRemarks("{}");
			
			if( newJhaTlAuditdtl.getJhadNcactionplan() == null || newJhaTlAuditdtl.getJhadNcactionplan() =="...")
				newJhaTlAuditdtl.setJhadNcactionplan("{}");		
			
			if( !CommonFunctions.isValidKeyId(newJhaTlAuditdtl.getJhadNcclosed())) 
				newJhaTlAuditdtl.setJhadNcclosed("-");
			else
				newJhaTlAuditdtl.setJhadNcclosed(newJhaTlAuditdtl.getJhadNcstatus().substring(0, 1));
			
			if( newJhaTlAuditdtl.getJhadNcremarks() == null )
				newJhaTlAuditdtl.setJhadNcremarks("{}");
			
			if( !CommonFunctions.isValidKeyId(newJhaTlAuditdtl.getJhadNcstatus()))
				newJhaTlAuditdtl.setJhadNcstatus("-");
			else
				newJhaTlAuditdtl.setJhadNcstatus(newJhaTlAuditdtl.getJhadNcstatus().substring(0, 1));
			
			if( newJhaTlAuditdtl.getJhadTempfield1() == null )
				newJhaTlAuditdtl.setJhadTempfield1("{}");
			
			if( newJhaTlAuditdtl.getJhadTempfield2() == null )
				newJhaTlAuditdtl.setJhadTempfield2("{}");
			
			if( newJhaTlAuditdtl.getJhadTempfield3() == null )
				newJhaTlAuditdtl.setJhadTempfield3("{}");
			
			if( newJhaTlAuditdtl.getJhadTempfield4() == null )
				newJhaTlAuditdtl.setJhadTempfield4("{}");
			
			if( newJhaTlAuditdtl.getJhadTempfield5() == null )
				newJhaTlAuditdtl.setJhadTempfield5("{}");
			
			jhaTlAuditdtlListNew.add(newJhaTlAuditdtl);
		}		
		return jhaTlAuditdtlListNew;
		
	}

	
	private JhaTlAuditdtl fillValues(JhaTlAuditdtl newJhaTlAuditdtl,JhaTlAuditdtl oldJhaTlAuditdtl,  JhAuditCreationBean jhAuditCreationBean) {
		CommonMessage.debugMsg("create service fill value");
		newJhaTlAuditdtl.setJhadActive("Y");
		CommonMessage.debugMsg("lllllll");		
		String dateTime = CommonFunctions.pg_getDate();		
		CommonMessage.debugMsg(dateTime);
		CommonMessage.debugMsg("lllllll"+ 	newJhaTlAuditdtl.getJhadCreatedon());
		CommonMessage.debugMsg("newBatchMst.getBatchKeyid()"+newJhaTlAuditdtl.getJhadKeyid());
		
		if( ! UIUtils.isValidKeyId(newJhaTlAuditdtl.getJhadKeyid())  )
		{
			newJhaTlAuditdtl.setJhadCreatedon(dateTime);
			newJhaTlAuditdtl.setJhadModifiedon(dateTime);
		}
		else{
			newJhaTlAuditdtl.setJhadCreatedon(dateTime);
			newJhaTlAuditdtl.setJhadModifiedon(dateTime);
		} 
				
		if( newJhaTlAuditdtl.getJhadJhauditmasterid() == null )
			newJhaTlAuditdtl.setJhadJhauditmasterid("{}");
		
		if( newJhaTlAuditdtl.getJhadMaximumpoints() == null )
			newJhaTlAuditdtl.setJhadMaximumpoints("{}");
		
		if( newJhaTlAuditdtl.getJhadParameterid() == null )
			newJhaTlAuditdtl.setJhadParameterid("{}");
		
		if( newJhaTlAuditdtl.getJhadPointsscored() == null )
			newJhaTlAuditdtl.setJhadPointsscored("0");
		
		if( newJhaTlAuditdtl.getJhadRemarks() == null )
			newJhaTlAuditdtl.setJhadRemarks("{}");
		
		if( newJhaTlAuditdtl.getJhadNcactionplan() == null )
			newJhaTlAuditdtl.setJhadNcactionplan("{}");		
		
		if( !CommonFunctions.isValidKeyId(newJhaTlAuditdtl.getJhadNcclosed())) 
			newJhaTlAuditdtl.setJhadNcclosed("{}");
		else
			newJhaTlAuditdtl.setJhadNcclosed(newJhaTlAuditdtl.getJhadNcstatus().substring(0, 1));
		
		if( newJhaTlAuditdtl.getJhadNcremarks() == null )
			newJhaTlAuditdtl.setJhadNcremarks("{}");
		
		if( !CommonFunctions.isValidKeyId(newJhaTlAuditdtl.getJhadNcstatus()))
			newJhaTlAuditdtl.setJhadNcstatus("{}");
		else
			newJhaTlAuditdtl.setJhadNcstatus(newJhaTlAuditdtl.getJhadNcstatus().substring(0, 1));
		
		if( newJhaTlAuditdtl.getJhadTempfield1() == null )
			newJhaTlAuditdtl.setJhadTempfield1("{}");
		
		if( newJhaTlAuditdtl.getJhadTempfield2() == null )
			newJhaTlAuditdtl.setJhadTempfield2("{}");
		
		if( newJhaTlAuditdtl.getJhadTempfield3() == null )
			newJhaTlAuditdtl.setJhadTempfield3("{}");
		
		if( newJhaTlAuditdtl.getJhadTempfield4() == null )
			newJhaTlAuditdtl.setJhadTempfield4("{}");
		
		if( newJhaTlAuditdtl.getJhadTempfield5() == null )
			newJhaTlAuditdtl.setJhadTempfield5("{}");
				
		return newJhaTlAuditdtl;
		
	}

	@Override
	public Workbook getjhAuditMultiExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		return this.jhaTlAuditmstItcDao.getjhAuditMultiExportExcel(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<String[]> getjhAuditParamterGrid(CommonFilter commonFilter)	throws Exception {
		return this.jhauditSheetCreationItcServiApi.getAuditParametersByTemplateId(commonFilter.getJhTemplateId());
		
		//return this.jhaTlAuditmstItcDao.getjhAuditParamterGrid(commonFilter);
	}

	@Override
	public List<ComboBox> getJhTemplateComboList(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception {
		String condSql="";
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("JHAP_TEMPLATECODE");
		comboFilter.setNameField("JHAP_TEMPLATENAME");
		comboFilter.setIdField("JHAP_KEYID");
		if (CommonFunctions.isValidKeyId(commonFilter.getType()))
			condSql=" and JHAP_AUDITPILLAR='" + commonFilter.getType() + "' " ;		
		if (CommonFunctions.isValidKeyId(commonFilter.getKey()))
			condSql+=" and JHAP_AUDITTYPE='" + commonFilter.getKey() + "' ";
		if (CommonFunctions.isValidKeyId(commonFilter.getKK()))
			condSql+=" and JHAP_AUDITLEVEL='" + commonFilter.getKK() + "' ";
		CommonMessage.debugMsg("condSql:"+condSql);
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_JHA_TL_AUDITPARAMETER);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getJhAppLevelComboList(CommonFilter commonFilter,ComboFilter comboFilter)throws Exception{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("JHAT_CODE");
		comboFilter.setNameField("JHAT_NAME");
		comboFilter.setIdField("JHAT_KEYID");
	
		comboFilter.setTableName(TableNames.TBL_JHA_TL_AUDITTEAM);
		//postgress table name change upperto lower case - sriram m
		String templateId = commonFilter.getKey();
		if (UIUtils.isValidKeyId(templateId))
			comboFilter.setCondSql( " AND JHAT_KEYID IN (SELECT JTLL_AUDITLEVELID FROM jha_tl_templatelevellink  WHERE JTLL_TEMPLATEID='" + templateId + "' ) ");
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<String[]> getjhAuditStepGrid(CommonFilter commonFilter)	throws Exception {
		return this.jhaTlAuditmstItcDao.getjhAuditStepGrid(commonFilter);
	}

	@Override
	public List<String[]> getjhAuditEquipmentGrid(CommonFilter commonFilter)throws Exception {
		return this.jhaTlAuditmstItcDao.getjhAuditEquipmentGrid(commonFilter);
	}

	@Override
	public List<String[]> getjhAuditLevelGrid(CommonFilter commonFilter)throws Exception {
		
		return this.jhaTlAuditmstItcDao.getjhAuditLevelGrid(commonFilter);
	}

	@Override
	public JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter) throws Exception {
		return jhauditSheetCreationItcServiApi.getParameterByKeyid(jhaTlAuditparameter.getJhapKeyid());
		
		//return this.jhaTlAuditmstItcDao.recallValues(jhaTlAuditparameter);
	}
	
	@Override
	public JhaTlAuditparameter create(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		
		try {	
			CommonMessage.debugMsg("enter service impl");
			String validationsFor;			
			validationsFor = "create";
			validations.validate(newJhaTlAuditparameter,"JHParameterCreation",validationsFor);
			FillValuesJHParameter(newJhaTlAuditparameter,existJhaTlAuditparameter);		
			CommonMessage.debugMsg("enter service impl");
			//return jhaTlAuditparameterItcDao.create(newJhaTlAuditparameter);			
			return jhauditSheetCreationItcServiApi.insertParameterRecord(newJhaTlAuditparameter);
		}
		catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch (BusinessApplicationExceptions e){
	
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch (Exception e){
	
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new Exception(e.getMessage());
		}		
		
	}

	private JhaTlAuditparameter FillValuesJHParameter(JhaTlAuditparameter newJhaTlAuditparameter,	JhaTlAuditparameter existJhaTlAuditparameter) {
		CommonMessage.debugMsg("inside fillvalues 1");
		newJhaTlAuditparameter.setJhapActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		String revisionDate = newJhaTlAuditparameter.getJhapRevisiondate();
		newJhaTlAuditparameter.setJhapRevisiondate(CommonFunctions.pg_getDateTimeFromDate(revisionDate));
		if(newJhaTlAuditparameter.getJhapKeyid() == null )
			newJhaTlAuditparameter.setJhapCreatedon(dateTime);	
		else
			newJhaTlAuditparameter.setJhapCreatedon(dateTime);
		
		newJhaTlAuditparameter.setJhapModifiedon(dateTime);
		
		if( newJhaTlAuditparameter.getJhapRemarks() == null )
			newJhaTlAuditparameter.setJhapRemarks("{}");
		
		if( newJhaTlAuditparameter.getJhapRevisiondate() == null )
			newJhaTlAuditparameter.setJhapRevisiondate(Constants.pgPassNullDateTime);
		
		if( newJhaTlAuditparameter.getJhapRemarks() == null )
			newJhaTlAuditparameter.setJhapRemarks("{}");
		 newJhaTlAuditparameter.setJhapRemarks(
		            sanitizeForJson(newJhaTlAuditparameter.getJhapRemarks()));
		
		if( newJhaTlAuditparameter.getJhapCriteriamax() == null )
			newJhaTlAuditparameter.setJhapCriteriamax("0");
		
		newJhaTlAuditparameter.setJhapTempfield2("{}");
		newJhaTlAuditparameter.setJhapTempfield3("{}");
		newJhaTlAuditparameter.setJhapTempfield4("{}");
		newJhaTlAuditparameter.setJhapTempfield5("{}");
		
		CommonMessage.debugMsg("after the fill values 1");
		CommonMessage.debugMsg("inside fillvalues 2");
		//newJhaTlAuditparameter.setEquipmentGrid(equipmentFillValues(newJhaTlAuditparameter,existJhaTlAuditparameter));
		//newJhaTlAuditparameter.setJHStepGrid(jhStepFillValues(newJhaTlAuditparameter,existJhaTlAuditparameter));
		newJhaTlAuditparameter.setJhLevelGrid(jhLevelFillValues(newJhaTlAuditparameter,existJhaTlAuditparameter));
		newJhaTlAuditparameter.setJhAuditTemplate(jhAuditTemplateFillValues(newJhaTlAuditparameter,existJhaTlAuditparameter));
		//newJhaTlAuditparameter.setJhGradeGrid(jhGradeFillValues(newJhaTlAuditparameter,existJhaTlAuditparameter));
		CommonMessage.debugMsg("inside fillvalues 3");
		return newJhaTlAuditparameter;
		
	}

	private List<JhaTlTemplategradelink> jhGradeFillValues(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) {
		
		List<JhaTlTemplategradelink> jhaTlTemplategradelink = newJhaTlAuditparameter.getJhGradeGrid();
		CommonMessage.debugMsg("newJhaTlAuditparameter.getJhGradeGrid()::"+jhaTlTemplategradelink.size());
		List<JhaTlAudittemplate> jhaTlAudittemplate = newJhaTlAuditparameter.getJhAuditTemplate();
		List<JhaTlTemplategradelink> newJhaTlTemplategradelinkList = new ArrayList<JhaTlTemplategradelink>();
		
		for(int i=1;i<jhaTlTemplategradelink.size();i++)
		{	CommonMessage.debugMsg("fillvaluess");
			JhaTlTemplategradelink newJhaTlTemplategradelink = (JhaTlTemplategradelink)newJhaTlAuditparameter.getJhGradeGrid().get(i); 
			newJhaTlTemplategradelink.setJtglActive("Y");
			String dateTime = CommonFunctions.dateTimeNow();
			CommonMessage.debugMsg("dateTime::"+dateTime);
			newJhaTlTemplategradelink.setJtglCreatedon(dateTime);
			newJhaTlTemplategradelink.setJtglModifiedon(dateTime);
			/*if(newJhaTlTemplategradelink.getJtglAuditmasterid()== null )			
			{	
				newJhaTlTemplategradelink.setJtglCreatedon(dateTime);			
			}					
			else
			{	
				newJhaTlTemplategradelink.setJtglCreatedon(dateTime);
			}
			newJhaTlTemplategradelink.setJtglModifiedon(dateTime);*/
			if(newJhaTlTemplategradelink.getJtglAuditmasterid() == null)
				newJhaTlTemplategradelink.setJtglAuditmasterid("{}");
			if(newJhaTlTemplategradelink.getJtglGradeid() == null)
				newJhaTlTemplategradelink.setJtglGradeid("{}");
			
			if(newJhaTlTemplategradelink.getJtglMinimummarks() == null)
				newJhaTlTemplategradelink.setJtglMinimummarks("0");
			CommonMessage.debugMsg("fillvaluess:::::::1111");
			/*double minval = 0;
			int rowid = 0;
			if(i==0)
			{
				minval = setMiniumVal(rowid,newJhaTlAuditparameter);
			}
			else
			{
				JhaTlTemplategradelink fillMaxVal = (JhaTlTemplategradelink)newJhaTlAuditparameter.getJhGradeGrid().get(i-1);
				CommonMessage.debugMsg(fillMaxVal.getJtglMinimummarks());
				if(jhaTlAudittemplate.get(rowid).getJautMasterid().equals(fillMaxVal.getJtglAuditmasterid()))
				{
					minval = Double.parseDouble(fillMaxVal.getJtglMinimummarks());
					minval = minval-0.01;
				}
				else
					minval = setMiniumVal(rowid+1,newJhaTlAuditparameter);
			}
			
			if(newJhaTlTemplategradelink.getJtglMaximummarks() == null)
				newJhaTlTemplategradelink.setJtglMaximummarks(Double.toString(minval));
			*/
			newJhaTlTemplategradelinkList.add(newJhaTlTemplategradelink);
		}
		CommonMessage.debugMsg("fillvaluess::::::22222");
		return newJhaTlTemplategradelinkList;
	}

	private double setMiniumVal(int rowid,JhaTlAuditparameter newJhaTlAuditparameter) {
		List<JhaTlAudittemplate> jhaTlAudittemplate = newJhaTlAuditparameter.getJhAuditTemplate();
		double minval = Double.parseDouble(jhaTlAudittemplate.get(rowid).getJautMaximumpoints());			
		return minval;
	}

	private List<JhaTlAudittemplate> jhAuditTemplateFillValues(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) {
		CommonMessage.debugMsg("after the fill values 12");
		
		CommonMessage.debugMsg("inside CommonFunctions 1");
		//comment by sriram - kiran sir
		// CommonMessage.debugMsg("after the......."+newJhaTlAuditparameter.getJhAuditTemplate().size());
		CommonMessage.debugMsg("inside CommonFunctions 2 : +newJhaTlAuditparameter.getJhAuditTemplate().size()");
		List<JhaTlAudittemplate> jhaTlAudittemplate = newJhaTlAuditparameter.getJhAuditTemplate();
		List<JhaTlAudittemplate> newJhaTlAudittemplateList = new ArrayList<JhaTlAudittemplate>();
		for( JhaTlAudittemplate newJhaTlAudittemplate :jhaTlAudittemplate)
		{
			CommonMessage.debugMsg("inside fillvalues 00001");
			newJhaTlAudittemplate.setJautActive("Y");
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			if(newJhaTlAudittemplate.getJautKeyid()== null )			
			{	
				newJhaTlAudittemplate.setJautCreatedon(dateTime);			
			}					
			else
			{	
				newJhaTlAudittemplate.setJautCreatedon(Constants.pgPassNullDateTime);
			}
			newJhaTlAudittemplate.setJautModifiedon(dateTime);
			if(newJhaTlAudittemplate.getJautMasterid() == null)
				newJhaTlAudittemplate.setJautMasterid("{}");
			if(newJhaTlAudittemplate.getJautParametername() == null)
				newJhaTlAudittemplate.setJautParametername("{}");
			else
	            newJhaTlAudittemplate.setJautParametername(
	                sanitizeForJson(newJhaTlAudittemplate.getJautParametername()));
			
			if(newJhaTlAudittemplate.getJautParameterdescription() == null)
				newJhaTlAudittemplate.setJautParameterdescription("{}");
			else
	            newJhaTlAudittemplate.setJautParameterdescription(
	                sanitizeForJson(newJhaTlAudittemplate.getJautParameterdescription()));
			
			if(newJhaTlAudittemplate.getJautEvidence() == null)
				newJhaTlAudittemplate.setJautEvidence("{}");
			else
	            newJhaTlAudittemplate.setJautEvidence(
	                sanitizeForJson(newJhaTlAudittemplate.getJautEvidence()));
			
			if(newJhaTlAudittemplate.getJautMaximumpoints() == null)
				newJhaTlAudittemplate.setJautMaximumpoints("{}");
			
			if(newJhaTlAudittemplate.getJautReviewPtSlno() == null)
				newJhaTlAudittemplate.setJautReviewPtSlno("0");
			
			if(newJhaTlAudittemplate.getJautCriteriaSlno() == null)
				newJhaTlAudittemplate.setJautCriteriaSlno("0");
			
			newJhaTlAudittemplate.setJautTempfield3("{}");
			newJhaTlAudittemplate.setJautTempfield4("{}");
			newJhaTlAudittemplate.setJautTempfield5("{}");			
			newJhaTlAudittemplateList.add(newJhaTlAudittemplate);			
		}
		CommonMessage.debugMsg("after the fill values 13");
		CommonMessage.debugMsg("inside fillvalues 00003");
		return newJhaTlAudittemplateList;
	}

	private List<JhaTlTemplatelevellink> jhLevelFillValues(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) {

		CommonMessage.debugMsg("after the fill values 9");
		List<JhaTlTemplatelevellink> jhaTlTemplatelevellink = newJhaTlAuditparameter.getJhLevelGrid();
		List<JhaTlTemplatelevellink> newJhaTlTemplatelevellinkList = new ArrayList<JhaTlTemplatelevellink>();
		for( JhaTlTemplatelevellink newJhaTlTemplatelevellink :jhaTlTemplatelevellink)
		{
			CommonMessage.debugMsg("entel the fill value 001");
			newJhaTlTemplatelevellink.setJtllActive("Y");
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			if(newJhaTlTemplatelevellink.getJtllAuditlevelid() == null )			
			{	
				newJhaTlTemplatelevellink.setJtllCreatedon(dateTime);			
			}					
			else
			{	
				newJhaTlTemplatelevellink.setJtllCreatedon(Constants.pgPassNullDateTime);
			}
			newJhaTlTemplatelevellink.setJtllModifiedon(dateTime);
			if(newJhaTlTemplatelevellink.getJtllAuditlevelid() == null)
				newJhaTlTemplatelevellink.setJtllAuditlevelid("{}");
			if(newJhaTlTemplatelevellink.getJtllTemplateid() == null)
				newJhaTlTemplatelevellink.setJtllTemplateid("{}");
			
			newJhaTlTemplatelevellink.setJtllTempfield1("{}");
			newJhaTlTemplatelevellink.setJtllTempfield2("{}");
			newJhaTlTemplatelevellink.setJtllTempfield3("{}");
			newJhaTlTemplatelevellink.setJtllTempfield4("{}");
			newJhaTlTemplatelevellink.setJtllTempfield5("{}");
			
			newJhaTlTemplatelevellinkList.add(newJhaTlTemplatelevellink);
			
		}
		CommonMessage.debugMsg("after the fill values 10");
		CommonMessage.debugMsg("entel the fill value 002");
		return newJhaTlTemplatelevellinkList;
	}

	private List<JhaTlTemplatesteplink> jhStepFillValues(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) 
	{
		CommonMessage.debugMsg("after the fill values 6");
		CommonMessage.debugMsg("JHaTLTemPlatesteplink:::"+newJhaTlAuditparameter.getJHStepGrid());
		List<JhaTlTemplatesteplink> jhaTlTemplatesteplink = newJhaTlAuditparameter.getJHStepGrid();
		
		List<JhaTlTemplatesteplink> newJhaTlTemplatesteplinkList = new ArrayList<JhaTlTemplatesteplink>();
		
		for( JhaTlTemplatesteplink newJhaTlTemplatesteplink :jhaTlTemplatesteplink)
		{
			newJhaTlTemplatesteplink.setJtslActive("Y");
			String dateTime = CommonFunctions.dateTimeNow();
			
			if(newJhaTlTemplatesteplink.getJtslTemplateid() == null )			
			{	
				newJhaTlTemplatesteplink.setJtslCreatedon(dateTime);			
			}					
			else
			{	
				newJhaTlTemplatesteplink.setJtslCreatedon(Constants.passNullDate);
			}
			newJhaTlTemplatesteplink.setJtslModifiedon(dateTime);
			if(newJhaTlTemplatesteplink.getJtslJhstepid() == null)
				newJhaTlTemplatesteplink.setJtslJhstepid("{}");
			if(newJhaTlTemplatesteplink.getJtslTemplateid() == null)
				newJhaTlTemplatesteplink.setJtslTemplateid("{}");
			newJhaTlTemplatesteplinkList.add(newJhaTlTemplatesteplink);
		}CommonMessage.debugMsg("after the fill values 7");
		return newJhaTlTemplatesteplinkList;
	}
	

	private List<JhaTlTemplatemchlink> equipmentFillValues(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter) {
		CommonMessage.debugMsg("after the fill values 2");
		List<JhaTlTemplatemchlink> jhaTlTemplatemchlink = newJhaTlAuditparameter.getEquipmentGrid();
		List<JhaTlTemplatemchlink> newJhaTlTemplatemchlinkList = new ArrayList<JhaTlTemplatemchlink>();
		CommonMessage.debugMsg("after the fill values 3");
		for( JhaTlTemplatemchlink newJhaTlTemplatemchlink :jhaTlTemplatemchlink){
			CommonMessage.debugMsg("for loop for the function");
		newJhaTlTemplatemchlink.setJtmlActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("after the fill values 3");
		if(newJhaTlTemplatemchlink.getJtmlTemplateid() == null )			
		{	
			newJhaTlTemplatemchlink.setJtmlCreatedon(dateTime);			
		}					
		else
		{	
			newJhaTlTemplatemchlink.setJtmlCreatedon(Constants.passNullDate);
		}
		CommonMessage.debugMsg("after the fill values 3");
		newJhaTlTemplatemchlink.setJtmlModtimestamp(dateTime);
		if(newJhaTlTemplatemchlink.getJtmlMachineid() == null)
			newJhaTlTemplatemchlink.setJtmlMachineid("{}");
		if(newJhaTlTemplatemchlink.getJtmlTemplateid() == null)
			newJhaTlTemplatemchlink.setJtmlTemplateid("{}");
		newJhaTlTemplatemchlinkList.add(newJhaTlTemplatemchlink);
	}
		CommonMessage.debugMsg("after the fill values 4");
		return newJhaTlTemplatemchlinkList;
	}

	@Override
	public void deleteParameter(String parameterId) throws ValidationExceptions, BusinessApplicationExceptions, Exception {
		
		 //jhaTlAuditparameterItcDao.deleteParameter(parameterId);
		jhauditSheetCreationItcServiApi.deleteAuditTemplate(parameterId);
	}

	@Override
	public JhaTlAuditparameter delete(JhaTlAuditparameter newJhaTlAuditparameter)	throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		try{
			return jhaTlAuditmstItcDao.deleteAuditPatameter(newJhaTlAuditparameter);
		}
		catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch (BusinessApplicationExceptions e){
	
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch (Exception e){
	
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	public List<String[]> getDMTMultiLevelAuditGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getDMTMultiLevelAuditGrid(commonFilter);
	}

	@Override
	public JhaTlTemplatelevellink getAppLvel(JhaTlTemplatelevellink jhaTlTemplatelevellink) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getAppLvel(jhaTlTemplatelevellink);
	}

	@Override
	public String getAuditLevelCurrent(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		//return jhaTlAuditmstItcDao.getAuditLevelCurrent(commonFilter);
		CommonMessage.debugMsg("AuditLevelCurrent");
		String flId = commonFilter.getJhTemplateId();
		  String level = jhauditSheetCreationItcServiApi.getAuditLevelCurrent(flId);
		  return level;
		  
		
	}
	
	@Override
	public String getSelectCnt(JhaTlAuditmst jhaTlAuditmst) throws Exception{
		
		
		CommonMessage.debugMsg("SelectCnt");
		//return jhaTlAuditmstItcDao.getSelectCnt(jhaTlAuditmst);
		  String flId = jhaTlAuditmst.getJhamFlid();
		  Long count = jhauditSheetCreationItcServiApi.getAuditCount(flId);
		  return count.toString();
	}

	@Override
	public List<String[]> getAuditReportGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getAuditReportGrid(commonFilter);
	}

	@Override
	public Workbook getjhAuditMultiGridExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getjhAuditMultiGridExportExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public String getJhLeader(String flid) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlAuditmstItcDao.getJhLeader(flid);
	}

	@Override
	public List<String[]> getlastauditgrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.jhaTlAuditmstItcDao.getlastauditgrid(commonFilter);
	}
	
	
	private String sanitizeForJson(String value) {
	    if (value == null) {
	        return null;
	    }
	    
	    return value
	        .replace("\\", "\\\\")  // Escape backslash FIRST
	        .replace("\"", "\\\"")  // Escape double quotes
	        .replace("\r", "\\r")   // Escape carriage return
	        .replace("\n", "\\n")   // Escape newline
	        .replace("\t", "\\t")   // Escape tab
	        .replace("\b", "\\b")   // Escape backspace
	        .replace("\f", "\\f");  // Escape form feed
	}
	
}
