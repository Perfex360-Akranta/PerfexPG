package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.TargetGrp;
import com.akranta.tpm.bean.Uniquepositionbean;
//import com.akranta.tpm.bean.Vocchecklistbean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlTopicmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.EntTlTopicmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupdtl;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlRolemst;
import com.akranta.tpm.service.EntTlTopicmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTlTopicmstServiceImpl implements EntTlTopicmstService{

	private EntTlTopicmstDao entTlTopicmstDao; 
	private CommonFilterDao commonFilterDao ;
	private Validations validations ;
	public EntTlTopicmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlTopicmstDao = new EntTlTopicmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	
	
	/*public List<EntTlTopicmst> getEntTlTopicmstValues(String factId,String DeptId) throws Exception {
		return this.entTlTopicmstDao.getEntTlTopicmstValues(factId,DeptId);
	}
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception{
		return this.entTlTopicmstDao.getAllLocation(functionalLocn);
	}
	public List<EntTlTopicmst> getAllSkill(EntTlTopicmst entTlTopicmst) throws Exception {
		return this.entTlTopicmstDao.getAllSkill(entTlTopicmst);
	}*/
	
	public EntTlTopicmst create(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst,String trainingAreaParentId)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
			String validationsFor="create";		
			validations.validate(newEntTlTopicmst,"EntTlTopicmst",validationsFor);	
			newEntTlTopicmst=fillValues( newEntTlTopicmst,  oldEntTlTopicmst);		
			EntTlTrainingarea newEntTlTrainingarea=new EntTlTrainingarea();
			newEntTlTrainingarea=fillTrValues (newEntTlTopicmst,  newEntTlTrainingarea,trainingAreaParentId);	
			return this.entTlTopicmstDao.create(newEntTlTopicmst,newEntTlTrainingarea);
	}
	
	public EntTlTopicmst update(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst)throws Exception
	{
			String validationsFor="update";		
			validations.validate(newEntTlTopicmst,"EntTlTopicmst",validationsFor);	
			newEntTlTopicmst=fillValues( newEntTlTopicmst,  oldEntTlTopicmst);
			return this.entTlTopicmstDao.update(newEntTlTopicmst);
	}
	
	/*public List<String []> getParentElem(String elemId) throws Exception {

		return this.entTlTopicmstDao.getParentElem(elemId);
	}
	
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end) throws Exception{
		return this.entTlTopicmstDao.getChildElem(childElem,formfield,start,end);
	}
	
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		return this.entTlTopicmstDao.getTotalCount(childElem,formfield);
	}*/
	
	@Override
	/*public String validateSkillLevel(EntTlTopicmst entTlTopicmst)throws Exception{
		int menuLevel=0;
		int configLevel=0;
		String validate=null;
		menuLevel=this.entTlTopicmstDao.getSkillLevel(entTlTopicmst);		
		configLevel=this.entTlTopicmstDao.getConfigSkillLevel();		
		if (menuLevel<configLevel){
			validate="Valid";			
		}	
		else{
			validate=Convert.toString(configLevel);
		}
		
		return validate;
	}
	@Override
	public String validateDelSkillLevel(EntTlTopicmst entTlTopicmst)throws Exception{
		int menuLevel=0;
		
		String validate="Not Valid";
		List<EntTlTopicmst> newEntTlTopicmst=new ArrayList<EntTlTopicmst>();
		newEntTlTopicmst=this.entTlTopicmstDao.getAllSkill(entTlTopicmst);
		menuLevel=newEntTlTopicmst.size();	
		CommonMessage.debugMsg("menuLevel:" + menuLevel);
		if (menuLevel<=0){
			validate="Valid";			
		}
		
		return validate;
	}
	*/
	
	public List<ComboBox> getTopiParentComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skill = commonFilter.getSkilName();
		CommonMessage.debugMsg("PARENT");
		skill.setIdField("TOPI_KEYID");		
		skill.setNameField("TOPI_NAME ");	
		skill.setTableName(TableNames.TBL_ENT_TL_TOPICMST);		
		return commonFilterDao.fillComboValues(skill);
	}	
	@Override
	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		
		
		ComboFilter skill = new ComboFilter();	
		skill.setIdField("TOPI_KEYID");			
		skill.setNameField("TOPI_NAME ");
		
		skill.setCodeField("TOPI_CODE ");
		skill.setTableName(TableNames.TBL_ENT_TL_TOPICMST);		
		return commonFilterDao.fillComboValues(skill);
	}	
	@Override
	public List<ComboBox> getEvaluationTypeComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skill = commonFilter.getSkilName();
		skill.setIdField("EVAL_KEYID");		
		skill.setNameField("EVAL_NAME ");		
		skill.setCondSql(" AND EVAL_ACTIVE='Y' ");		
		skill.setTableName(TableNames.TBL_ENT_TL_EVALUATIONTYPEMST);		
		return commonFilterDao.fillComboValues(skill);
	}
	 
		public List<ComboBox> getDeliveryModeCombo(ComboFilter comboFilter)
				throws Exception{
			comboFilter.setNameField("TMOD_NAME");
			comboFilter.setCode("TMOD_CODE");
			comboFilter.setIdField("TMOD_KEYID");
			
			comboFilter.setCondSql(" AND TMOD_ACTIVE='Y' ");	
			comboFilter.setTableName(TableNames.TBL_ENT_TL_DELIVERYMODEMST);
			return commonFilterDao.fillComboValues(comboFilter);
		}
	@Override
	public List<ComboBox> getCategoryComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skill = commonFilter.getSkilName();
		skill.setIdField("TCAT_KEYID");		
		skill.setNameField("TCAT_NAME ");		
		skill.setCondSql(" AND TCAT_ACTIVE='Y' ");		
		skill.setTableName("ENT_TL_TOPICCATEGORYMST");		
		return commonFilterDao.fillComboValues(skill);
	}
	@Override
	public List<ComboBox> getTopiTypeComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skillType = commonFilter.getSkilName();
		skillType.setIdField("STYP_KEYID");		
		skillType.setNameField("STYP_NAME ");		
		skillType.setCondSql(" AND STYP_ACTIVE='Y' ");		
		skillType.setTableName(TableNames.TBL_ENT_TL_SKILLTYPE);		
		return commonFilterDao.fillComboValues(skillType);
	}	
	@Override
	public List<ComboBox> getLocationCombo() throws Exception {
		ComboFilter comboFilter = new ComboFilter();		
		comboFilter.setIdField("LOCN_KEYID");
		comboFilter.setCodeField("LOCN_NAME");
		comboFilter.setNameField("LOCN_CODE");
		comboFilter.setOrderByField("LOCN_NAME");	
		comboFilter.setCondSql(" AND LOCN_ACTIVE='Y' ");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	private EntTlTopicmst fillValues(EntTlTopicmst newEntTlTopicmst, EntTlTopicmst oldEntTlTopicmst)
	{
		CommonMessage.debugMsg("inside service impl");
		newEntTlTopicmst.setTopiActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if(newEntTlTopicmst.getTopiKeyid()== null )			
		{	
			newEntTlTopicmst.setTopiCreatedon(dateTime);
			newEntTlTopicmst.setTopiEffectiveDate(dateTime);
			newEntTlTopicmst.setTopiInactiveDate(dateTime);			
		}
		else
		{
			newEntTlTopicmst.setTopiCreatedon(oldEntTlTopicmst.getTopiCreatedon());
			newEntTlTopicmst.setTopiEffectiveDate(oldEntTlTopicmst.getTopiEffectiveDate());
			newEntTlTopicmst.setTopiInactiveDate(oldEntTlTopicmst.getTopiInactiveDate());
			newEntTlTopicmst.setTopiIschild(oldEntTlTopicmst.getTopiIschild());	
		}		
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiLocationid()))
			newEntTlTopicmst.setTopiLocationid("{}");			
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiCode()))
			newEntTlTopicmst.setTopiCode("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiName()))
			newEntTlTopicmst.setTopiName("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiRemarks()))
			newEntTlTopicmst.setTopiRemarks("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiParentid()))
			newEntTlTopicmst.setTopiParentid("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiIschild()))
			newEntTlTopicmst.setTopiIschild("Y");	
			
		newEntTlTopicmst.setTopiModifiedon(dateTime);	
		if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiRelatedto()))
			newEntTlTopicmst.setTopiRelatedto("-");
		if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiTrainingmode()))
		newEntTlTopicmst.setTopiTrainingmode("-");
		newEntTlTopicmst.setTopiCategory("-");
		newEntTlTopicmst.setTopiCategory("-");
		CommonMessage.debugMsg("inside service impl"+newEntTlTopicmst);
		return newEntTlTopicmst;
		
	}
	private EntTlTrainingarea fillTrValues(EntTlTopicmst newEntTlTopicmst, EntTlTrainingarea newEntTlTrainingarea,String trParentId)
	{		
		newEntTlTrainingarea.setTrarActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();		
		newEntTlTrainingarea.setTrarCreatedon(dateTime);	
		newEntTlTrainingarea.setTrarModifiedon(dateTime);	
		newEntTlTrainingarea.setTrarCreatedby(newEntTlTopicmst.getTopiCreatedby());
		newEntTlTrainingarea.setTrarLocationid(newEntTlTopicmst.getTopiLocationid());
		newEntTlTrainingarea.setTrarName(newEntTlTopicmst.getTopiName());	
		newEntTlTrainingarea.setTrarParentid(trParentId);	
		newEntTlTrainingarea.setTrarElementtype("TOPIC");
		newEntTlTrainingarea.setTrarReftype("TOP");	
		newEntTlTrainingarea.setTrarRemarks("{}");
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarLevelno()))
			newEntTlTrainingarea.setTrarLevelno("7");	
		
		/*if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarRefid()))
			newEntTlTrainingarea.setTrarRefid("{}");*/	
		
		newEntTlTrainingarea.setTrarTempfield1("-");
		newEntTlTrainingarea.setTrarTempfield2("-");
		newEntTlTrainingarea.setTrarTempfield3("-");
		newEntTlTrainingarea.setTrarTempfield4("-");
		newEntTlTrainingarea.setTrarTempfield5("-");
		newEntTlTrainingarea.setTrarTempfield6("-");
		newEntTlTrainingarea.setTrarTempfield7("-");
		newEntTlTrainingarea.setTrarTempfield8("-");
		newEntTlTrainingarea.setTrarTempfield9("-");
		newEntTlTrainingarea.setTrarTempfield10("-");			
		return newEntTlTrainingarea;
	}
	@Override
	public EntTlTopicmst delete(EntTlTopicmst entTlTopicmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
			return this.entTlTopicmstDao.delete(entTlTopicmst);
	}
	
	@Override
	public EntTlTopicmst select(EntTlTopicmst entTlTopicmst) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.select(entTlTopicmst);
	}
	@Override
	public List<String[]> getselect(CommonFilter commonfilter) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getselect(commonfilter);
	}
	@Override
	public GenTlRolemst createunique(GenTlRolemst newgentlrolemst,
			GenTlRolemst existGenTlRolemst, Uniquepositionbean uniquebean) throws Exception,ValidationExceptions {
		// TODO Auto-generated method stub
			String roleType = uniquebean.getRoleelementType();
			CommonMessage.debugMsg("service impl roleType="+roleType );
			
			CommonMessage.debugMsg("after service impl "+roleType );
			
			if(  roleType.equals("CELL") || roleType.equals("SECT")) {
				CommonMessage.debugMsg("insider not JH roleType"+roleType);
			}
			else{
				CommonMessage.debugMsg("insider not JH roleType"+roleType);
				throw new ValidationExceptions("RoleelementType-required");
				// validations.validate(uniquebean,"unique","create");
			}
			//validations.validate(newgentlrolemst,"unique","create");
			CommonMessage.debugMsg("JH roleType"+roleType);
			
			fillValues( newgentlrolemst,  existGenTlRolemst,uniquebean);	
			return this.entTlTopicmstDao.createunique(newgentlrolemst);
	}
	
	@Override
	public GenTlRolemst updateunique(GenTlRolemst newgentlrolemst,
			GenTlRolemst existGenTlRolemst, Uniquepositionbean uniquebean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg("service impl");
			String roleType = uniquebean.getRoleelementType();
			if(  roleType.equals("CELL") || roleType.equals("SECT")) {
				CommonMessage.debugMsg("insider  JH roleType"+roleType);
			}
			else{
				CommonMessage.debugMsg("insider not JH roleType"+roleType);
				throw new ValidationExceptions("RoleelementType-required");
				// validations.validate(uniquebean,"unique","create");
			}
			//validations.validate(newgentlrolemst,"unique","update");
			 
		fillValues( newgentlrolemst,  existGenTlRolemst,uniquebean);	
		return this.entTlTopicmstDao.updateunique(newgentlrolemst);
	}
	
	
	
	private GenTlRolemst fillValues(GenTlRolemst newgentlrolemst,
			GenTlRolemst existGenTlRolemst, Uniquepositionbean uniquebean) {
		// TODO Auto-generated method stub
		newgentlrolemst.setRoleActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleDescription()))
			newgentlrolemst.setRoleDescription("{}");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleFactKeyid()))
			newgentlrolemst.setRoleFactKeyid("{}");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleName()))
			newgentlrolemst.setRoleName("{}");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleRemarks()))
			newgentlrolemst.setRoleRemarks("{}");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleModifiedon()))
			newgentlrolemst.setRoleModifiedon(dateTime);
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleCreatedon()))
			newgentlrolemst.setRoleCreatedon(dateTime);
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleFlid()))
			newgentlrolemst.setRoleFlid("-");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleElementId()))
			newgentlrolemst.setRoleElementId("-");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleTempfield3()))
			newgentlrolemst.setRoleTempfield3("-");
		
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleCode()))
			newgentlrolemst.setRoleCode("{}");
		if(!CommonFunctions.isValidKeyId(newgentlrolemst.getRoleLevel()))
			newgentlrolemst.setRoleLevel("0");
		
		return newgentlrolemst;
		
		
	}
	@Override
	public List<String[]> getselectmain(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		return this.entTlTopicmstDao.getselectmain(commonFilter);
	}
	@Override
	public GenTlRolemst getrolemain(String keyId) {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getrolemain(keyId);
	}
	@Override
	public GenTlRolemst deleteunique(GenTlRolemst newgentlrolemst) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.deleteunique(newgentlrolemst);
	}
	@Override
	public List<String[]> getselectdEmpRole(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlTopicmstDao.getselectdEmpRole(commonFilter);
	}
	@Override
	public List<String[]> getselectmaintarget(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getselectmaintarget(commonFilter);
	}
	@Override
	public EntTlTargetgroupmst gettargetmain(String keyId) {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.gettargetmain(keyId);
	}
	@Override
	public List<String[]> getselecttarget(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getselecttarget(commonFilter);
	}
	@Override
	public EntTlTargetgroupmst createtarget(EntTlTargetgroupmst newEntTlTargetGroupmst,EntTlTargetgroupmst existEntTlTargetgroupmst,TargetGrp targetGrpbean) throws Exception {
		// TODO Auto-generated method stub		
			CommonMessage.debugMsg("service impl");
			validations.validate(newEntTlTargetGroupmst,"unique","create");
			fillValues( newEntTlTargetGroupmst,  existEntTlTargetgroupmst,targetGrpbean);	
			return this.entTlTopicmstDao.createtarget(newEntTlTargetGroupmst);
	}
	

	@Override
	public EntTlTargetgroupmst updatetarget(EntTlTargetgroupmst newEntTlTargetGroupmst,EntTlTargetgroupmst existEntTlTargetgroupmst,TargetGrp targetGrpbean) throws Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg("service impl");
			validations.validate(newEntTlTargetGroupmst,"unique","update");
			fillValues( newEntTlTargetGroupmst,  existEntTlTargetgroupmst,targetGrpbean);
			CommonMessage.debugMsg(" service impl 0");
			return this.entTlTopicmstDao.updatetarget(newEntTlTargetGroupmst);
	}
	private void fillValues(EntTlTargetgroupmst newEntTlTargetGroupmst,
			EntTlTargetgroupmst existEntTlTargetgroupmst,
			TargetGrp targetGrpbean) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" service impl 1");
		newEntTlTargetGroupmst.setTgtmActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmTitle()))
			newEntTlTargetGroupmst.setTgtmTitle("{}");
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmFlid()))
			newEntTlTargetGroupmst.setTgtmFlid("{}");
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmFlid()))
			newEntTlTargetGroupmst.setTgtmFlid("{}");
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmTempfield2()))
			newEntTlTargetGroupmst.setTgtmTempfield2("-");
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmTempfield3()))
			newEntTlTargetGroupmst.setTgtmTempfield3("-");
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmCreatedon()))
			newEntTlTargetGroupmst.setTgtmCreatedon(dateTime);
		if(!CommonFunctions.isValidKeyId(newEntTlTargetGroupmst.getTgtmModifiedon()))
			newEntTlTargetGroupmst.setTgtmModifiedon(dateTime);
		
		newEntTlTargetGroupmst.setTargetgroupdtl( fillValuesnewEntTlTargetGroupmst( newEntTlTargetGroupmst, existEntTlTargetgroupmst))	;
	}
	private List<EntTlTargetgroupdtl> fillValuesnewEntTlTargetGroupmst(
			EntTlTargetgroupmst newEntTlTargetGroupmst,
			EntTlTargetgroupmst existEntTlTargetgroupmst) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" service impl 2");
		List<EntTlTargetgroupdtl> entTlTargetgroupdtl = newEntTlTargetGroupmst.getTargetgroupdtl();
		List<EntTlTargetgroupdtl> existEntTlTargetgroupdtl = newEntTlTargetGroupmst.getTargetgroupdtl();
		EntTlTargetgroupdtl exitentTlTargetgroupdtll = null;
		CommonMessage.debugMsg(" service impl3");
		if(existEntTlTargetgroupmst != null){
			existEntTlTargetgroupdtl =existEntTlTargetgroupmst.getTargetgroupdtl();
			if(existEntTlTargetgroupdtl != null && existEntTlTargetgroupdtl.size()>0)
				exitentTlTargetgroupdtll=existEntTlTargetgroupdtl.get(0);
		}
			
		
		List<EntTlTargetgroupdtl> newenttlTargetgroupdtl = new ArrayList<EntTlTargetgroupdtl>();
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(" service impl4");
		for( EntTlTargetgroupdtl newEntTlTargetgroupdtl : entTlTargetgroupdtl)
			
		{
			
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdActive()))
				newEntTlTargetgroupdtl.setTgtdActive("Y");
			CommonMessage.debugMsg(" service impl5");
			newEntTlTargetgroupdtl.setTgtdCreatedby(newEntTlTargetGroupmst.getTgtmCreatedby());
			CommonMessage.debugMsg(" service impl6");
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdCreatedon()))
				newEntTlTargetgroupdtl.setTgtdCreatedon(dateTime);
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdEmpid()))
				newEntTlTargetgroupdtl.setTgtdEmpid("{}");
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdModifiedon()))
				newEntTlTargetgroupdtl.setTgtdModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdTempfield1()))
				newEntTlTargetgroupdtl.setTgtdTempfield1("-");
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdTempfield2()))
				newEntTlTargetgroupdtl.setTgtdTempfield2("-");
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdTempfield3()))
				newEntTlTargetgroupdtl.setTgtdTempfield3("-");
			if(!UIUtils.isValidKeyId(newEntTlTargetgroupdtl.getTgtdUniquepositionid()))
				newEntTlTargetgroupdtl.setTgtdUniquepositionid("{}");		
			CommonMessage.debugMsg(" service impl7");
			newenttlTargetgroupdtl.add(newEntTlTargetgroupdtl);
		}
		return newenttlTargetgroupdtl;
	}
	@Override
	public EntTlTargetgroupmst deletetarget(EntTlTargetgroupmst entTlTargetgroupmst,EntTlTargetgroupmst existEntTlTargetgroupmst,
			TargetGrp TargetGrpbean) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.deletetarget(entTlTargetgroupmst);
	}
	@Override
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.gettargetExcel(colmodel,format, commonFilter);
	}
	@Override
	public EntTlTargetgroupdtl deletetargetdtl(List<EntTlTargetgroupdtl> newentTlTargetgroupdtl) throws Exception {
		// TODO Auto-generated method stub
		
		return this.entTlTopicmstDao.deletetargetdtl(newentTlTargetgroupdtl);
	}
	@Override
	public GenTlRolemst deleteUPEmployee(GenTlRolemst newgentlrolemst)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlTopicmstDao.deleteUPEmployee(newgentlrolemst);
	}
	
	//addition
	@Override
	public Workbook getUniquePositionExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		CommonMessage.debugMsg("U R in : EntTopicmst Service") ;
		return entTlTopicmstDao.getUniquePositionExportExcel(commonFilter, tblJSONObj, format);
	}

	
	/*@Override
	public List<EntTlTopicmst> selectList(EntTlTopicmst entTlTopicmst)throws Exception{
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.selectList(entTlTopicmst);
	}
	
	@Override
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception{
		return this.entTlTopicmstDao.getSearchNode(searchNode,originalId);
	}
	
	@Override
	public List<String[]> getSearchSkillLevel(String originalId) throws Exception{
		return this.entTlTopicmstDao.getSearchSkillLevel(originalId);
	}*/
}
