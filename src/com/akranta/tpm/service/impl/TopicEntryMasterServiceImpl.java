package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlTopicmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntTlTopicmstDaoImpl;
import com.akranta.tpm.dao.impl.GenTlMommstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTopicLinkRoledtl;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.GenTlRolemst;
import com.akranta.tpm.service.TopicEntryMasterService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class TopicEntryMasterServiceImpl implements TopicEntryMasterService
{
	private EntTlTopicmstDao entTlTopicmstDao;
	private Validations validations;
	private CommonFilterDao commonFilterDao;
	public TopicEntryMasterServiceImpl(DBActionTemplate dbActionTemplate) {
		entTlTopicmstDao = new EntTlTopicmstDaoImpl(dbActionTemplate);
		// genTlMomatendanceDao = new
		// GenTlMomattendanceDaoImpl(dbActionTemplate);
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);

	}

	@Override
	public List<String[]> getTopicDet(CommonFilter commonFilter,String TopicKeyid) {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getTopicDet(commonFilter,TopicKeyid);
	}
	public Workbook TopicExportExcel(CommonFilter commonFilter, JSONObject colmodel,String format) throws SQLException, Exception {
		return this.entTlTopicmstDao.TopicExportExcel(commonFilter,colmodel,format);
	}
	public EntTlTopicmst create(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst,String trRoleid)throws ValidationExceptions,BusinessApplicationExceptions,Exception
	{
			String validationsFor="createtopic";
			validations.validate(newEntTlTopicmst,"TopicEntrymst",validationsFor);				
			newEntTlTopicmst=fillValues( newEntTlTopicmst,  oldEntTlTopicmst);
			EntTlTopicLinkRoledtl newEntTlLinkRoledtl=new EntTlTopicLinkRoledtl();
			newEntTlLinkRoledtl=fillTrValues (newEntTlTopicmst, newEntTlLinkRoledtl,trRoleid);	       			
			return this.entTlTopicmstDao.createTopic(newEntTlTopicmst,newEntTlLinkRoledtl);
	}


	private EntTlTopicLinkRoledtl fillTrValues(EntTlTopicmst newEntTlTopicmst,EntTlTopicLinkRoledtl newEntTlLinkRoledtl,String trRoleid)
	{
		CommonMessage.debugMsg("fill values Detal");
		newEntTlLinkRoledtl.setToprActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();		
		newEntTlLinkRoledtl.setToprCreatedon(dateTime);	
		newEntTlLinkRoledtl.setToprModifiedon(dateTime);	
		newEntTlLinkRoledtl.setToprCreatedby(newEntTlTopicmst.getTopiCreatedby());
		CommonMessage.debugMsg("fill values Detal 2");
		CommonMessage.debugMsg("createeee "+newEntTlLinkRoledtl.getToprCreatedon());	
		newEntTlLinkRoledtl.setToprTopiKeyid(newEntTlTopicmst.getTopiKeyid());	
		newEntTlLinkRoledtl.setToprRoleKeyid(trRoleid);					
		/*if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarRefid()))
			newEntTlTrainingarea.setTrarRefid("{}");*/	
		
		newEntTlLinkRoledtl.setToprTempfield1("-");
		newEntTlLinkRoledtl.setToprTempfield2("-");
		newEntTlLinkRoledtl.setToprTempfield3("-");
		newEntTlLinkRoledtl.setToprTempfield4("-");
		newEntTlLinkRoledtl.setToprTempfield5("-");
		CommonMessage.debugMsg("fill values Detal End  ");	
		return newEntTlLinkRoledtl;	
	}

	private EntTlTopicmst fillValues(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst) {
	
		CommonMessage.debugMsg("inside service impl");
		newEntTlTopicmst.setTopiActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
	//	if(newEntTlTopicmst.getTopiKeyid()== null )			
	//	{	
			newEntTlTopicmst.setTopiCreatedon(dateTime);
			newEntTlTopicmst.setTopiEffectiveDate(dateTime);
			newEntTlTopicmst.setTopiInactiveDate(dateTime);			
	//	}
		/*else
		{

            newEntTlTopicmst.setTopiCreatedon(oldEntTlTopicmst.getTopiCreatedon());
			CommonMessage.debugMsg("inside service impl 3");
	    	newEntTlTopicmst.setTopiEffectiveDate(oldEntTlTopicmst.getTopiEffectiveDate());
			CommonMessage.debugMsg("inside service impl 4");
	        newEntTlTopicmst.setTopiInactiveDate(oldEntTlTopicmst.getTopiInactiveDate());
			CommonMessage.debugMsg("inside service impl 5");
        	newEntTlTopicmst.setTopiIschild(oldEntTlTopicmst.getTopiIschild());	
			CommonMessage.debugMsg("inside service impl 6");
		}	*/	
		
		//if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiLocationid()))
		//	newEntTlTopicmst.setTopiLocationid("{}");			
		
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
		
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiSpokeid()))
			newEntTlTopicmst.setTopiSpokeid("{}");	
		if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiEvaluationtypeid()))
			newEntTlTopicmst.setTopiEvaluationtypeid("{}");	
		
		newEntTlTopicmst.setTopiModifiedon(dateTime);	
		if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiRelatedto()))
			newEntTlTopicmst.setTopiRelatedto("-");
		
		if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiTrainingmode()))
			newEntTlTopicmst.setTopiTrainingmode("-");
	//	if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiCategory()))
			newEntTlTopicmst.setTopiCategory("-");
		
		//if(!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiTempfield5()))
			newEntTlTopicmst.setTopiTempfield5("-");
		CommonMessage.debugMsg("inside service impl"+newEntTlTopicmst);
		return newEntTlTopicmst;
	}

	@Override
	public EntTlTopicmst update(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst existEntTlTopicmst,String trRoleid) throws  Exception {
			String validationsFor="createtopic";
			validations.validate(newEntTlTopicmst,"TopicEntrymst",validationsFor);	
			/*if(trRoleid == null|| trRoleid == " "|| trRoleid ==""){
		 		throw new ValidationExceptions("RoleKeyid-required");
		 	}*/
			newEntTlTopicmst=fillValues( newEntTlTopicmst,  existEntTlTopicmst);
			EntTlTopicLinkRoledtl newEntTlLinkRoledtl=new EntTlTopicLinkRoledtl();
			newEntTlLinkRoledtl=fillTrValues (newEntTlTopicmst, newEntTlLinkRoledtl,trRoleid);
			CommonMessage.debugMsg("newDetails::"+newEntTlLinkRoledtl.getToprCreatedon());
			return this.entTlTopicmstDao.updateTopic(newEntTlTopicmst,newEntTlLinkRoledtl,trRoleid);
	}

	@Override
	public void DeleteTopiclist(String keyid) throws BusinessApplicationExceptions, Exception {
		this.entTlTopicmstDao.DeleteTopiclist(keyid);
		
	}

	@Override
	public EntTlTopicmst delete(EntTlTopicmst entTlTopicmst) throws Exception {
		// TODO Auto-generated method stub
			return this.entTlTopicmstDao.deleteTopicmst(entTlTopicmst);
	}

	@Override
	public List<String[]> getSql(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTopicmstDao.getSql(keyid);
	}

	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		return entTlTopicmstDao.selectCount(commonFilter);
	}

	@Override
	public List<ComboBox> getDeliveryModeCombo(ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TMOD_NAME");
		comboFilter.setCode("TMOD_CODE");
		comboFilter.setIdField("TMOD_KEYID");
		
		comboFilter.setCondSql(" AND TMOD_ACTIVE='Y' ");	
		comboFilter.setTableName(TableNames.TBL_ENT_TL_DELIVERYMODEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	  public String getLocationid(String locationid) throws Exception{
		       return entTlTopicmstDao.getLocationid(locationid);  
	  }
}
