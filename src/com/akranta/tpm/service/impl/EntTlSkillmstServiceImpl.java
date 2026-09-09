package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlSkillmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntTlSkillmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.service.EntTlSkillmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTlSkillmstServiceImpl implements EntTlSkillmstService{

	private EntTlSkillmstDao entTlSkillmstDao; 
	private CommonFilterDao commonFilterDao ;
	private Validations validations ;
	DBActionTemplate  dbActionTemplate;
	public EntTlSkillmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlSkillmstDao = new EntTlSkillmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	
	
	public List<EntTlSkillmst> getEntTlSkillmstValues(String factId,String DeptId) throws Exception {
		return this.entTlSkillmstDao.getEntTlSkillmstValues(factId,DeptId);
	}
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception{
		return this.entTlSkillmstDao.getAllLocation(functionalLocn);
	}
	public List<EntTlSkillmst> getAllSkill(EntTlSkillmst entTlSkillmst) throws Exception {
		return this.entTlSkillmstDao.getAllSkill(entTlSkillmst);
	}
	
	public EntTlSkillmst create(EntTlSkillmst newEntTlSkillmst,EntTlSkillmst oldEntTlSkillmst)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		try{
			String validationsFor="create";		
			validations.validate(newEntTlSkillmst,"EntTlTopicmst",validationsFor);	
			newEntTlSkillmst=fillValues( newEntTlSkillmst,  oldEntTlSkillmst);			
			return this.entTlSkillmstDao.create(newEntTlSkillmst);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	
	public EntTlSkillmst update(EntTlSkillmst newEntTlSkillmst,EntTlSkillmst oldEntTlSkillmst)throws Exception
	{
		try{
			String validationsFor="create";		
			validations.validate(newEntTlSkillmst,"EntTlSkillmst",validationsFor);	
			newEntTlSkillmst=fillValues( newEntTlSkillmst,  oldEntTlSkillmst);
			return this.entTlSkillmstDao.update(newEntTlSkillmst);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	
	public List<String []> getParentElem(String elemId) throws Exception {

		return this.entTlSkillmstDao.getParentElem(elemId);
	}
	
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end) throws Exception{
		return this.entTlSkillmstDao.getChildElem(childElem,formfield,start,end);
	}
	
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		return this.entTlSkillmstDao.getTotalCount(childElem,formfield);
	}
	
	@Override
	public String validateSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception{
		int menuLevel=0;
		int configLevel=0;
		String validate=null;
		menuLevel=this.entTlSkillmstDao.getSkillLevel(entTlSkillmst);		
		configLevel=this.entTlSkillmstDao.getConfigSkillLevel();		
		if (menuLevel<configLevel){
			validate="Valid";			
		}	
		else{
			validate=Convert.toString(configLevel);
		}
		
		return validate;
	}
	@Override
	public String validateDelSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception{
		int menuLevel=0;
		
		String validate="Not Valid";
		List<EntTlSkillmst> newEntTlSkillmst=new ArrayList<EntTlSkillmst>();
		newEntTlSkillmst=this.entTlSkillmstDao.getAllSkill(entTlSkillmst);
		menuLevel=newEntTlSkillmst.size();	
		CommonMessage.debugMsg("menuLevel:" + menuLevel);
		if (menuLevel<=0){
			validate="Valid";			
		}
		
		return validate;
	}
	
	@Override
	public List<ComboBox> getSkilParentComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skill = commonFilter.getSkilName();
		skill.setIdField("SKIL_KEYID");		
		skill.setNameField("SKIL_NAME ");	
		String FactId=commonFilter.getFactoryId();
		if (CommonFunctions.isValidKeyId(FactId))
			skill.setCondSql(" AND  SKIL_FACT_KEYID='" + FactId + "'");		
		skill.setTableName(TableNames.TBL_ENT_TL_SKILLMST);		
		return commonFilterDao.fillComboValues(skill);
	}	
	@Override
	public List<ComboBox> getSkilComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skill = commonFilter.getSkilName();
		skill.setIdField("SKIL_NAME");		
		skill.setNameField("SKIL_NAME ");
		skill.setCodeField("SKIL_CODE ");
		String FactId=commonFilter.getFactoryId();
		if (CommonFunctions.isValidKeyId(FactId))
			skill.setCondSql(" AND  SKIL_FACT_KEYID='" + FactId + "'");		
		skill.setTableName(TableNames.TBL_ENT_TL_SKILLMST);		
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
	@Override
	public List<ComboBox> getSkillTypeComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter skillType = commonFilter.getSkilName();
		skillType.setIdField("STYP_KEYID");		
		skillType.setNameField("STYP_NAME ");		
		skillType.setCondSql(" AND STYP_ACTIVE='Y' ");		
		skillType.setTableName(TableNames.TBL_ENT_TL_SKILLTYPE);		
		return commonFilterDao.fillComboValues(skillType);
	}	
	@Override
	public List<ComboBox> getDepartmentCombo() throws Exception {
		ComboFilter comboFilter = new ComboFilter();		
		comboFilter.setIdField("DEPT_KEYID");
		comboFilter.setCodeField("DEPT_NAME");
		comboFilter.setNameField("DEPT_CODE");
		comboFilter.setOrderByField("DEPT_NAME");	
		comboFilter.setCondSql(" AND DEPT_TYPE='N' ");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DEPARTMENTMST);		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	private EntTlSkillmst fillValues(EntTlSkillmst newEntTlSkillmst, EntTlSkillmst oldEntTlSkillmst)
	{
		newEntTlSkillmst.setSkilActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if(newEntTlSkillmst.getSkilKeyid()== null )			
		{	
			newEntTlSkillmst.setSkilCreatedon(dateTime);
			newEntTlSkillmst.setSkilEffectiveDate(dateTime);
			newEntTlSkillmst.setSkilInactiveDate(dateTime);			
		}
		else
		{
			newEntTlSkillmst.setSkilCreatedon(oldEntTlSkillmst.getSkilCreatedon());
			newEntTlSkillmst.setSkilEffectiveDate(oldEntTlSkillmst.getSkilEffectiveDate());
			newEntTlSkillmst.setSkilInactiveDate(oldEntTlSkillmst.getSkilInactiveDate());
			newEntTlSkillmst.setSkilIschild(oldEntTlSkillmst.getSkilIschild());	
		}
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilCellFunKeyid()))
			newEntTlSkillmst.setSkilCellFunKeyid("{}");
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilCellFunction()))
			newEntTlSkillmst.setSkilCellFunction("F");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilFactKeyid()))
			newEntTlSkillmst.setSkilFactKeyid("{}");
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilDeptKeyid()))
			newEntTlSkillmst.setSkilDeptKeyid("{}");			
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilCode()))
			newEntTlSkillmst.setSkilCode("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilName()))
			newEntTlSkillmst.setSkilName("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilRemarks()))
			newEntTlSkillmst.setSkilRemarks("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilParentid()))
			newEntTlSkillmst.setSkilParentid("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlSkillmst.getSkilIschild()))
			newEntTlSkillmst.setSkilIschild("Y");	
			
		newEntTlSkillmst.setSkilModifiedon(dateTime);	
		
		newEntTlSkillmst.setSkilTempfield1("-");
		newEntTlSkillmst.setSkilTempfield2("-");
		newEntTlSkillmst.setSkilTempfield3("-");
		newEntTlSkillmst.setSkilTempfield4("-");
		newEntTlSkillmst.setSkilTempfield5("-");
			
		return newEntTlSkillmst;
		
	}
	
	@Override
	public EntTlSkillmst delete(EntTlSkillmst entTlSkillmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		try{
			return this.entTlSkillmstDao.delete(entTlSkillmst);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	
	@Override
	public EntTlSkillmst select(EntTlSkillmst entTlSkillmst) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlSkillmstDao.select(entTlSkillmst);
	}
	
	@Override
	public List<EntTlSkillmst> selectList(EntTlSkillmst entTlSkillmst)throws Exception{
		// TODO Auto-generated method stub
		return this.entTlSkillmstDao.selectList(entTlSkillmst);
	}
	
	@Override
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception{
		return this.entTlSkillmstDao.getSearchNode(searchNode,originalId);
	}
	
	@Override
	public List<String[]> getSearchSkillLevel(String originalId) throws Exception{
		return this.entTlSkillmstDao.getSearchSkillLevel(originalId);
	}
	
	public List<String[]> getSkillLevelInvent(CommonFilter commonFilter)throws Exception {
		return this.entTlSkillmstDao.getSkillLevelInvent(commonFilter);
	}
	@Override
	public Workbook skillLevelInventoryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return this.entTlSkillmstDao.skillLevelInventoryExportExcel(commonFilter,tblJSONObj,format);
	}
	
	
	public void getAllSkillLevel(List<String[]> skillLevel) throws Exception {
		 this.entTlSkillmstDao.getAllSkillLevel(skillLevel);
		
	}
	@Override
	public List<String[]> getAllresource() throws Exception {
		// TODO Auto-generated method stub
		return entTlSkillmstDao.getResource();
	}
}
