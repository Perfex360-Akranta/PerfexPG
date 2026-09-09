package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlFeedbackDao;
import com.akranta.tpm.dao.EntTlFeedbackparammstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlFeedbackDaoImpl;
import com.akranta.tpm.dao.impl.EntTlFeedbackparammstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlFeedback;
import com.akranta.tpm.model.EntTlFeedbackparammst;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.service.ENTProgFBService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class ENTProgFBServiceImpl implements ENTProgFBService{
	
	private EntTlFeedbackparammstDao entTlFeedbackparammstDao;
	private EntTlFeedbackDao entTlFeedbackDao;
	private CommonFilterDao commonFilterDao ;
	private Validations validations ;
	public ENTProgFBServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlFeedbackparammstDao = new EntTlFeedbackparammstDaoImpl(dbActionTemplate);
		entTlFeedbackDao = new EntTlFeedbackDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public List<EntTlFeedbackparammst> getAllFeedback(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception
	{
		return this.entTlFeedbackparammstDao.getAllFeedback(entTlFeedbackparammst);
	}
	public EntTlFeedbackparammst select(String keyid) throws Exception
	{
		return this.entTlFeedbackparammstDao.select(keyid);
	}
	public EntTlFeedbackparammst inactiveFB(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception
	{
		try{
			String validationsFor="inactive";		
			validations.validate(entTlFeedbackparammst,"EntFeedbackProg",validationsFor);							
			return this.entTlFeedbackparammstDao.inactiveFB(entTlFeedbackparammst);
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
	public EntTlFeedbackparammst create(
			EntTlFeedbackparammst newEntTlFeedbackparammst,
			EntTlFeedbackparammst oldEntTlFeedbackparammst) throws Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor="create";		
			validations.validate(newEntTlFeedbackparammst,"EntFeedbackProg",validationsFor);	
			newEntTlFeedbackparammst=fillValues( newEntTlFeedbackparammst,  oldEntTlFeedbackparammst);	
			String dispOrder = newEntTlFeedbackparammst.getFbpmDisplayorder();
			if(UIUtils.isValidKeyId(dispOrder))
			{
				if(dispOrder.equals("First"))
					newEntTlFeedbackparammst.setFbpmDisplayorder("1");
				else
				{
					Integer doCount = Integer.parseInt(dispOrder);
					doCount = doCount +1; 
					newEntTlFeedbackparammst.setFbpmDisplayorder(doCount.toString());
				}
			}
			else
			{
				newEntTlFeedbackparammst.setFbpmDisplayorder("1");
			}
			return this.entTlFeedbackparammstDao.create(newEntTlFeedbackparammst);
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
	public EntTlFeedbackparammst update(
			EntTlFeedbackparammst newEntTlFeedbackparammst,
			EntTlFeedbackparammst oldEntTlFeedbackparammst) throws Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor="update";		
			validations.validate(newEntTlFeedbackparammst,"EntFeedbackProg",validationsFor);	
			newEntTlFeedbackparammst=fillValues( newEntTlFeedbackparammst,  oldEntTlFeedbackparammst);			
			return this.entTlFeedbackparammstDao.update(newEntTlFeedbackparammst);
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
	public EntTlFeedbackparammst delete(
			EntTlFeedbackparammst entTlFeedbackparammst) throws Exception {
		// TODO Auto-generated method stub
		try{
			return this.entTlFeedbackparammstDao.delete(entTlFeedbackparammst);
		}
		catch (ValidationExceptions e){
			
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	
	public EntTlFeedback create(EntTlFeedback newEntTlFeedback,EntTlFeedback oldEntTlFeedback) throws Exception
	{
		try{
			//String validationsFor="create";		
			//validations.validate(newEntTlFeedbackparammst,"EntFeedbackProg",validationsFor);	
			newEntTlFeedback=fillFBValues( newEntTlFeedback,  oldEntTlFeedback);					
			return this.entTlFeedbackDao.create(newEntTlFeedback);
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
	public EntTlFeedback update(EntTlFeedback newEntTlFeedback,EntTlFeedback oldEntTlFeedback) throws Exception
	{
		try{
			//String validationsFor="update";		
			//validations.validate(newEntTlFeedbackparammst,"EntFeedbackProg",validationsFor);	
			newEntTlFeedback=fillFBValues( newEntTlFeedback,  oldEntTlFeedback);			
			return this.entTlFeedbackDao.update(newEntTlFeedback);
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
	public EntTlFeedback delete(EntTlFeedback entTlFeedback) throws Exception
	{
		try{
			return this.entTlFeedbackDao.delete(entTlFeedback);
		}
		catch (ValidationExceptions e){
			
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	public List<ComboBox> getComboFB(String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("FBPM_DESCRIPTION");
		comboFilter.setIdField("FBPM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_ENT_TL_FEEDBACKPARAMMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getComboFBParent(String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("FBPM_DESCRIPTION");
		comboFilter.setIdField("FBPM_KEYID");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_FEEDBACKPARAMMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public String getDisplayOrder(String parentId) throws Exception
	{
		return this.entTlFeedbackparammstDao.getDisplayOrder(parentId);
	}
	public List<String[]> getEmployees(String progId,String fromDate,String toDate) throws Exception
	{
		return this.entTlFeedbackparammstDao.getEmployees(progId,fromDate,toDate);
	}
	public List<String[]> getQuestions() throws Exception
	{
		return this.entTlFeedbackparammstDao.getQuestions();
	}
	public List<String[]> getFBDatas(String empId,String progId) throws Exception
	{
		return this.entTlFeedbackparammstDao.getFBDatas(empId,progId);
	}
	public List<String[]> getRating(String empId) throws Exception
	{
		return this.entTlFeedbackparammstDao.getRating(empId);
	}
	private EntTlFeedbackparammst fillValues(EntTlFeedbackparammst newEntTlFeedbackparammst,EntTlFeedbackparammst oldEntTlFeedbackparammst)
	{
		newEntTlFeedbackparammst.setFbpmActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		/*if(UIUtils.isValidKeyId(oldEntTlFeedbackparammst.getFbpmKeyid()))
		{
			newEntTlFeedbackparammst.setFbpmCreatedon(oldEntTlFeedbackparammst.getFbpmCreatedon());
			newEntTlFeedbackparammst.setFbpmEffectiveDate(oldEntTlFeedbackparammst.getFbpmEffectiveDate());
			newEntTlFeedbackparammst.setFbpmInactiveDate(oldEntTlFeedbackparammst.getFbpmInactiveDate());
		}
		else
		{*/
			newEntTlFeedbackparammst.setFbpmCreatedon(dateTime);
			newEntTlFeedbackparammst.setFbpmEffectiveDate(dateTime);
			newEntTlFeedbackparammst.setFbpmInactiveDate(Constants.passNullDate);
		//}
		newEntTlFeedbackparammst.setFbpmModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmCode()))
			newEntTlFeedbackparammst.setFbpmCode("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmDescription()))
			newEntTlFeedbackparammst.setFbpmDescription("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmType()))
			newEntTlFeedbackparammst.setFbpmType("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmRemarks()))
			newEntTlFeedbackparammst.setFbpmRemarks("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmDisplayorder()))
			newEntTlFeedbackparammst.setFbpmDisplayorder("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmIschild()))
			newEntTlFeedbackparammst.setFbpmIschild("Y");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmTempfield1()))
			newEntTlFeedbackparammst.setFbpmTempfield1("-");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmTempfield2()))
			newEntTlFeedbackparammst.setFbpmTempfield2("-");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmTempfield3()))
			newEntTlFeedbackparammst.setFbpmTempfield3("-");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmTempfield4()))
			newEntTlFeedbackparammst.setFbpmTempfield4("-");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedbackparammst.getFbpmTempfield5()))
			newEntTlFeedbackparammst.setFbpmTempfield5("-");
		
		return newEntTlFeedbackparammst;
	}
	
	private EntTlFeedback fillFBValues(EntTlFeedback newEntTlFeedback,EntTlFeedback oldEntTlFeedback)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		
		newEntTlFeedback.setFeedCreatedon(dateTime);
		newEntTlFeedback.setFeedModifiedon(dateTime);
		newEntTlFeedback.setFeedActive("Y");
		newEntTlFeedback.setFeedTempfield1("-");
		newEntTlFeedback.setFeedTempfield2("-");
		newEntTlFeedback.setFeedTempfield3("-");
		newEntTlFeedback.setFeedTempfield4("-");
		newEntTlFeedback.setFeedTempfield5("-");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedback.getFeedAnswer()))
			newEntTlFeedback.setFeedAnswer("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedback.getFeedFermKeyid()))
			newEntTlFeedback.setFeedFermKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedback.getFeedFbpmKeyid()))
			newEntTlFeedback.setFeedFbpmKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlFeedback.getFeedFrqdKeyid()))
			newEntTlFeedback.setFeedFrqdKeyid("{}");
		
		return newEntTlFeedback;
	}

	
	

}
