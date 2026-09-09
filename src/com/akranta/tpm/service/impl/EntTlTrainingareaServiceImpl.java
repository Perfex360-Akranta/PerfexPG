package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.taglibs.standard.lang.jpath.adapter.Convert;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
//import com.akranta.tpm.bean.EntTlTrainingareaBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlTrainingareaDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.EntTlTrainingareaDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.service.EntTlTrainingareaService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTlTrainingareaServiceImpl implements EntTlTrainingareaService{

	private EntTlTrainingareaDao entTlTrainingareaDao; 
	private CommonFilterDao commonFilterDao ;
	private Validations validations ;
	public EntTlTrainingareaServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlTrainingareaDao = new EntTlTrainingareaDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	
	
	public List<EntTlTrainingarea> getEntTlTrainingareaValues(EntTlTrainingarea entTlTrainingarea) throws Exception {
		return this.entTlTrainingareaDao.getEntTlTrainingareaValues(entTlTrainingarea);
	}
	public List<EntTlTrainingarea> getAllTrArea(EntTlTrainingarea EntTlTrainingarea) throws Exception {
		return this.entTlTrainingareaDao.getAllTrArea(EntTlTrainingarea);
	}
	public EntTlTrainingarea create(EntTlTrainingarea newEntTlTrainingarea,EntTlTrainingarea oldEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		try{
			String validationsFor="create";		
			validations.validate(newEntTlTrainingarea,"EntTlTrainingarea",validationsFor);	
			newEntTlTrainingarea=fillValues( newEntTlTrainingarea,  oldEntTlTrainingarea);			
			return this.entTlTrainingareaDao.create(newEntTlTrainingarea);
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
	public List<EntTlTrainingarea> createList(List<EntTlTrainingarea> newEntTlTrainingarea,List<EntTlTrainingarea> oldEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		try{
			String validationsFor="create";		
			//validations.validate(newEntTlTrainingarea,"EntTlTrainingarea",validationsFor);	
			newEntTlTrainingarea=fillValuesList( newEntTlTrainingarea,  oldEntTlTrainingarea);			
			return this.entTlTrainingareaDao.createList(newEntTlTrainingarea);
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
	public EntTlTrainingarea update(EntTlTrainingarea newEntTlTrainingarea,EntTlTrainingarea oldEntTlTrainingarea)throws Exception
	{
		try{
			String validationsFor="create";		
			validations.validate(newEntTlTrainingarea,"EntTlTrainingarea",validationsFor);	
			newEntTlTrainingarea=fillValues( newEntTlTrainingarea,  oldEntTlTrainingarea);
			return this.entTlTrainingareaDao.update(newEntTlTrainingarea);
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
	public List<ComboBox> getParentComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter trArea = commonFilter.getTrainingtype();
		trArea.setIdField("TRAR_KEYID");		
		trArea.setNameField("TRAR_NAME");
		String refType=commonFilter.getType();
		if (CommonFunctions.isValidKeyId(refType))
			trArea.setCondSql(" AND TRAR_ELEMENTTYPE='" + refType + "'");	
		
		trArea.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);		
		return commonFilterDao.fillComboValues(trArea);
	}	
	
	private EntTlTrainingarea fillValues(EntTlTrainingarea newEntTlTrainingarea, EntTlTrainingarea oldEntTlTrainingarea)
	{
		newEntTlTrainingarea.setTrarActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if(newEntTlTrainingarea.getTrarKeyid()== null )			
		{	
			newEntTlTrainingarea.setTrarCreatedon(dateTime);	
		}
		else
		{newEntTlTrainingarea.setTrarCreatedon(oldEntTlTrainingarea.getTrarCreatedon());}
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarName()))
			newEntTlTrainingarea.setTrarName("{}");
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarParentid()))
			newEntTlTrainingarea.setTrarParentid("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarLevelno()))
			newEntTlTrainingarea.setTrarLevelno("0");
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarElementtype()))
			newEntTlTrainingarea.setTrarElementtype("{}");			
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarRefid()))
			newEntTlTrainingarea.setTrarRefid("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarReftype()))
			newEntTlTrainingarea.setTrarReftype("{}");	
		
		if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarRemarks()))
			newEntTlTrainingarea.setTrarRemarks("{}");
		
		newEntTlTrainingarea.setTrarModifiedon(dateTime);	
		
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
	private List<EntTlTrainingarea> fillValuesList(List<EntTlTrainingarea> newEntTlTrainingarea,
			List<EntTlTrainingarea> oldEntTlTrainingarea) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		// TODO Auto-generated method stub
		String validationsFor;	
		 EntTlTrainingarea entTlTrainingarea=new EntTlTrainingarea();
		String dateTime = CommonFunctions.dateTimeNow();		
		if (newEntTlTrainingarea!= null && newEntTlTrainingarea.size() > 0) 
		{
			CommonMessage.debugMsg("iNSIDE fOR");
			for(int i=0;i<=newEntTlTrainingarea.size()-1;i++)
			{	
				//entTlTrainingarea=newEntTlTrainingarea.get(i);
				//if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarKeyid())){
				newEntTlTrainingarea.get(i).setTrarCreatedon(dateTime);
				//}
				newEntTlTrainingarea.get(i).setTrarActive("Y");
				newEntTlTrainingarea.get(i).setTrarModifiedon(dateTime);
				if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarName()))
					newEntTlTrainingarea.get(i).setTrarName("{}");
				
				//if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarParentid()))
					//newEntTlTrainingarea.get(i).setTrarParentid("{}");	
				
				if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarLevelno()))
					newEntTlTrainingarea.get(i).setTrarLevelno("0");
				
				//if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarElementtype()))
					//newEntTlTrainingarea.get(i).setTrarElementtype("{}");			
				
				if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarRefid()))
					newEntTlTrainingarea.get(i).setTrarRefid("{}");	
				
				//if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarReftype()))
					//newEntTlTrainingarea.get(i).setTrarReftype("{}");	
				
				if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.get(i).getTrarRemarks()))
					newEntTlTrainingarea.get(i).setTrarRemarks("{}");
				
				newEntTlTrainingarea.get(i).setTrarModifiedon(dateTime);	
				
				newEntTlTrainingarea.get(i).setTrarTempfield1("-");
				newEntTlTrainingarea.get(i).setTrarTempfield2("-");
				newEntTlTrainingarea.get(i).setTrarTempfield3("-");
				newEntTlTrainingarea.get(i).setTrarTempfield4("-");
				newEntTlTrainingarea.get(i).setTrarTempfield5("-");
				newEntTlTrainingarea.get(i).setTrarTempfield6("-");
				newEntTlTrainingarea.get(i).setTrarTempfield7("-");
				newEntTlTrainingarea.get(i).setTrarTempfield8("-");
				newEntTlTrainingarea.get(i).setTrarTempfield9("-");
				newEntTlTrainingarea.get(i).setTrarTempfield10("-");		
				
			}
		}
		return newEntTlTrainingarea;
	
	}

	@Override
	public EntTlTrainingarea delete(EntTlTrainingarea EntTlTrainingarea) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		try{
			return this.entTlTrainingareaDao.delete(EntTlTrainingarea);
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
	public String validateTrAreaLevel(EntTlTrainingarea entTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		int menuLevel=0;
		int configLevel=0;
		String validate=null;
		try{
			menuLevel=this.entTlTrainingareaDao.getTrAreaLevel(entTlTrainingarea);
			menuLevel=menuLevel-5;
			CommonMessage.debugMsg("menuLevel:" + menuLevel);
			configLevel=this.entTlTrainingareaDao.getConfigTrAreaLevel();		
			CommonMessage.debugMsg("configLevel:" + configLevel);
			if (menuLevel<configLevel){
				validate="Valid";			
			}	
			else{
				validate=Convert.toString(configLevel);
			}
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		return validate;
	}
	@Override
	public String validateDelTrAreaLevel(EntTlTrainingarea EntTlTrainingarea)throws Exception{
		int menuLevel=0;		
		String validate="Not Valid";
		List<EntTlTrainingarea> newEntTlTrainingarea=new ArrayList<EntTlTrainingarea>();
		newEntTlTrainingarea=this.entTlTrainingareaDao.getAllTrArea(EntTlTrainingarea);
		menuLevel=newEntTlTrainingarea.size();	
		CommonMessage.debugMsg("menuLevel:" + menuLevel);
		if (menuLevel<=0){
			validate="Valid";			
		}		
		return validate;
	}
	
	@Override
	public EntTlTrainingarea select(EntTlTrainingarea entTlTrainingarea) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTrainingareaDao.select(entTlTrainingarea);
	}
	
	@Override
	public List<EntTlTrainingarea> selectList(EntTlTrainingarea entTlTrainingarea)throws Exception{
		// TODO Auto-generated method stub
		return this.entTlTrainingareaDao.selectList(entTlTrainingarea);
	}
	
	@Override
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception{
		return this.entTlTrainingareaDao.getSearchNode(searchNode,originalId);
	}
	
	public List<String []> getParentElem(String elemId) throws Exception {
		return this.entTlTrainingareaDao.getParentElem(elemId);
	}
	
	public List<String []> getChildElem(EntTlTrainingarea entTlTrainingarea,String start,String end) throws Exception{
		return this.entTlTrainingareaDao.getChildElem(entTlTrainingarea,start,end);
	}
	
	public String getTotalCount(EntTlTrainingarea entTlTrainingarea) throws Exception{
		return this.entTlTrainingareaDao.getTotalCount(entTlTrainingarea);
	}
	public List<ComboBox> getTrainingAreaCombo(String type) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("TRAR_NAME");
		if( UIUtils.isValidKeyId(type) )
		{
			comboFilter.setCondSql(" AND  TRAR_REFTYPE = '" + type +"'");
		}		
		//comboFilter.setIdField("TRAR_KEYID");
		comboFilter.setIdField("TRAR_NAME");
		comboFilter.setNameField("TRAR_NAME");
		comboFilter.setOrderByField("TRAR_NAME");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);
		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	@Override
	public String deleteTopic(String topicid) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTrainingareaDao.deleteTopic(topicid);
	}
	
}
